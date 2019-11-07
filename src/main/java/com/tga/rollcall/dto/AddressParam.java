package com.tga.rollcall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 地址查询参数对象
 * @author  Mario 
 * @version 2019年11月7日 下午6:06:02
 * Class: AddressParam.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressParam {
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 地址类型
     */
    private Integer addressType;
}
