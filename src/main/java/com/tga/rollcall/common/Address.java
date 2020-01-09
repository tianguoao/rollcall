package com.tga.rollcall.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
    private List<Address> nextNode;
    
    public static void main(String[] args) {
        try {
            List<Address> datas = Lists.newArrayList();
            File file = new File("C:\\Users\\45759\\Desktop\\地址json\\省.json");
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s);// 将读取的字符串添加换行符后累加存放在缓存中
            }
            bReader.close();
            datas.addAll(JSONObject.parseArray(sb.toString(), Address.class));
//            System.out.println(JSONObject.toJSONString(datas,SerializerFeature.WriteMapNullValue));
            List<Address> shi = a();
            List<Address> quxian = b();
            for (Address sheng : datas) {
                List<Address> two = Lists.newArrayList();
                for (Address si : shi) {
                    if (sheng.getAddressId().equals(si.getParentId())) {
                        two.add(si);
                    }
                }
                sheng.setNextNode(two);
                for (Address qx : sheng.getNextNode()) {
                    List<Address> six = Lists.newArrayList();
                    for (Address aa : quxian) {
                        if (aa.getParentId().equals(qx.getAddressId())) {
                            six.add(aa);
                        }
                        qx.setNextNode(six);
                    }
                }
            }
//            datas.forEach(sheng -> {
//                List<Address> two=Lists.newArrayList();
//                for(Address ss:shi) {
//                    if(ss.getAddressId().equals(0)) {
//                        
//                    }
//                }
//                sheng.setNextNode(two);
//            });
            
            System.out.println(JSONObject.toJSONString(datas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Address> a() {
        List<Address> datas = Lists.newArrayList();
        try {
            File file = new File("C:\\Users\\45759\\Desktop\\地址json\\市.json");
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s);// 将读取的字符串添加换行符后累加存放在缓存中
                continue;
            }
            bReader.close();
            datas.addAll(JSONObject.parseArray(sb.toString(), Address.class));
            System.out.println(JSONObject.toJSONString(datas,SerializerFeature.WriteMapNullValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    
    public static List<Address> b() {
        List<Address> datas = Lists.newArrayList();
        try {
            File file = new File("C:\\Users\\45759\\Desktop\\地址json\\区县.json");
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s);// 将读取的字符串添加换行符后累加存放在缓存中
            }
            bReader.close();
            datas.addAll(JSONObject.parseArray(sb.toString(), Address.class));
            System.out.println(JSONObject.toJSONString(datas,SerializerFeature.WriteMapNullValue));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    
    public static void get() {
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
//                datas.add(new Address(parentId,code.replace("=>", ""),name));
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
//                datas.add(new Address(null,name.replace("=>", "").replace(",", ""),code.replace("'", "")));
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
