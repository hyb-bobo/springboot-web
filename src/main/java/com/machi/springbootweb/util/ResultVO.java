package com.machi.springbootweb.util;

import lombok.Data;

/**
 * Created by dell on 2018/3/1.
 */

@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}
