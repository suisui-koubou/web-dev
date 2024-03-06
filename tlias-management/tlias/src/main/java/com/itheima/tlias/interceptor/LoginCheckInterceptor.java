package com.itheima.tlias.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.itheima.tlias.pojo.Result;
import com.itheima.tlias.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求url
        String uri = request.getRequestURI();
        log.info("[[ DEBUG ]] 请求的 URI: {}", uri);
        // 2. 判断请求 url 是否是 login, 如果不是就跳转
        if (uri.equals("/login")){
            log.info("[[ DEBUG ]] 登陆操作! 直接放行");
            return true;
        }
        // 3. 获取请求头的令牌(Header名字由前端决定，所以要看API手册)
        String jwt = request.getHeader("token");
        // 4. 判断令牌是否存在
        if (!StringUtils.hasLength(jwt)){
            log.info("[[ DEBUG ]] 令牌不存在!");
            Result err = Result.error("NOT_LOGIN");
            // FastJSON将结果转换为JSON (注意这里不能标注 @RestController (因为不是 Controller),
            // 所以返回的结果不会自动转换为JSON)
            String notLoginStr = JSONObject.toJSONString(err);
            response.getWriter().write(notLoginStr);
            return false;
        }
        // 5. 解析令牌
        try{
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            // e.printStackTrace();
            log.info("[[ DEBUG ]] 解析令牌失败，未登陆");
            Result err = Result.error("NOT_LOGIN");
            // FastJSON将结果转换为JSON (注意这里不能标注 @RestController (因为不是 Controller),
            // 所以返回的结果不会自动转换为JSON)
            String notLoginStr = JSONObject.toJSONString(err);
            response.getWriter().write(notLoginStr);
            return false;
        }

        // 6. 放行
        log.info("[[ DEBUG ]] 令牌合法, 放行");
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("[[ DEBUG ]] Interceptor:: postHandle");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("[[ DEBUG ]] Interceptor:: afterCompletion");
//    }
}
