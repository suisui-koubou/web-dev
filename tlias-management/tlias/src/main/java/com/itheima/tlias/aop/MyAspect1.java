package com.itheima.tlias.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect1 {
    @Pointcut("execution(* com.itheima.tlias.service.impl.DeptServiceImpl.*(..))")
    private void pat() {} // pattern

    @Before("pat()")
    public void before(){
        log.info("[[ SpringAOP ]] Before...");
    }

    @Around("pat()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("[[ SpringAOP ]] Around before...");
        // 调用目标对象的原始方法换行
        Object Result = proceedingJoinPoint.proceed();
        log.info("[[ SpringAOP ]] Around after...");
        return Result;
    }

    @After("pat()")
    public void after(){
        log.info("[[ SpringAOP ]] After ..");
    }

    @AfterReturning("pat()")
    public void afterReturning(){
        log.info("[[ SpringAOP ]] After Returning .."); // 异常后执行
    }

    @AfterThrowing("pat()")
    public void afterThrowing(){
        log.info("[[ SpringAOP ]] After Throwing .."); // 异常后执行
    }

}
