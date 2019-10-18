package com.tga.rollcall.dao;

import com.tga.rollcall.entity.Group;
import com.tga.rollcall.entity.GroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * GroupMapper继承基类
 */
@Repository
public interface GroupMapper extends MyBatisBaseDao<Group, Long, GroupExample> {
    /**
     * 根据groupId  查询对应的负责人id
     * @param groupId
     * @return
     */
    @Select(" select leader_id FROM     group where id=#{groupId}")
    Long querTeacherIdByGroupId(@Param("groupId") Long groupId);
}