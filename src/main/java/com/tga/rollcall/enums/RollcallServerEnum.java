package com.tga.rollcall.enums;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年7月8日 下午7:11:29
 * Class: RollcallServerEnum.java
 */
public enum RollcallServerEnum {
    SUCCESS(0, "success", "SUCCESS", "请求接口处理正常"), 
    ERROR(1, "server error", "ERROR", "服务器内部处理异常"),
    PARAM_NOT_NULL_ERROR(2, "参数不能为空", "FAIL", "参数不能为空"),
    ;
    private Integer code;
    private String message;
    private String ret;
    private String remark;

    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }


    public String getRemark() {
        return remark;
    }

    public String getRet() {
        return ret;
    }


    RollcallServerEnum(Integer code, String message, String ret, String remark) {
        this.code = code;
        this.message = message;
        this.ret = ret;
        this.remark = remark;
    }

    /**
     * 通过状态值获取RollcallServerEnum
     */
    public static RollcallServerEnum getMgtException(int code) {
        for (RollcallServerEnum item : RollcallServerEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        // 默认
        return RollcallServerEnum.ERROR;
    }

    /**
     * 勿删！！！！ 生成wiki 图表文档
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("||错误码||" + "||中文描述||" + "||英文提示||" + "||级别||");
        for (RollcallServerEnum data : RollcallServerEnum.values()) {
            System.out.println("||" + data.getCode() + "||" + "||" + data.getRemark() + "||" + "||"+ data.getMessage() + "||" + "||" + data.getRet() + "||");
        }
    }
    
}

