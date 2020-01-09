package com.tga.rollcall.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
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
    @Autowired
    CommonService commonService;
    private static Integer stop=1; 
    @PostMapping("/queryBaseAddress")
    public ResultBase<?> queryBaseAddress(@RequestBody(required = true) AddressParam param) {
        return commonService.queryBaseAddress(param);
    }
    
    @PostMapping("/sina")
    public ResultBase<?> sina(@RequestParam(value="stop",defaultValue="")Integer stop) {
        run(stop);
        return ResultBase.Builder.success();
    }
     
    @Async
    public void run(Integer stop) {
        try {
            for (;;) {
                if (null != stop && 1 == stop) {
                    stop = 1;
                    log.warn("结束微博检索脚本！");
                    break;
                }
                int a = (int) (Math.random() * 30 + 20);
                Long sleep = Long.valueOf(a * 1000);
                log.info("检索最新微博~  {}ms", sleep);
                Thread.sleep(sleep);
                Date now = new Date();
                if (now.getHours() >= 1 && now.getHours() <= 6) {
                    log.info("不到指定脚本执行时间！暂停任务发送！脚本暂停时间为每天凌晨1点  至 早上6点");
                    continue;
                }
                String result = HttpClientUtil.get(
                        "https://m.weibo.cn/api/container/getIndex?page=1&count=3&containerid=1076035069029750");
                JSONObject json = JSONObject.parseObject(result);
                JSONObject data = json.getJSONObject("data");
                JSONArray array = data.getJSONArray("cards");
                JSONObject mblog =
                        JSONObject.parseObject(array.get(1).toString()).getJSONObject("mblog");
                String date = mblog.getString("created_at");
                 if(!"刚刚".equals(date)||"1分钟前".equals(date)) {
                 continue;
                 }
                String coreText = null;
                String rawText = mblog.getString("raw_text");
                if (!StringUtils.isEmpty(rawText)) {
                    coreText = rawText;
                } else {
                    coreText = mblog.getString("text");
                }
                String url = getUrl(coreText);
                String img = mblog.getString("original_pic");
//                sendMsg(date, coreText, img, url);
                sendAll(date, coreText, img, url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sendMsg(String date, String text, String img, String curl) {
        try {
            log.info("检测到博主最新微博！ 微博内容：{}", text);
            StringBuffer sb = new StringBuffer();
            String url =
                    "https://sc.ftqq.com/SCU41004T84386e4546e928ce0d2cec29e27aae985c3d8fd4065f8.send";
            sb.append("#### 正文：\n");
            sb.append("--- \n");
            sb.append("<html>\n   %s \n </html> \n");
            if (!StringUtils.isEmpty(curl)) {
                sb.append("--- \n");
                sb.append("[网页链接](" + curl + ") \n");
                sb.append("[" + curl + "]  \n");
            }
            sb.append("--- \n");
            sb.append("###### 时间：%s  \n ");
            sb.append("--- \n");
            if (!StringUtils.isEmpty(img)) {
                sb.append("![image](%s) \n");
            }
            String param = sb.toString();
            param = String.format(param, delHTMLTag(text), date, img);
            Map<String, String> data = Maps.newHashMap();
            data.put("text", "薅羊毛的大队长-最新微博");
            data.put("desp", param);
            String result = HttpClientUtil.postForm(url, data);
            log.info("已发送通知消息！！！ send msg result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendAll(String date, String text, String img, String curl) {
        try {
            log.info("检测到博主最新微博！ 微博内容：{}", text);
            StringBuffer sb = new StringBuffer();
            String url = "http://push.ijingniu.cn/send";
            sb.append("微博内容:\n" + 
                    "------------------------------ \n" + 
                    "%s \n" + 
                    "网页链接:[%s ]\n" + 
                    "------------------------------  \n" + 
                    "时间：%s    \n" + 
                    "------------------------------ \n" + 
                    "\n" + 
                    "![image](%s) \n" + 
                    "图片链接：%s \n" + 
                    "");
            String param = sb.toString();
            param = String.format(param, delHTMLTag(text),curl, date, img);
            Map<String, String> data2 = Maps.newHashMap();
            data2.put("key", "f2e8f1990c50438e897010b32474ecc4");
            data2.put("head", "薅羊毛的大队长-最新微博");
            data2.put("body", param);
            String result = HttpClientUtil.postForm(url, data2);
            log.info("已发送通知消息！！！ send msg result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String getUrl(String html) {
        Document document = Jsoup.parse(html);
        Element link = document.select("a").first();
        return link.attr("data-url");
    }
    public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤  
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        return htmlStr.trim(); //返回文本字符串 
    } 
    public static void main(String[] args) {
        String html="蒙牛纯甄小蛮腰红西柚口味酸牛奶230g*10瓶/整箱，少数地区2件5折，拍3件叠加199-30购物券，74.85元\n" + 
                "<a data-url=\"http://t.cn/Aispsfcf\" \n" + 
                "target=\"_blank\" \n" + 
                "href=\"https://weibo.cn/sinaurl/blocked020c1b4b?luicode=10000011&lfid=1076035069029750&u=http%3A%2F%2Ftb.sinayouhuiquan.cn%2F0pnKoH\" class=\"\">\n" + 
                "<span class='url-icon'><img style='width: 1rem;height: 1rem' src='//h5.sinaimg.cn/upload/2015/09/25/3/timeline_card_small_web_default.png'\n" + 
                "></span><span class=\"surl-text\">网页链接</span></a> ";
        System.out.println(delHTMLTag(html));
        Document document = Jsoup.parse(html);
        Element link = document.select("a").first();
        System.out.println(link.attr("data-url"));
    }
}
