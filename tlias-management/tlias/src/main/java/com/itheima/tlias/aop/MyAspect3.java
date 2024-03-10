package com.itheima.tlias.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
//
@Slf4j
@Component
@Aspect
public class MyAspect3 {
    @Pointcut("@annotation(com.itheima.tlias.aop.MyFirstAnnotation)")
    private void pat() {} // pattern

    @Before("pat()")
    public void before(){
        log.info("[[ SpringAOP-Annotation ]] Before...");
    }
}
