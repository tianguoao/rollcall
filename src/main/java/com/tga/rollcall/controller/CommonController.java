package com.tga.rollcall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dto.AddressParam;
import com.tga.rollcall.service.CommonService;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 公共服务接口
 * @author  Mario 
 * @version 2019年11月7日 下午5:39:25
 * Class: CommonController.java
 */
@RestController
@RequestMapping(RollCallApi.SERVER_NAME+"/common")
@Slf4j
public class CommonController {
    @Autowired
    CommonService commonService;
    
    @PostMapping("/queryBaseAddress")
    public ResultBase<?> queryBaseAddress(@RequestBody(required = true) AddressParam param) {
        return commonService.queryBaseAddress(param);
    }
}
