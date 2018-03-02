package com.machi.springbootweb.Enum;

import lombok.Data;
import lombok.Getter;

/**
 * Created by dell on 2018/2/28.
 */

@Getter
public enum ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
