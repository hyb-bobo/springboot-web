package com.machi.springbootweb.util;

import java.util.Random;

/**
 * Created by dell on 2018/3/1.
 */
public class KeyUtil {
    /**
     * @Author machi
     * 多线程环境下防止并发
     * @Date 2018/3/1 15:29
     */
    public static synchronized String genUniqueKey(){

        Random random = new Random();
        int number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
