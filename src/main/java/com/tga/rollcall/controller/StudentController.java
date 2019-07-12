package com.tga.rollcall.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.entity.Student;
import com.tga.rollcall.service.StudentService;
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
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = RollCallApi.QUERY_STUDENT_LIST, method = RequestMethod.POST)
    public Object serverState(@RequestBody Student record, HttpServletRequest request) {
        log.info("**************************** test");
        return studentService.queryStudent(record);
    }
}
