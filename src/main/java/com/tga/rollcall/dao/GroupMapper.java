package com.tga.rollcall.dao;

import com.tga.rollcall.entity.Group;
import com.tga.rollcall.entity.GroupExample;
import org.springframework.stereotype.Repository;

/**
 * GroupMapper继承基类
 */
@Repository
public interface GroupMapper extends MyBatisBaseDao<Group, Long, GroupExample> {
}