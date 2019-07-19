package com.tga.rollcall.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tga.rollcall.util.ResultBase;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年7月19日 下午5:45:31
 * Class: ExceptionHandler.java
 */
@ControllerAdvice
public class AllExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (StringUtils.isEmpty(msg)) {
            msg = "服务器出错";
        }
        return ResultBase.Builder.initError(e.getMessage());
    }
}
