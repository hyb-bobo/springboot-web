package com.machi.springbootweb.repository;

import com.machi.springbootweb.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dell on 2018/3/1.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("10191817");
        orderDetail.setOrderId("111111");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(3.5));
        orderDetail.setProductIcon("www.xxxxx.jpg");
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(3);

        OrderDetail detail = orderRepository.save(orderDetail);
        Assert.assertNotNull(detail);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetails = orderRepository.findByOrderId("111111");
        Assert.assertNotEquals(0,orderDetails.size());
    }

}