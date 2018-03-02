package com.machi.springbootweb.service;

import com.machi.springbootweb.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dell on 2018/3/1.
 */
public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询某个订单
    OrderDTO findOne(String orderId);

    //查询订单列表
    Page<OrderDTO> findOrderList(String buyerOpenid, Pageable pageable);

    //取消
    OrderDTO cancle(OrderDTO orderDTO);

    //完结
    OrderDTO finish(OrderDTO orderDTO);

    //支付
    OrderDTO pay(OrderDTO orderDTO);

}
