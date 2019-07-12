package com.tga.rollcall.interceptor;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *    logID请求过程中的所有log都会带上这个业务ID
 *    @Author  
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 用20位代表requestId
        String requestId = UUID.randomUUID().toString().replaceAll("-", "").substring(12);
        MDC.put("requestId", requestId);
        return true;
    }
}
