package com.machi.springbootweb.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by dell on 2018/3/1.
 */

@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private String productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;


}
