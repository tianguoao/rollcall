package com.tga.rollcall.util;

import lombok.Data;

/**
 * 
 * 通用分页返回数据对象
 * @author  Mario 
 * @version 2019年3月11日 上午11:53:58
 * Class: PageResult.java
 */
@Data
public class PageResult<T> {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页多少条
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer count;
    /**
     * 扩展字段
     */
    private Object extend;
    /**
     * 分页后的数据
     */
    private T pageData;


    public PageResult(Integer currentPage, Integer pageSize, Integer count, T pageData) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
        this.pageData = pageData;
    }
    
    public PageResult(Integer currentPage, Integer pageSize, Integer count, Object extend,
            T pageData) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
        this.extend = extend;
        this.pageData = pageData;
    }

    public PageResult() {
        super();
    }

    /**
     * 构建返回分页对象
     * @author  
     *
     */
    public static class Builder {
        /**
         * 构建分页对象
         * @param data
         * @param currentPage
         * @param pageSize
         * @param count
         * @return
         */
        public static <T> PageResult<T> result(T data, Integer currentPage, Integer pageSize,
                Integer count) {
            PageResult<T> pageData = new PageResult<T>(currentPage, pageSize, count, data);
            return pageData;
        }

        public static <T> PageResult<T> result(T data, Object extend, Integer currentPage,
                Integer pageSize, Integer count) {
            PageResult<T> pageData = new PageResult<T>(currentPage, pageSize, count, extend, data);
            return pageData;
        }
    }
}
