package com.machi.springbootweb.exception;

import com.machi.springbootweb.Enum.ResultEnum;

/**
 * Created by dell on 2018/3/1.
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
