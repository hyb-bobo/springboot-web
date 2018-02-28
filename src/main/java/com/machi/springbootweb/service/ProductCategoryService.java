package com.machi.springbootweb.service;

import com.machi.springbootweb.model.ProductCategory;

import java.util.List;

/**
 * Created by dell on 2018/2/28.
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

    ProductCategory save(ProductCategory productCategory);

}
