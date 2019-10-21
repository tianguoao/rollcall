package com.tga.rollcall.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tga.rollcall.annotations.NotLogin;
import com.tga.rollcall.entity.User;
import com.tga.rollcall.enums.UserTypeEnum;
import com.tga.rollcall.service.UserService;

/**
 * 
 * 用户登陆认证拦截器
 * @author  Mario 
 * @version 2019年7月19日 下午4:59:18
 * Class: AuthenticationInterceptor.java
 */
public class AuthenticationInterceptor implements HandlerInterceptor{
    @Autowired
    UserService userService;
    
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有NotLogin注释，有则跳过认证
        if (method.isAnnotationPresent(NotLogin.class)) {
            NotLogin passToken = method.getAnnotation(NotLogin.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 执行认证
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            User user = userService.queryUserInfo(Long.valueOf(userId));
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPwd())).build();
            jwtVerifier.verify(token);
            if (UserTypeEnum.STUDENT.getCode().equals(user.getUserType())
                    && "0".equals(user.getUserStatus())) {
                throw new RuntimeException("用户账号还未激活！请联系老师激活账号！");
            }
            httpServletRequest.setAttribute("userId", user.getId());
            httpServletRequest.setAttribute("userName", user.getUserName());
            httpServletRequest.setAttribute("userType", user.getUserType());
            httpServletRequest.setAttribute("groupId", user.getGroupId());
            return true;
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
}
