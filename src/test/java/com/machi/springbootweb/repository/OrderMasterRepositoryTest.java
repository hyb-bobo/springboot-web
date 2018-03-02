package com.machi.springbootweb.repository;

import com.machi.springbootweb.model.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dell on 2018/3/1.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void insert(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("111111");
        orderMaster.setBuyerName("马驰");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setBuyerPhone("18156743898");
        orderMaster.setBuyerOpenid("123456");
        orderMaster.setOrderAmount(new BigDecimal(5.6));

        OrderMaster master = repository.save(orderMaster);
        Assert.assertNotNull(master);
    }



    @Test
    public void findByBuyerOpenId() throws Exception {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> page = repository.findByBuyerOpenid("123456", pageRequest);
        Assert.assertNotEquals(0,page.getTotalElements());


    }

}