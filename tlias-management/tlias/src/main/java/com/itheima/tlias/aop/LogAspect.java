package com.itheima.tlias.aop;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.tlias.mapper.OperateLogMapper;
import com.itheima.tlias.pojo.OperateLog;
import com.itheima.tlias.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.itheima.tlias.anno.TliasLog)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 提示: 从JWT令牌中获取已经登陆的员工信息(不然怎么知道是谁在访问)
        // 读取 请求头 中的 JWT令牌?
        //      提示: 如何获取请求头？直接往IOC注入一个 HttpServletRequest 对象!
        String jwt = request.getHeader("token"); // 前端定义的
        Claims claims = JwtUtils.parseJwt(jwt); // 我们在令牌内存放的自定义数据(一个Map集合，是登陆人身份信息)
        // 操作人id
        Integer userId = (Integer) claims.get("id");
        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodArgs = Arrays.toString(args);
        Long begin = System.currentTimeMillis();
        // 方法返回值(需要转换为Json才能顺利返回，要注意这里没有@RestController的注解)
        Object result = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();
        String resultStr = JSONObject.toJSONString(result);
        // 操作耗时
        Long timeSpent = end - begin;
        // 调用原始目标方法运行
        operateLogMapper.insert(new OperateLog(
                null, userId, operateTime, className, methodName, methodArgs, resultStr, timeSpent
        ));
        log.info("[[ SpringAOP ]] 记录操作日志的样例!!! ");
        return result;
    }
}
