package com.machi.springbootweb.service.impl;

import com.machi.springbootweb.Enum.OrderStatusEnum;
import com.machi.springbootweb.Enum.PayStatusEnum;
import com.machi.springbootweb.Enum.ResultEnum;
import com.machi.springbootweb.converter.OrderMaster2OrderDTO;
import com.machi.springbootweb.dto.CartDTO;
import com.machi.springbootweb.dto.OrderDTO;
import com.machi.springbootweb.exception.SellException;
import com.machi.springbootweb.model.OrderDetail;
import com.machi.springbootweb.model.OrderMaster;
import com.machi.springbootweb.model.ProductInfo;
import com.machi.springbootweb.repository.OrderMasterRepository;
import com.machi.springbootweb.repository.OrderRepository;
import com.machi.springbootweb.repository.ProductInfoRepository;
import com.machi.springbootweb.service.OrderService;
import com.machi.springbootweb.service.ProductService;
import com.machi.springbootweb.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 2018/3/1.
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired private ProductService productService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal accountAmount = new BigDecimal(BigInteger.ZERO);

        //订单ID
        String orderId = KeyUtil.genUniqueKey();

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //计算总价
            accountAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(accountAmount);

            //订单详情入库

            //订单详情ID
            String orderDetailId = KeyUtil.genUniqueKey();
            orderDetail.setDetailId(orderDetailId);
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderRepository.save(orderDetail);
        }

        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(accountAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNPAY.getCode());
        orderMasterRepository.save(orderMaster);

        //扣库存  java8
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetails = orderRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetails))
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOS = OrderMaster2OrderDTO.converter(page.getContent());

        return new PageImpl<OrderDTO>(orderDTOS, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancle(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改
        OrderMaster orderMaster = new OrderMaster();

        orderDTO.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);

        OrderMaster master = orderMasterRepository.save(orderMaster);
        if (master == null){
            log.error("【取消订单】更新失败, orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情,orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        //增加库存
        productService.increaseStock(cartDTOS);

        //如果已支付，需要退款
        if (orderDTO.getOrderStatus() == PayStatusEnum.PAY.getCode()){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (OrderStatusEnum.NEW.getCode() != orderDTO.getOrderStatus()){
            log.error("【完结订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.PAY.getCode());

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);
        if (master == null){
            log.error("【完结订单】保存失败,orderMaster = {}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        //判断订单状态
        if (OrderStatusEnum.NEW.getCode() != orderDTO.getOrderStatus()){
            log.error("【支付订单完成】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (PayStatusEnum.PAY.getCode() == orderDTO.getPayStatus()){
            log.error("【支付订单完成】支付状态不正确,orderId = {}, payStatus = {}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }

        //修改订单支付状态
        orderDTO.setPayStatus(PayStatusEnum.PAY.getCode());

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
