package com.machi.springbootweb.Enum;

import lombok.Getter;

/**
 * Created by dell on 2018/3/1.
 */

@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    PAY(1,"完结"),
    CANCLE(2,"已取消")
    ;

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
