package com.machi.springbootweb.service;

import com.machi.springbootweb.dto.OrderDTO;

/**
 * Created by dell on 2018/3/2.
 */

public interface BuyerOrderService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);
}
