package com.tga.rollcall.aspect;

import java.lang.reflect.Field;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 打印接口入参注解实现
 * @author Mario 
 * @version 2019年2月18日 上午11:32:04 
 * Class: PrintParamsAspect.java
 */
@Aspect
@Component
@Slf4j
public class PrintParamsAspect {
    @Pointcut("@annotation(com.tga.rollcall.annotations.PrintParams)")
    public void printParamsCut() {}

    @Before("printParamsCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
            if (null == attributes) {
                throw new RuntimeException("printParamsCut ServletRequestAttributes null  error !");
            }
            HttpServletRequest request = attributes.getRequest();
            if (null == request) {
                throw new RuntimeException("printParamsCut HttpServletRequest null  error !");
            }
            String requestUrl = request.getServerName() + ":" + request.getServerPort()
                    + request.getRequestURI();
            String method = request.getMethod();
            String queryString = request.getQueryString();
            // 参数
            Object[] args = joinPoint.getArgs();
            String params = null;
            if (null != args && args.length > 0) {
                if (RequestMethod.POST.toString().equals(method)) {
                    Object object = args[0];
                    if (object instanceof HttpServletRequest
                            || object instanceof HttpServletResponse) {
                        log.warn("**** PrintParams   not  data ! requestMethod:{} ,requestUrl:{}",
                                method, requestUrl);
                        return;
                    }
                    params = JSONObject.toJSONString(object);
                } else if (RequestMethod.GET.toString().equals(method)) {
                    params = queryString;
                }
                log.info(" **** PrintParams requestMethod:{} ,requestUrl:{},  param:{}", method,
                        requestUrl, params);
            } else {
                log.info(" **** PrintParams   not  data ! requestMethod:{} ,requestUrl:{}", method,
                        requestUrl);
            }
        } catch (Exception e) {
            log.error("PrintParamsAspect  error:{}", e);
        }
    }

    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = Maps.newHashMap();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            // 设置些属性是可以访问的
            f.setAccessible(true);
            try {
                Object val = f.get(obj);
                // 得到此属性的值
                // 设置键值
                map.put(f.getName(), val);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
