package com.tga.rollcall.entity;

import java.io.Serializable;
/**
 * user
 * @author 
 */
public class User implements Serializable {
    private Long id;

    /**
     * 用户登录名
     */
    private String user;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    private String pwd;

    private String mobile;

    private String email;

    /**
     * 0管理员,1老师,2学生
     */
    private Integer userType;

    /**
     * 所属分组
     */
    private Long groupId;

    /**
     * 账号是否已停用0;未停用 1:已停用
     */
    private Boolean isDeletd;

    /**
     * 审核状态 0待审核，1已通过
     */
    private Integer userStatus;

    /**
     * 人像数据
     */
    private String faceData;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getIsDeletd() {
        return isDeletd;
    }

    public void setIsDeletd(Boolean isDeletd) {
        this.isDeletd = isDeletd;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getUserIcon() == null ? other.getUserIcon() == null : this.getUserIcon().equals(other.getUserIcon()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getIsDeletd() == null ? other.getIsDeletd() == null : this.getIsDeletd().equals(other.getIsDeletd()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getFaceData() == null ? other.getFaceData() == null : this.getFaceData().equals(other.getFaceData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getUserIcon() == null) ? 0 : getUserIcon().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getIsDeletd() == null) ? 0 : getIsDeletd().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getFaceData() == null) ? 0 : getFaceData().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", userIcon=").append(userIcon);
        sb.append(", userName=").append(userName);
        sb.append(", age=").append(age);
        sb.append(", pwd=").append(pwd);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", userType=").append(userType);
        sb.append(", groupId=").append(groupId);
        sb.append(", isDeletd=").append(isDeletd);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", faceData=").append(faceData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}