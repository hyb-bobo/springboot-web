package com.machi.springbootweb.service;

import com.machi.springbootweb.dto.CartDTO;
import com.machi.springbootweb.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dell on 2018/2/28.
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     *查询所有已上架的
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //增加库存
    void increaseStock(List<CartDTO> cartDTOS);

    //减少库存
    void decreaseStock(List<CartDTO> cartDTOS);


}
