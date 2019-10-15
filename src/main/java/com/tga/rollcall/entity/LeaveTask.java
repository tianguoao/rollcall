package com.tga.rollcall.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * leave_task
 * @author 
 */
public class LeaveTask implements Serializable {
    private Long id;

    private Long userId;

    private Long teacherId;

    /**
     * 请假原因
     */
    private String reasonsForLeave;

    /**
     * 是否同意0：no，1：yes
     */
    private Boolean isAgree;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getReasonsForLeave() {
        return reasonsForLeave;
    }

    public void setReasonsForLeave(String reasonsForLeave) {
        this.reasonsForLeave = reasonsForLeave;
    }

    public Boolean getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Boolean isAgree) {
        this.isAgree = isAgree;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
        LeaveTask other = (LeaveTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getReasonsForLeave() == null ? other.getReasonsForLeave() == null : this.getReasonsForLeave().equals(other.getReasonsForLeave()))
            && (this.getIsAgree() == null ? other.getIsAgree() == null : this.getIsAgree().equals(other.getIsAgree()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getReasonsForLeave() == null) ? 0 : getReasonsForLeave().hashCode());
        result = prime * result + ((getIsAgree() == null) ? 0 : getIsAgree().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", reasonsForLeave=").append(reasonsForLeave);
        sb.append(", isAgree=").append(isAgree);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}