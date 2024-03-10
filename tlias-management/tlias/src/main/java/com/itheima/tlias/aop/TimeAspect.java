//package com.itheima.tlias.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component  // 交给IOC容器管理
//@Aspect     // AOP的类
//public class TimeAspect {
//    @Around("execution(* com.itheima.tlias.service.*.*(..))") // 切入点表达式
//    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable{
//        // 1. 记录开始时间
//        long begin = System.currentTimeMillis();
//        // 2. 调用原始方法
//        Object ans = joinPoint.proceed();
//        // 3. 记录结束时间
//        long end = System.currentTimeMillis();
//        log.info(joinPoint.getSignature() + "方法执行耗时: {}ms", end - begin);
//        return ans;
//    }
//}
