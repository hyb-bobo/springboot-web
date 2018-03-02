package com.machi.springbootweb.Enum;

import lombok.Getter;

/**
 * Created by dell on 2018/3/1.
 */

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(20,"商品库存错误"),
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(40,"订单详情不存在"),
    ORDER_STATUS_ERROR(50,"订单状态不正确"),
    ORDER_UPDATE_FAIL(60,"更新订单失败"),
    ORDER_DETAIL_EMPTY(70,"订单详情不存在"),
    PAY_STATUS_ERROR(80,"支付状态不存在"),
    PRRAM_ERROR(90,"参数不合法"),
    ORDER_OWNER_ERROR(100,"该订单不属于当前用户"),
    ;


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
