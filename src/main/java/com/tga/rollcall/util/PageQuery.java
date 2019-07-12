package com.tga.rollcall.util;

import java.io.Serializable;

/**
 * 
 * 通用分页查询参数对象
 * @author  Mario 
 * @version 2019年3月12日 下午3:42:42
 * Class: PageQuery.java
 */
public class PageQuery<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7963334581177701729L;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页多少条
     */
    private Integer pageSize;
    /**
     * 数据
     */
    private T data;
    
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public PageQuery() {
        super();
    }
    public PageQuery(Integer currentPage, Integer pageSize, T data) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
    }

}
