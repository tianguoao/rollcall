package com.tga.rollcall.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tga.rollcall.dao.GroupMapper;
import com.tga.rollcall.dao.StudentGroupMapper;
import com.tga.rollcall.dao.UserMapper;
import com.tga.rollcall.entity.Group;
import com.tga.rollcall.entity.GroupExample;
import com.tga.rollcall.entity.StudentGroup;
import com.tga.rollcall.entity.StudentGroupExample;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 后台管理相关接口
 * @author  Mario 
 * @version 2019年10月15日 下午8:10:03
 * Class: AdminService.java
 */
@Service
@Slf4j
public class AdminService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentGroupMapper groupMapper;
    
    /**
     * 开启某个用户账号
     * @param groupId
     * @return
     */
    public ResultBase<?> openUserAccount(Long userId) {
        if (userMapper.updateUserStatus(userId, 1) > 0) {
            return ResultBase.Builder.success();
        } else {
            return ResultBase.Builder.error();
        }
    }
    /**
     * 获取分组列表
     * @return
     */
    public ResultBase<List<StudentGroup>> getGroupList() {
        StudentGroupExample example = new StudentGroupExample();
        StudentGroupExample.Criteria criteria = example.createCriteria();
        List< StudentGroup> list = groupMapper.selectByExample(example);
        return ResultBase.Builder.success(list);
    }
    
    /**
     * 增加分组
     * @param groupName
     * @param teacherId
     * @return
     */
    public ResultBase<?> addGroup(String groupName, Long teacherId) {
        StudentGroup record = new  StudentGroup();
        record.setGroupName(groupName);
        record.setLeaderId(teacherId);
        if (groupMapper.insertSelective(record) > 0) {
            return ResultBase.Builder.success();
        } else {
            return ResultBase.Builder.error();
        }
    }
    
}
