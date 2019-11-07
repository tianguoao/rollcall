package com.tga.rollcall.enums;
/**
 * 
 * 地址类型枚举
 * @author  Mario 
 * @version 2019年11月7日 下午5:56:54
 * Class: AddressTypeEnum.java
 */
public enum AddressTypeEnum {
    /**
     * 省
     */
    PROVINCE(1, "省份"),
    /**
     * 市
     */
    CITY(2, "市"),
    /**
     * 区县
     */
    DISTRICT(3, "区县"),;
    private Integer code;
    private String type;

    private AddressTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }
    
    public String getType() {
        return type;
    }
    
    public static String getTypeStr(Integer code) {
        for (AddressTypeEnum a : AddressTypeEnum.values()) {
            if (a.getCode().equals(code)) {
                return a.getType();
            }
        }
        return null;
    }
}
