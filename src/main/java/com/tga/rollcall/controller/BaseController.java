package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.annotations.PrintParams;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dto.User;
import com.tga.rollcall.service.AdminService;
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
public class BaseController  extends Base {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    
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
    
    /**
     * 获取分组列表</br>
     * <b>学生注册选择对应分组</b>
     * @param request
     * @return
     */
    @RequestMapping(value = "/getGroup", method = RequestMethod.GET)
    public ResultBase<?> getGroup(HttpServletRequest request) {
        return adminService.getGroupList();
    }

    /**
     * 增加分组
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public ResultBase<?> addGroup(HttpServletRequest request) {
        return adminService.addGroup(null, null);
    }
    
    /**
     * 开启用户账号
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/openUserAccount", method = RequestMethod.POST)
    public Object openStudentAccount(HttpServletRequest request) {
        User user = getUserInfo(request);
        return adminService.openUserAccount(user.getUserId());
    }
}
