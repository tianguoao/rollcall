package com.tga.rollcall.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年7月10日 下午3:25:38
 * Class: LoginAspect.java
 */
@Aspect
@Component
@Slf4j
public class CheckLoginAspect {
    @Pointcut("@annotation(com.tga.rollcall.annotations.CheckLogin)")
    public void checkLoginCut() {}
    
    @Before("checkLoginCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        ServletRequestAttributes attributes =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (null == attributes) {
            throw new RuntimeException(" ServletRequestAttributes null  error !");
        }
        HttpServletRequest request = attributes.getRequest();
        if (null == request) {
            throw new RuntimeException(" HttpServletRequest null  error !");
        }
    }
}
