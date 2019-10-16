package com.tga.rollcall.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tga.rollcall.dao.UserMapper;
import com.tga.rollcall.dto.User.UserInfo;
import com.tga.rollcall.entity.User;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 老师相关服务接口
 * @author  Mario 
 * @version 2019年10月15日 下午8:10:18
 * Class: TeacherService.java
 */
@Service
@Slf4j
public class TeacherService {
    @Autowired
    UserMapper userMapper;

    /**
     * 查询本班未启用的学生账号
     * 
     * @param groupId
     * @return
     */
    public ResultBase<List<User>> getStudentRegisterList(Long groupId) {
        List<User> lsit = userMapper.getStudentRegisterList(groupId);
        return ResultBase.Builder.success(lsit);
    }

    /**
     * 开启某个学生账号
     * 
     * @param groupId
     * @return
     */
    public ResultBase<?> openStudentAccount(Long userId) {
        if (userMapper.updateUserStatus(userId, 1) > 0) {
            return ResultBase.Builder.success();
        } else {
            return ResultBase.Builder.error();
        }
    }
}
