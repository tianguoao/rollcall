package com.tga.rollcall.util;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * 
 * 加密工具类
 * @author  Mario 
 * @version 2019年7月19日 下午3:53:11
 * Class: AesUtils.java
 */
public class AesUtils {
    private final String SECRET_KEY = "123456";// 生成key需要的密钥

    public String encrypt(String password) {
        try {
            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            byte[] result = cipher.doFinal(password.getBytes());
            return Base64.encodeBase64String(result);// 通过Base64转码返回
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * encryptedPassword:待解密的密文
     */
    public String decrypt(String encryptedPassword) {
        try {
            // 解密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            // byte[] result = cipher.doFinal(encryptedPassword.getBytes());
            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptedPassword));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private Key key;

    public Key getKey() {
        if (key == null) {
            try {
//                byte[] keyUUID = UUID.randomUUID().toString().getBytes();
                // 生成KEY ,AES 要求密钥长度为 128
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(SECRET_KEY.getBytes());
                keyGenerator.init(128, secureRandom);
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] byteKey = secretKey.getEncoded();

                // 转换KEY
                key = new SecretKeySpec(byteKey, "AES");
                return key;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return key;
        }
    }
    public Key getKey(String pwdKey) {
        if (key == null) {
            try {
                byte[] keyArray = pwdKey.getBytes();
                // 生成KEY ,AES 要求密钥长度为 128
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(keyArray);
                keyGenerator.init(128, secureRandom);
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] byteKey = secretKey.getEncoded();

                // 转换KEY
                key = new SecretKeySpec(byteKey, "AES");
                return key;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return key;
        }
    }
    
    public static void main(String[] args) {
        AesUtils aesUtils=new AesUtils();
        String pwd="mario";
        String encryptStr = aesUtils.encrypt(pwd);
        System.out.println("加密后的结果："+ encryptStr);
        
        String decryptStr = aesUtils.decrypt(encryptStr);
        System.out.println("解密后的结果："+ decryptStr);
    }
}
