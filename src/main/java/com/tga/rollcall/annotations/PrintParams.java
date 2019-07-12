package com.tga.rollcall.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 请求参数信息打印注解类
 * 
 * @author Mario 
 * @version 2019年2月18日 上午11:08:19 <br>
 * @Class PrintParams.java
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintParams {
    
}
