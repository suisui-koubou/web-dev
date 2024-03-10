package com.itheima.tlias.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 添加两个元注解
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时有效/保留
@Target(ElementType.METHOD)         // 这个注解只能作用在方法上
public @interface MyFirstAnnotation {

}
