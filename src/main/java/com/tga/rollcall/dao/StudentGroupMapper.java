package com.tga.rollcall.dao;

import com.tga.rollcall.entity.StudentGroup;
import com.tga.rollcall.entity.StudentGroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * StudentGroupMapper继承基类
 */
@Repository
public interface StudentGroupMapper extends MyBatisBaseDao<StudentGroup, Long, StudentGroupExample> {
    /**
     * 根据groupId  查询对应的负责人id
     * @param groupId
     * @return
     */
    @Select(" select leader_id FROM     group where id=#{groupId}")
    Long querTeacherIdByGroupId(@Param("groupId") Long groupId);
}