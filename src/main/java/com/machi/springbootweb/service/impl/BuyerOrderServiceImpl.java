package com.machi.springbootweb.service.impl;

import com.machi.springbootweb.Enum.ResultEnum;
import com.machi.springbootweb.dto.OrderDTO;
import com.machi.springbootweb.exception.SellException;
import com.machi.springbootweb.service.BuyerOrderService;
import com.machi.springbootweb.service.OrderService;
import com.machi.springbootweb.util.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2018/3/2.
 */
@Service
@Slf4j
public class BuyerOrderServiceImpl implements BuyerOrderService {

    @Autowired private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
       return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("【订单取消】订单不存在, orderId = {}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancle(orderDTO);
    }

    public OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null)
            return null;
        //判断openid是否一致
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【订单详情查询】买家微信号不一致");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
