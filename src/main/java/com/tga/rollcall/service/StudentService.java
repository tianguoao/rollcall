package com.tga.rollcall.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tga.rollcall.dao.StudentMapper;
import com.tga.rollcall.dao.UserMapper;
import com.tga.rollcall.entity.Student;
import com.tga.rollcall.entity.StudentExample;
import com.tga.rollcall.entity.User;
import com.tga.rollcall.entity.UserExample;
import com.tga.rollcall.enums.UserTypeEnum;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 学生相关服务接口
 * @author  Mario 
 * @version 2019年6月17日 下午6:37:14
 * Class: StudentService.java
 */
@Service
@Slf4j
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserMapper userMapper;
    public void test(Student record) {
        studentMapper.insertSelective(record);
    }
    
    public ResultBase<List<Student>> queryStudent(Student record) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        List<Student> students = studentMapper.selectByExample(example);
        return ResultBase.Builder.success(students);
    }

    /**
     * 查询某个分组下的学生列表
     * 
     * @param groupId
     * @return
     */
    public ResultBase<List<User>> queryStudentList(Long groupId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        criteria.andIsDeletdEqualTo(false);
        criteria.andUserTypeEqualTo(UserTypeEnum.STUDENT.getCode());
        return ResultBase.Builder.success(userMapper.selectByExample(example));
    }
    
}
