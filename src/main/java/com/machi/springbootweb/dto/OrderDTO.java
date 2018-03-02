package com.machi.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.machi.springbootweb.Enum.OrderStatusEnum;
import com.machi.springbootweb.Enum.PayStatusEnum;
import com.machi.springbootweb.model.OrderDetail;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2018/3/1.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
