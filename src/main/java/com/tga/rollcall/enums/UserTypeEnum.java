package com.tga.rollcall.enums;
/**
 * 
 * 用户类型枚举类
 * @author  Mario
 * @version 2019年10月15日 下午7:12:12
 * Class: UserTypeEnum.java
 */
public enum UserTypeEnum {
    ADMIN(0, "管理员"),
    TEACHER(1, "老师"),
    STUDENT(2, "学生"),
    ;
    private Integer code;
    private String name;

    private UserTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
