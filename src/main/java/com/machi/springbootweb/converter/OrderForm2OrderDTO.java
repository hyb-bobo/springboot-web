package com.machi.springbootweb.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.machi.springbootweb.Enum.ResultEnum;
import com.machi.springbootweb.dto.OrderDTO;
import com.machi.springbootweb.exception.SellException;
import com.machi.springbootweb.form.OrderForm;
import com.machi.springbootweb.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/3/2.
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO orderForm2OrderDTO(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("【类型转换】 数据格式不正确, orderFormItems = {}",orderForm.getItems());
            throw new SellException(ResultEnum.PRRAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
