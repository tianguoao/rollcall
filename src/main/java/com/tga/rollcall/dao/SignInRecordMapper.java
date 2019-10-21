package com.tga.rollcall.dao;

import com.tga.rollcall.entity.SignInRecord;
import com.tga.rollcall.entity.SignInRecordExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * SignInRecordMapper继承基类
 */
@Repository
public interface SignInRecordMapper extends MyBatisBaseDao<SignInRecord, Long, SignInRecordExample> {
    /**
     * 汇总当前学生的签到任务记录
     * @param studentId
     * @param taskId
     * @return
     */
    @Select("select count(student_id) from sign_in_record where sign_in_task_id=#{taskId  and student_id=#{studentId}")
    int countByStudent(@Param("studentId") Long studentId,@Param("taskId") Long taskId);
}