package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.annotations.PrintParams;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dto.ReviewLeaveTaskParam;
import com.tga.rollcall.dto.SignInTaskParam;
import com.tga.rollcall.dto.User;
import com.tga.rollcall.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 老师相关操作接口
 * @author  Mario 
 * @version 2019年10月15日 下午7:23:59
 * Class: TeacherController.java
 */
@RestController
@RequestMapping(RollCallApi.SERVER_NAME)
@Slf4j
public class TeacherController extends Base {
    @Autowired
    TeacherService teacherService;
    
    /**
     * 查询本班学生注册列表
     * @param record
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/getStudentRegisterList", method = RequestMethod.GET)
    public Object getStudentRegisterList(HttpServletRequest request) {
        User user = getUserInfo(request);
        return teacherService.getStudentRegisterList(Long.valueOf("" + user.getGroupId()));
    }
    
    /**
     * 开启学生注册的账号
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/openStudentAccount", method = RequestMethod.POST)
    public Object openStudentAccount(HttpServletRequest request) {
        User user = getUserInfo(request);
        return teacherService.openStudentAccount(user.getUserId());
    }
    
    /**
     * 发布打卡任务
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/addSignInTask", method = RequestMethod.POST)
    public Object addSignInTask(@RequestBody SignInTaskParam param ,HttpServletRequest request) {
        User user = getUserInfo(request);
        return teacherService.openStudentAccount(user.getUserId());
    }
    
    /**
     * 审核学生请假
     * 
     * @param param
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/reviewLeaveTask", method = RequestMethod.POST)
    public Object reviewLeaveTask(@RequestBody ReviewLeaveTaskParam param,
            HttpServletRequest request) {
        User user = getUserInfo(request);
        return teacherService.reviewLeaveTask(param);
    }
    
}
