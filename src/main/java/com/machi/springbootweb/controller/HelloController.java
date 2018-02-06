package com.machi.springbootweb.controller;

import com.machi.springbootweb.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2018/2/6.
 */

@Api(value = "meetingCon", description = "测试环节")
@RestController
public class HelloController {

    @ApiOperation(value = "hello初体验",notes = "hello初体验")
    @GetMapping(value = "/hello")
    public String hello(){
        return "程序员，嘟嘟";
    }

    @ApiOperation(value="swagger-Post测试", notes="swagger-Post测试")
    @ApiImplicitParam(name = "String", value = "String的东西", required = false, dataType = "String")
    @PostMapping(value = "/postTest")
    public String postTest(@RequestParam(value = "name") String name){

        return name;
    }


    /**
     * Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，
     * 会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。
     * 由于页面提示不够友好，我们通常需要去实现我们自己的异常提示
     */
    @ApiOperation(value="swagger-error测试", notes="swagger-error测试")
    @GetMapping(value = "/testError")
    public String testError(HttpServletRequest request) throws Exception{
        System.out.println("=======================");
        throw  new MyException("发生错误");
    }

}
