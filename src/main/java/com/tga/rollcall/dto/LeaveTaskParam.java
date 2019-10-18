package com.tga.rollcall.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 请假任务参数类
 * @author  Mario 
 * @version 2019年10月17日 下午5:53:29
 * Class: LeaveTaskParam.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTaskParam {
    private Long studentId;
    private Long teacherId;
    private Long groupId;
    /**
     * 请假理由
     */
    private String reason;
    /**
     * 请假开始时间
     */
    private Date startDate;
    /**
     * 请假结束时间
     */
    private Date endDate;
    
}
