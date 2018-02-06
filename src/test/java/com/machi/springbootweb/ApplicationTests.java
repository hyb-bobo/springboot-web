package com.machi.springbootweb;

import com.machi.springbootweb.model.BlogProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dell on 2018/2/6.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private BlogProperties blogProperties;


    @Test
    public void getHello(){
        Assert.assertEquals(blogProperties.getName(),"程序猿DD");
        Assert.assertEquals(blogProperties.getTitle(),"Spring Boot教程");
    }

}
