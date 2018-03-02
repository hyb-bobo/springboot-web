package com.machi.springbootweb.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by dell on 2018/3/2.
 */

@Data
public class OrderForm {


    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "ipenid不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;


}
