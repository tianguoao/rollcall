package com.tga.rollcall.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tga.rollcall.dao.StudentMapper;
import com.tga.rollcall.entity.Student;
import com.tga.rollcall.entity.StudentExample;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年6月17日 下午6:37:14
 * Class: StudentService.java
 */
@Service
@Slf4j
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    public void test(Student record) {
        studentMapper.insertSelective(record);
    }
    
    public ResultBase<List<Student>> queryStudent(Student record) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        List<Student> students = studentMapper.selectByExample(example);
        return ResultBase.Builder.success(students);
    }
}
