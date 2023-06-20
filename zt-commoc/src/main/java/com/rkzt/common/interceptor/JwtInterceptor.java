package com.rkzt.common.interceptor;


import com.rkzt.common.anno.JwtToken;
import com.rkzt.common.config.JwtConfig;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Token");
        System.out.println(token);
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(JwtToken.class)) {
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);
            if (jwtToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 userId
                String userId = JwtConfig.getUserId(token);
                System.out.println("用户id:" + userId);

                // 验证 token
                JwtConfig.checkSign(token);
            }
        }
        return true;
    }

}
