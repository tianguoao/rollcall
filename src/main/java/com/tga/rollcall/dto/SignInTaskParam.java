package com.tga.rollcall.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 签到任务发布参数类
 * @author  Mario 
 * @version 2019年10月16日 下午7:55:31
 * Class: SignInTaskParam.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInTaskParam {
    /**
     * 签到任务名称
     */
    private String taskName;
    /**
     * 任务开始时间
     */
    private Date startDate;
    /**
     * 任务结束时间
     */
    private Date endDate;
    private Long teacherId;
    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 签到地址
     */
    private String address;
    /**
     * 经度
     */
    private Double latitude;
    /**
     * 纬度
     */
    private Double longitude;
}
