package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dto.User;
import com.tga.rollcall.service.StudentService;
import com.tga.rollcall.service.UserService;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年6月13日 下午5:00:05
 * Class: BaseController.java
 */
@RestController
@RequestMapping(RollCallApi.SERVER_NAME)
@Slf4j
public class BaseController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/serverState", method = RequestMethod.POST)
    public Object serverState(@RequestBody String str, HttpServletRequest request) {
        log.info("**************************** test");
        return "200";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBase<?> login(@RequestBody User param, HttpServletRequest request) {
        return userService.login(param);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultBase<?> register(@RequestBody User param, HttpServletRequest request) {
        
        return userService.register(param);
    }
}
