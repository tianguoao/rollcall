package com.tga.rollcall.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 统一异常处理
 * @author  Mario 
 * @version 2019年10月18日 下午5:29:25
 * Class: GlobalExceptionHandler.java
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBase<?> exceptionHandle(HttpServletRequest req, Exception ex) {
        ex.printStackTrace();
        log.error("GlobalExceptionHandler 未知异常  error:{}", ex);
        return ResultBase.Builder.error(ex.getMessage());
    }
}
