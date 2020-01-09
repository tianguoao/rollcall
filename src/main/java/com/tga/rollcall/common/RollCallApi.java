package com.tga.rollcall.common;

import java.io.IOException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tga.rollcall.util.HttpClientUtil;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年6月17日 下午6:51:07
 * Class: RollCallApi.java
 */
public class RollCallApi {
    public static final String SERVER_NAME = "/rollCall";
    public static final String SERVER_STATE = "/serverState";
    public static final String QUERY_STUDENT_LIST = "/queryStudentList";

    public static void main(String[] args) {
        try {
            for(;;) {
                int a = (int) (Math.random() * 30 + 20);
                Long sleep=Long.valueOf(a*100);
                System.out.println(sleep);
                Thread.sleep(sleep);
                String result = HttpClientUtil.get(
                        "https://m.weibo.cn/api/container/getIndex?page=1&count=3&containerid=1076035069029750");
                JSONObject json = JSONObject.parseObject(result);
                JSONObject data = json.getJSONObject("data");
                JSONArray array = data.getJSONArray("cards");
                System.out.println(array.get(1).toString());
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
