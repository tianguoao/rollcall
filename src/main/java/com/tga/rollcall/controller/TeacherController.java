package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.annotations.NotLogin;
import com.tga.rollcall.annotations.PrintParams;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.entity.Student;
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
public class TeacherController {
    /**
     * 查询本班学生注册列表
     * @param record
     * @param request
     * @return
     */
    @PrintParams
    @RequestMapping(value = "/getStudentRegisterList", method = RequestMethod.GET)
    public Object getStudentRegisterList(HttpServletRequest request) {
        log.info("**************************** test");
        return null;
    }
}
