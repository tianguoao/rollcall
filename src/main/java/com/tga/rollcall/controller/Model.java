package com.tga.rollcall.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年11月4日 下午7:00:30
 * Class: Model.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model extends BaseRowModel {
    @ExcelProperty(value = "id", index = 0)
    private Integer id;
    @ExcelProperty(value = "姓名", index = 0)
    private String name;
}
