package com.tga.rollcall.dao;

import com.tga.rollcall.entity.LeaveTask;
import com.tga.rollcall.entity.LeaveTaskExample;
import org.springframework.stereotype.Repository;

/**
 * LeaveTaskMapper继承基类
 */
@Repository
public interface LeaveTaskMapper extends MyBatisBaseDao<LeaveTask, Long, LeaveTaskExample> {
}