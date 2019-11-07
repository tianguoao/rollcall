package com.tga.rollcall.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年11月7日 下午3:37:11
 * Class: Address.java
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String parentId;
    private String addressId;
    private String addressName;
    
    public static void main(String[] args) {
        try {
            List<Address> datas = Lists.newArrayList();
            File file = new File("C:\\Users\\45759\\Desktop\\address.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
                System.out.println(s);
                //'110000-北京市'=>110100,
                String parentId = s.substring(1, s.indexOf("-"));
                String code = s.substring( s.indexOf("=>"),s.length()-1);
                String name = s.substring(s.indexOf("-")+1,s.indexOf("'=>"));
                datas.add(new Address(parentId,code.replace("=>", ""),name));
            }
            bReader.close();
            System.out.println(JSONObject.toJSONString(datas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //省数据
    public static void getCityData() {
        try {
            List<Address> datas = Lists.newArrayList();
            File file = new File("C:\\Users\\45759\\Desktop\\address.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
                System.out.println(s);
                String code = s.substring(0, s.indexOf("=>"));
                String name = s.substring(s.indexOf("=>"), s.length());
                datas.add(new Address(null,name.replace("=>", "").replace(",", ""),code.replace("'", "")));
            }
            bReader.close();
            System.out.println(JSONObject.toJSONString(datas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //区县
    public static void quxian() {
        
    }

}
