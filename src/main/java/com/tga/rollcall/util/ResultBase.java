package com.tga.rollcall.util;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.alibaba.fastjson.JSONObject;
import com.tga.rollcall.enums.RollcallServerEnum;
import lombok.Data;

/**
 * 通用服务返回结果类 可用于 service ,controller
 * 
 * @author tianguoao
 *
 * @param <T>
 */
@Data
public class ResultBase<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5735948842180184427L;
    /**
     * success/fail/error
     */
    private String ret;
    /**
     * 0成功 ,非0异常
     */
    private int code;
    /**
     * 具体错误描述or成功描述
     */
    private String message = StringUtils.EMPTY;
    /**
     * 存放业务数据
     */
    private T data;

    /**
     * 构造 成功 返回结果的函数
     */
    public ResultBase() {
        this.init(RollcallServerEnum.SUCCESS);
    }

    public void init(RollcallServerEnum RollcallServerEnum) {
        this.code = RollcallServerEnum.getCode();
        this.message = RollcallServerEnum.getMessage();
        this.ret = RollcallServerEnum.getRet();
    }

    /**
     * 默认的服务异常
     */
    public void initError() {
        this.code = RollcallServerEnum.ERROR.getCode();
        this.message = RollcallServerEnum.ERROR.getMessage();
        this.ret = RollcallServerEnum.ERROR.getRet();
    }

    public void init(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public void initSuccess(int code, String msg) {
        this.code = code;
        this.message = msg;
        this.ret = RollcallServerEnum.SUCCESS.getRet();
    }

    public void initError(int code, String msg) {
        this.code = code;
        this.message = msg;
        this.ret = RollcallServerEnum.ERROR.getRet();
    }

    public void init(RollcallServerEnum rollcallServerEnum, T data) {
        this.code = rollcallServerEnum.getCode();
        this.message = rollcallServerEnum.getMessage();
        this.ret = rollcallServerEnum.getRet();
        this.data = data;
    }

    public void initMessage(String msg) {
        this.message = msg;
    }


    public ResultBase(T data) {
        this.data = data;
    }

    public ResultBase(RollcallServerEnum rollcallServerEnum, T data) {
        this.code = rollcallServerEnum.getCode();
        this.message = rollcallServerEnum.getMessage();
        this.ret = rollcallServerEnum.getRet();
        this.data = data;
    }

    /**
     * 判断调用后的成功状态
     * 
     * @return
     */
    public Boolean successState() {
        return RollcallServerEnum.SUCCESS.getCode().equals(this.code);
    }

    public void init(ResultBase<?> result) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.ret = result.getRet();
    }

    /**
     * 构建返回对象
     * 
     * @author tianguoao
     *
     */
    public static class Builder {
        /**
         * 成功
         * 
         * @param data 返回的对象值
         * @return
         */
        public static <T> ResultBase<T> success(T data) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(RollcallServerEnum.SUCCESS, data);
            return resultBase;
        }

        public static <T> ResultBase<T> success() {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(RollcallServerEnum.SUCCESS);
            return resultBase;
        }

        public static <T> ResultBase<T> success(T data, String msg) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(RollcallServerEnum.SUCCESS.getCode(), msg, data);
            return resultBase;
        }
        /**
         * 失败
         * 
         * @param data 返回的对象值
         * @return
         */
        public static <T> ResultBase<T> error() {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(RollcallServerEnum.ERROR);
            return resultBase;
        }
        
        public static <T> ResultBase<T> error(T data) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(RollcallServerEnum.ERROR);
            resultBase.setData(data);
            return resultBase;
        }
        
        public static <T> ResultBase<T> error(RollcallServerEnum rollcallServerEnum) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(rollcallServerEnum);
            return resultBase;
        }

        public static <T> ResultBase<T> error(RollcallServerEnum rollcallServerEnum,
                T data) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(rollcallServerEnum, data);
            return resultBase;
        }
        public static <T> ResultBase<T> initError(RollcallServerEnum rollcallServerEnum,String errorMsg) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.init(rollcallServerEnum);
            resultBase.initMessage(errorMsg);
            return resultBase;
        }
        
        public static <T> ResultBase<T> initError(String errorMsg) {
            ResultBase<T> resultBase = new ResultBase<T>();
            resultBase.initError(RollcallServerEnum.ERROR.getCode(), errorMsg);
            return resultBase;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

}
