package com.tga.rollcall.dao;

import com.tga.rollcall.entity.SignInTask;
import com.tga.rollcall.entity.SignInTaskExample;
import org.springframework.stereotype.Repository;

/**
 * SignInTaskMapper继承基类
 */
@Repository
public interface SignInTaskMapper extends MyBatisBaseDao<SignInTask, Long, SignInTaskExample> {
}