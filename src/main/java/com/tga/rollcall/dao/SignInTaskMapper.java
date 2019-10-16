package com.tga.rollcall.dao;

import com.tga.rollcall.entity.SignInTask;
import com.tga.rollcall.entity.SignInTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * SignInTaskMapper继承基类
 */
@Repository
public interface SignInTaskMapper extends MyBatisBaseDao<SignInTask, Long, SignInTaskExample> {
    /**
     * 获取学生签到任务
     * @param groupId
     * @return
     */
    @Select("select  id, task_name, start_date, end_date, create_task_teacher_id, create_date, group_id, " + 
            "    teacher_name, address, latitude, longitude from sign_in_task where start_date>=now() order by start_date  asc")
    @ResultMap("BaseResultMap")
    List<SignInTask> querySignInTask(@Param("groupId") Long groupId);
}