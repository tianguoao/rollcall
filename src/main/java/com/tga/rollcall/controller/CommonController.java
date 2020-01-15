package com.tga.rollcall.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dto.AddressParam;
import com.tga.rollcall.service.AsyncService;
import com.tga.rollcall.service.AsyncXPPService;
import com.tga.rollcall.service.CommonService;
import com.tga.rollcall.util.HttpClientUtil;
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
//    private static ExecutorService executor =
//            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    @Autowired
    CommonService commonService;
    @Autowired
    AsyncService asyncService;
    @Autowired
    AsyncXPPService asyncXPPService;
    
    @PostMapping("/queryBaseAddress")
    public ResultBase<?> queryBaseAddress(@RequestBody(required = true) AddressParam param) {
        return commonService.queryBaseAddress(param);
    }
    
    @GetMapping("/sina")
    public ResultBase<?> sina(@RequestParam(value = "stop", defaultValue = "") Integer stop) {
        // executor.execute(new RegisterExtroOptionRunnable(appHeader, acct, request, clUserService,
        // appTair, accessKey,
        // iUserDeviceService, LOG, reqDTO, this));
        asyncService.run(stop);
        asyncXPPService.run(stop);
        return ResultBase.Builder.success();
    }
 
}
