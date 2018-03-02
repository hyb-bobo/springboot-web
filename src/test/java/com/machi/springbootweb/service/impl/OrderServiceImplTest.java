package com.machi.springbootweb.service.impl;

import com.machi.springbootweb.Enum.OrderStatusEnum;
import com.machi.springbootweb.Enum.PayStatusEnum;
import com.machi.springbootweb.dto.OrderDTO;
import com.machi.springbootweb.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dell on 2018/3/1.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPEN_ID = "11234521";
    private final String ORDER_ID = "1519912317438995214";
    private final String BUYER_OPENID = "11234521";

    private Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName("machi");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerOpenid(BUYER_OPEN_ID);
        orderDTO.setBuyerPhone("18276298739");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("111222333");
        orderDetail2.setProductQuantity(2);
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO orderDTO1 = orderService.create(orderDTO);

        logger.info("result={}"+orderDTO1);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        logger.info("【查询某个订单】 result={}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findOrderList() throws Exception {

        PageRequest pageRequest = new PageRequest(0,2);

        Page<OrderDTO> page = orderService.findOrderList(BUYER_OPENID, pageRequest);

        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void cancle() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO dto = orderService.cancle(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(),dto.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO dto = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.PAY.getCode(),dto.getOrderStatus());
    }

    @Test
    public void pay() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO dto = orderService.pay(orderDTO);
        Assert.assertEquals(PayStatusEnum.PAY.getCode(),dto.getPayStatus());
    }

}