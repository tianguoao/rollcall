package com.tga.rollcall.dao;

import com.tga.rollcall.entity.LeaveTask;
import com.tga.rollcall.entity.LeaveTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * LeaveTaskMapper继承基类
 */
@Repository
public interface LeaveTaskMapper extends MyBatisBaseDao<LeaveTask, Long, LeaveTaskExample> {
    @Select("select id, user_id, teacher_id, reasons_for_leave, start_date, end_date, is_agree, create_date, update_date "
            + " from leave_task where user_id=#{studentId}")
    List<LeaveTask> quereyLeaveTask(@Param("studentId")  Long studentId);
    /**
     * 同意请假任务
     * @param taskId
     * @return
     */
    @Update(" update leave_task set is_agree=1 where id=#{taskId}")
    int updateAgree(@Param("taskId")  Long taskId);
    
    /**
     * 拒绝请假任务
     * @param taskId
     * @return
     */
    @Update(" update leave_task set is_agree=1 where id=#{taskId}")
    int updateNotAgree(@Param("taskId")  Long taskId);
}