package com.tga.rollcall.service;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.tga.rollcall.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * 
 * @author  Mario 
 * @version 2020年1月9日 下午7:51:27
 * Class: AsyncService.java
 */
@Service
@Slf4j
public class AsyncWDService {
    private static Integer open = 1;

    @Async
    public void run(Integer stop) {
        try {
            for (;;) {
                if (null != stop) {
                    open = stop;
                }
                if (0 == open) {
                    open = 0;
                    log.warn("结束微博检索脚本！");
                    break;
                }
                int a = (int) (Math.random() * 30 + 20);
                Long sleep = Long.valueOf(a * 1000);
                log.info("检索【无敌】最新微博~  {}ms", sleep);
                Thread.sleep(sleep);
                Date now = new Date();
                if (now.getHours() >= 1 && now.getHours() <= 6) {
                    log.info("不到指定脚本执行时间！暂停任务发送！脚本暂停时间为每天凌晨1点  至 早上6点");
                    continue;
                }
                String result = HttpClientUtil.get(
                        "https://m.weibo.cn/api/container/getIndex?page=1&count=3&containerid=1076035744916923");
                JSONObject json = JSONObject.parseObject(result);
                JSONObject data = json.getJSONObject("data");
                JSONArray array = data.getJSONArray("cards");
                JSONObject mblog =
                        JSONObject.parseObject(array.get(1).toString()).getJSONObject("mblog");
                String date = mblog.getString("created_at");
                if (!"刚刚".equals(date) || "1分钟前".equals(date)) {
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
            log.error("sina job  error:{}",e);
            run(null);
        }
    }

    private void sendAll(String date, String text, String img, String curl) {
        try {
            log.info("检测到博主最新微博！ 微博内容：{}", text);
            StringBuffer sb = new StringBuffer();
            String url = "http://push.ijingniu.cn/send";
            sb.append("【无敌羊毛】\n");
            sb.append("微博内容:\n" + 
                    "------------------------------ \n" + 
                    delHTMLTag(text)+" \n" + 
                    "网页链接:["+curl+" ]\n" + 
                    "------------------------------  \n" + 
                    "时间："+date+"    \n" + 
                    "------------------------------ \n"
                   );
            sb.append("博主微博地址： \n");
            sb.append(
                    "https://m.weibo.cn/u/5744916923\n");
            if (!StringUtils.isEmpty(img)) {
                sb.append("![avatar][base64str] \n" + "图片链接： \n " + img + " \n");
                sb.append("[base64str]:" + getImageBase64FromUrl(img));
            }
            String param = sb.toString();
            Map<String, String> data2 = Maps.newHashMap();
            data2.put("key", "f2e8f1990c50438e897010b32474ecc4");
            data2.put("head", "无敌-最新微博");
            data2.put("body", param);
            String result = HttpClientUtil.postForm(url, data2);
            log.info("已发送通知消息！！！ send msg result:{}", result);
            Thread.sleep(1500L);
        } catch (Exception e) {
            log.error("sendAll error:{}",e);
        }
    }
    
    private String getUrl(String html) {
        Document document = Jsoup.parse(html);
        if (null == document) {
            return null;
        }
        Element link = document.select("a").first();
        if (null != link) {
            return link.attr("data-url");
        }
        return null;
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
    
    public String getImageBase64FromUrl(String imgURL) {
        BufferedImage image = null;
        URL url;
        try {
            url = new URL(imgURL);
            image = ImageIO.read(url);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            Base64.Encoder encoder1 = Base64.getEncoder();
            String imgStr = encoder1.encodeToString(outputStream.toByteArray());
            System.out.println("data:image/jpeg;base64," + imgStr);
            return "data:image/jpeg;base64," + imgStr;
        } catch (Exception e) {
            log.error("base64   Encoder  error:{}", e);
        }
        return null;
    }
}
