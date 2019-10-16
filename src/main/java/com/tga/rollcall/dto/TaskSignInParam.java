package com.tga.rollcall.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年10月16日 下午8:23:37
 * Class: TaskSignInParam.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSignInParam {
    private Long studentId;
    /**
     * 经度
     */
    private Double latitude;
    /**
     * 纬度
     */
    private Double longitude;
    /**
     * 人脸数据
     */
    private String faceData;
    
    private Long groupId;
}
