package com.machi.springbootweb.dto;

import lombok.Data;

/**购物车
 * Created by dell on 2018/3/1.
 */

@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;


    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
