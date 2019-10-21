package com.tga.rollcall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年7月10日 下午4:10:58
 * Class: User.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String user;
    private String userName;
    private String pwd;
    private String email;
    private String mobile;
    private Integer age;
    /**
     * 用户类型 0：管理员 ，1：老师，2：学生
     */
    private  Integer userType;
    private  Integer groupId;
    /**
     * 人脸数据(ps:注册采集人脸信息  后续签到拿第一次人脸数据做对比)
     */
    private String faceData;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String user;
        private String userName;
        private String email;
        private String mobile;
        private String token;
    }
}
