package com.tga.rollcall.dao;

import com.tga.rollcall.entity.SignInRecord;
import com.tga.rollcall.entity.SignInRecordExample;
import org.springframework.stereotype.Repository;

/**
 * SignInRecordMapper继承基类
 */
@Repository
public interface SignInRecordMapper extends MyBatisBaseDao<SignInRecord, Long, SignInRecordExample> {
}