package com.tga.rollcall.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tga.rollcall.dao.UserMapper;
import com.tga.rollcall.dto.User;
import com.tga.rollcall.dto.User.UserInfo;
import com.tga.rollcall.entity.UserExample;
import com.tga.rollcall.enums.RollcallServerEnum;
import com.tga.rollcall.util.AesUtils;
import com.tga.rollcall.util.ResultBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author  Mario 
 * @version 2019年7月10日 下午4:14:27
 * Class: UserService.java
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    UserMapper userMapper; 
    
    public ResultBase<UserInfo> login(User user) {
        AesUtils aesUtils = new AesUtils();
        String encryptStr = aesUtils.encrypt(user.getPwd());
        com.tga.rollcall.entity.User userdata = userMapper.queryUser(user.getUser(), encryptStr);
        if (null == userdata) {
            return ResultBase.Builder.error();
        }
        // 转换用户信息为 token
        String token = this.getToken(userdata);
        if (StringUtils.isEmpty(token)) {
            return ResultBase.Builder.error();
        }
        UserInfo info = new UserInfo(userdata.getId(), userdata.getUser(), userdata.getUserName(),
                userdata.getEmail(), userdata.getMobile(), token);
        return ResultBase.Builder.success(info);
    }
    
    private String getToken(com.tga.rollcall.entity.User user) {
        String token="";
        token= JWT.create().withAudience(user.getId()+"")
                .sign(Algorithm.HMAC256(user.getPwd()));
        return token;
    }
    public ResultBase<?> register(User user) {
        Integer num = userMapper.queryUserIsExisted(user.getUser());
        Boolean isExisted = num != null && num > 0;
        if (isExisted) {
            return ResultBase.Builder.error(RollcallServerEnum.USER_IS_IS_EXISTED);
        }
        // 加密用户密码
        AesUtils aesUtils = new AesUtils();
        String encryptStr = aesUtils.encrypt(user.getPwd());
        com.tga.rollcall.entity.User record = new com.tga.rollcall.entity.User();
        record.setUser(user.getUser());
        record.setPwd(encryptStr);
        record.setMobile(user.getMobile());
        record.setUser(user.getUser());
        if (StringUtils.isEmpty(user.getUserName())) {
            record.setUserName(UUID.randomUUID().toString().replaceAll("-", ""));
        } else {
            record.setUserName(user.getUserName());
        }
        if (StringUtils.isEmpty(user.getEmail())) {

        } else {

        }
        // 保存用户账号信息
        int count = userMapper.insertSelective(record);
        log.info("user:{} register:{} ", record.getUser(), count);
        return ResultBase.Builder.success();
    }
    
    public com.tga.rollcall.entity.User queryUserInfo(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    
    private String encryptionPwd(User user) {
        return null;
    }
    
    public static void main(String[] args) {
        try {
            String middleKey = "123456";
            String password = "mario";

            // 生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom(middleKey.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            Key key = new SecretKeySpec(byteKey, "AES");


            // 加密
            Cipher cipher;
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(password.getBytes());
            System.out.println("加密后：" + Hex.encodeHexString(result));
            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("解密后：" + new String(result));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
 
    }
}