package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import com.tga.rollcall.dto.User;

/**
 * 
 * 公共处理类
 * @author  Mario 
 * @version 2019年10月15日 下午8:30:48
 * Class: Base.java
 */
public class Base {
    /**
     * 获取当前登陆的用户信息
     * 
     * @param request
     * @return
     */
    public static User getUserInfo(HttpServletRequest request) {
        User user = new User();
        user.setUserId(Long.valueOf("" + request.getAttribute("userId")));
        user.setUserName(request.getAttribute("userName") + "");
        user.setUserType(Integer.valueOf(request.getAttribute("userType") + ""));
        return user;
    }
}
