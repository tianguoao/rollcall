package com.tga.rollcall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 审批学生请假任务参数类
 * @author  Mario 
 * @version 2019年10月17日 下午7:06:29
 * Class: ReviewLeaveTaskParam.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewLeaveTaskParam {
    private Long taskId;
    private Boolean agree;
}
