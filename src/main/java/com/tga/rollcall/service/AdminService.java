package com.tga.rollcall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tga.rollcall.dao.UserMapper;
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
}
