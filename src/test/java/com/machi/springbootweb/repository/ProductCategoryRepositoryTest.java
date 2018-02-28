package com.machi.springbootweb.repository;

import com.machi.springbootweb.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dell on 2018/2/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired private ProductCategoryRepository repository;

    @Test
    public void test1(){
        ProductCategory productCategory = repository.findOne(123);
        System.out.println(productCategory.toString());
    }


    //@Transactional  测试完成之后会数据回滚，这样数据库中没有测试数据
    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("xixixi");
        productCategory.setCategoryType(3);

        ProductCategory category = repository.save(productCategory);
        Assert.assertNotNull(category);
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = repository.findOne(124);

        productCategory.setCategoryType(1);

        repository.save(productCategory);
    }

    @Test
    public void testIn(){
        List<ProductCategory> list = repository.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0,list.size());
    }
}