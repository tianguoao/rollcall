package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.annotations.PrintParams;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dto.SignInTaskParam;
import com.tga.rollcall.dto.TaskSignInParam;
import com.tga.rollcall.dto.User;
import com.tga.rollcall.service.StudentService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 学生相关api
 * @author  Mario 
 * @version 2019年6月13日 下午5:00:05
 * Class: BaseController.java
 */
@RestController
@RequestMapping(RollCallApi.SERVER_NAME)
@Slf4j
public class StudentController extends Base{
    @Autowired
    StudentService studentService;
    
    /**
     * 查询某个老师名下的学生列表
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = RollCallApi.QUERY_STUDENT_LIST, method = RequestMethod.POST)
    public Object queryStudentList(HttpServletRequest request) {
        User user = getUserInfo(request);
        log.info("queryStudentList  param:{}",user.toString());
        return studentService.queryStudentList(Long.valueOf(user.getGroupId() + ""));
    }

    /**
     * 获取学生待签到任务列表
     * 
     * @param param
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/querySignInTaskList", method = RequestMethod.POST)
    public Object querySignInTaskList(@RequestBody SignInTaskParam param,
            HttpServletRequest request) {
        User user = getUserInfo(request);
        log.info("queryStudentList  param:{}", user.toString());
        return studentService.querySignInTaskList(Long.valueOf("" + user.getGroupId()));
    }
    /**
     * 任务签到
     * @param param
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/taskSignIn", method = RequestMethod.POST)
    public Object taskSignIn(@RequestBody TaskSignInParam param, HttpServletRequest request) {
        User user = getUserInfo(request);
        param.setStudentId(user.getUserId());
        param.setGroupId(Long.valueOf("" + user.getGroupId()));
        return studentService.taskSignIn(param);
    }

    /**
     * 查询签到任务记录
     * 
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/querySignInTaskRecord", method = RequestMethod.POST)
    public Object querySignInTaskRecord(HttpServletRequest request) {
        User user = getUserInfo(request);
        return studentService.signInTaskRecord(Long.valueOf("" + user.getGroupId()),
                user.getUserId());
    }
    
}
