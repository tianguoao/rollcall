package com.tga.rollcall.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tga.rollcall.dao.SignInRecordMapper;
import com.tga.rollcall.dao.SignInTaskMapper;
import com.tga.rollcall.dao.StudentMapper;
import com.tga.rollcall.dao.UserMapper;
import com.tga.rollcall.dto.TaskSignInParam;
import com.tga.rollcall.entity.SignInRecord;
import com.tga.rollcall.entity.SignInRecordExample;
import com.tga.rollcall.entity.SignInTask;
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
    @Autowired
    SignInTaskMapper signInTaskMapper;
    @Autowired
    SignInRecordMapper signInRecordMapper;
    
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
    /**
     * 查询我的待签到任务
     * @param groupId
     * @return
     */
    public ResultBase<List<SignInTask>> querySignInTaskList(Long groupId) {
        List<SignInTask> list = signInTaskMapper.querySignInTask(groupId);
        return ResultBase.Builder.success(list);
    }
    
    /**
     * 任务签到
     * @param param
     * @return
     */
    public ResultBase<?> taskSignIn(TaskSignInParam param) {
        // 判断当前学生分组下当前时间是否在签到任务内 找出任务id
        // 判断学生注册人脸数据 和 签到人脸数据是否一致
        // 人像对比接口（阿里云）
        // 判断学生当前打卡位置数据是否在指定区域内
        // 经纬度区域判断代码
        // 增加签到任务记录
        SignInRecord record = new SignInRecord();
        record.setCreateDate(new Date());
        record.setStudentId(param.getStudentId());
        record.setSignInTaskId(123L);
        signInRecordMapper.insertSelective(record);
        
        return ResultBase.Builder.success();
    }
    
    /**
     * 查询已签到记录
     * 
     * @param groupId
     * @param studentId
     * @return
     */
    public ResultBase<?> signInTaskRecord(Long groupId, Long studentId) {
        SignInRecordExample example = new SignInRecordExample();
        SignInRecordExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("create_date  asc");
        List<SignInRecord> lsit = signInRecordMapper.selectByExample(example);
        return ResultBase.Builder.success(lsit);
    }
}
