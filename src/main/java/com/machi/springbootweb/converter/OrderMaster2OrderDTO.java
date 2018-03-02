package com.machi.springbootweb.converter;

import com.machi.springbootweb.dto.OrderDTO;
import com.machi.springbootweb.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 2018/3/1.
 */

public class OrderMaster2OrderDTO {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster,orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasters){
       return orderMasters.stream().map(
                e -> converter(e)
        ).collect(Collectors.toList());

    }

}
