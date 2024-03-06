package com.itheima.tlias.exception;

import com.itheima.tlias.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) // 制定捕获所有异常`Exception.class`
    public Result ex(Exception ex){
        // ex.printStackTrace();
        return Result.error("对不起，操作失败。请联系管理员。");
    }
}