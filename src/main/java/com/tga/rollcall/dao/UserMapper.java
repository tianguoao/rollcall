package com.tga.rollcall.dao;

import com.tga.rollcall.entity.User;
import com.tga.rollcall.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Select("select id, `user`, user_icon, " + 
            "      user_name, age, pwd, " + 
            "      mobile, email, user_type, " + 
            "      group_id, is_deletd, user_status, " + 
            "      face_data  from user where user=#{user} and pwd=#{pwd}  and is_deletd=0")
    @ResultMap("ResultMapWithBLOBs")
    User queryUser(@Param("user") String user, @Param("pwd") String pwd);
    
    /**
     * 获取某个分组下所有的待审核的学生信息
     * @param groupId
     * @return
     */
    @Select("select id, `user`, user_icon,    user_name, age, pwd, "
            + "      mobile, email, user_type,    group_id, is_deletd, user_status, "
            + "      face_data  from user where group_id=#{groupId} and  user_type=2  and user_status=0 and is_deletd=0")
    @ResultMap("BaseResultMap")
    List<User> getStudentRegisterList(@Param("groupId") Long groupId );

    /**
     * 获取待审核的注册用户
     * 
     * @param groupId
     * @param userType
     * @return
     */
    @Select("select id, `user`, user_icon,    user_name, age, pwd, "
            + "      mobile, email, user_type,    group_id, is_deletd, user_status, "
            + "      face_data  from user where group_id=#{groupId} and  user_type=#{userType}  and user_status=0 and is_deletd=0")
    @ResultMap("BaseResultMap")
    List<User> getUserRegisterList(@Param("groupId") Long groupId,
            @Param("userType") Integer userType);
    
    /**
     * 更新某个用户账户状态
     * @param id
     * @param userStatus
     * @return
     */
    @Update("update user set userStatus=#{userStatus}  where id=#{id}")
    int updateUserStatus(@Param("id") Long id,@Param("userStatus") Integer userStatus);
}