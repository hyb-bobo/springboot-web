package com.machi.springbootweb;

import com.machi.springbootweb.exception.MyException;
import com.machi.springbootweb.model.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * 使用@ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }


}
