package com.machi.springbootweb.service.impl;

import com.machi.springbootweb.model.ProductCategory;
import com.machi.springbootweb.repository.ProductCategoryRepository;
import com.machi.springbootweb.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2018/2/28.
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return repository.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
