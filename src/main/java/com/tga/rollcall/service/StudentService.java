package com.tga.rollcall.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.tga.rollcall.dao.GroupMapper;
import com.tga.rollcall.dao.LeaveTaskMapper;
import com.tga.rollcall.dao.SignInRecordMapper;
import com.tga.rollcall.dao.SignInTaskMapper;
import com.tga.rollcall.dao.StudentMapper;
import com.tga.rollcall.dao.UserMapper;
import com.tga.rollcall.dto.LeaveTaskParam;
import com.tga.rollcall.dto.TaskSignInParam;
import com.tga.rollcall.entity.LeaveTask;
import com.tga.rollcall.entity.LeaveTaskExample;
import com.tga.rollcall.entity.SignInRecord;
import com.tga.rollcall.entity.SignInRecordExample;
import com.tga.rollcall.entity.SignInTask;
import com.tga.rollcall.entity.Student;
import com.tga.rollcall.entity.StudentExample;
import com.tga.rollcall.entity.User;
import com.tga.rollcall.entity.UserExample;
import com.tga.rollcall.enums.UserTypeEnum;
import com.tga.rollcall.util.LocationUtils;
import com.tga.rollcall.util.ResultBase;
import com.tga.rollcall.util.LocationUtils.Position;
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
    @Autowired
    LeaveTaskMapper leaveTaskMapper;
    @Autowired
    GroupMapper groupMapper;
    
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
        log.info("data :{}",JSONObject.toJSON(list));
        return ResultBase.Builder.success(list);
    }
    
    /**
     * 任务签到
     * @param param
     * @return
     */
    public ResultBase<?> taskSignIn(TaskSignInParam param) {
        SignInTask task=signInTaskMapper.queryNowSignInTask(param.getGroupId());
        if(null==task) {
            return ResultBase.Builder.initError("你目前没有要签到的任务");
        }
        if(signInRecordMapper.countByStudent( param.getStudentId(), task.getId())>0) {
            return ResultBase.Builder.success("你已经签到过任务： "+task.getTaskName());
        }
        // 判断学生当前打卡位置数据是否在指定区域内
        // 位置相差50米以上则定位失败 不能进任务签到
        boolean success =
                LocationUtils.getDistance(new Position(param.getLatitude(), param.getLongitude()),
                        new Position(task.getLatitude(), task.getLongitude())) > 50;
        if (!success) {
            return ResultBase.Builder.error("请到达任务指定位置进行打卡 ");
        }
        // 判断学生注册人脸数据 和 签到人脸数据是否一致
        // 人像对比接口（阿里云）
        // 增加签到任务记录
        SignInRecord record = new SignInRecord();
        record.setCreateDate(new Date());
        record.setStudentId(param.getStudentId());
        record.setSignInTaskId(task.getId());
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
    
    /**
     * 新增请假任务
     * @param param
     * @return
     */
    public ResultBase<?> addLeaveTask(LeaveTaskParam param) {
        Long teacherId=groupMapper.querTeacherIdByGroupId(param.getGroupId());
        param.setTeacherId(teacherId);
        LeaveTask record = new LeaveTask();
        record.setTeacherId(param.getTeacherId());
        record.setStartDate(param.getStartDate());
        record.setEndDate(param.getEndDate());
        record.setReasonsForLeave(param.getReason());
        record.setUserId(param.getStudentId());
        if (leaveTaskMapper.insert(record) > 0) {
            return ResultBase.Builder.success();
        }
        return ResultBase.Builder.error(null);
    }
    
    /**
     * 查询学生请假记录及状态
     * @param type  0:all 1:agree  2:notAgree
     * @return
     */
    public ResultBase<?> queryLeaveTaskList(String type, Long studentId) {
        LeaveTaskExample example = new LeaveTaskExample();
        LeaveTaskExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(studentId);
        if ("1".equals(type)) {
            criteria.andIsAgreeEqualTo(1);
        }
        if ("2".equals(type)) {
            criteria.andIsAgreeEqualTo(0);
        }
        List<LeaveTask> list = leaveTaskMapper.selectByExample(example);
        return ResultBase.Builder.success(list);
    }
    
}
