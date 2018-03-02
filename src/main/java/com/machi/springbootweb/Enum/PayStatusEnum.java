package com.machi.springbootweb.Enum;

import lombok.Getter;

/**
 * Created by dell on 2018/3/1.
 */

@Getter
public enum PayStatusEnum {
    UNPAY(0,"未支付"),
    PAY(1,"支付成功")
    ;


    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
