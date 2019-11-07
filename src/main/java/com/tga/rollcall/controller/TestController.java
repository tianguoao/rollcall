package com.tga.rollcall.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tga.rollcall.annotations.PrintParams;
import com.tga.rollcall.common.RollCallApi;
import com.tga.rollcall.dao.AddressMapper;
import com.tga.rollcall.dto.User;
import com.tga.rollcall.entity.Address;
import com.tga.rollcall.entity.AddressExample;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年11月4日 下午6:55:06
 * Class: TestController.java
 */
@RestController
@RequestMapping(RollCallApi.SERVER_NAME+"/test")
@Slf4j
public class TestController {
    @Autowired
    AddressMapper addressMapper;
    
    @PrintParams
    @PostMapping("/excel")
    public void getStudentRegisterList(HttpServletRequest request, HttpServletResponse response) {
        List<Model> m=Lists.newArrayList();
        int i = 0;
        for (;;) {
            if (i == 10) {
                break;
            }
            m.add(new Model(i, "小王"+i));
            i++;
        }
        try {
            createExcelStreamMutilByEaysExcel(response, m, Model.class, ExcelTypeEnum.XLSX, "测试");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void createExcelStreamMutilByEaysExcel(HttpServletResponse response,
            List<? extends BaseRowModel> sheetNameAndDateList, Class<? extends BaseRowModel> clazz,
            ExcelTypeEnum type, String fileName) throws UnsupportedEncodingException {
        if (null == type) {
            return;
        }
        try {
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + fileName + type.getValue());
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, type, true);
            Sheet sheet1 = new Sheet(1, 0, clazz);
            sheet1.setSheetName("one");
            writer.write(sheetNameAndDateList, sheet1);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @PostMapping("/importAddress")
    public ResultBase<?> importAddress(@RequestParam MultipartFile file,
            @RequestParam(defaultValue = "1") String type, HttpServletRequest request) {
        try {
            InputStream inputStream = file.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s);// 将读取的字符串添加换行符后累加存放在缓存中
            }
            bufferedReader.close();
            List<com.tga.rollcall.common.Address> address =
                    JSONObject.parseArray(sb.toString(), com.tga.rollcall.common.Address.class);
            address.forEach(data -> {
                Address record = new Address();
                record.setAddressType(Integer.valueOf(type));
                if(null!=data.getParentId()) {
                    record.setParentId(Long.valueOf(data.getParentId()));
                }
                record.setCityId(Long.valueOf(data.getAddressId()));
                record.setAddressName(data.getAddressName());
                log.info("*************  address:{}", record.toString());
                addressMapper.insertSelective(record);
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultBase.Builder.success();
    }
}
