package com.tga.rollcall.dao;

import com.tga.rollcall.entity.User;
import com.tga.rollcall.entity.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * UserMapper继承基类
 */
@Repository
public interface UserMapper extends MyBatisBaseDao<User, Long, UserExample> {
    /**
     * 查询当前用户是否已存在
     * 
     * @param user
     * @return
     */
    @Select(" select count(*) from user where user=#{user}")
    Integer queryUserIsExisted(@Param("user") String user);

    /**
     * 根据账号密码查询用户信息
     * 
     * @param user
     * @param pwd
     * @return
     */
    @Select("select id, `user`, user_name, pwd, mobile, group_id, group_name, email,"
            + " is_deletd from user where user=#{user} and pwd=#{pwd}  and is_deletd=0")
    @ResultMap("BaseResultMap")
    User queryUser(@Param("user") String user, @Param("pwd") String pwd);
}