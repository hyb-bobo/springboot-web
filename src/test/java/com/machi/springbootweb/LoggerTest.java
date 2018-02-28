package com.machi.springbootweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dell on 2018/2/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "machi",pwd = "123456";
        logger.info("name:-{},pwd:-{}",name,pwd);
        logger.info("info...");
        logger.debug("debug...");
        logger.error("error...");
    }



}
