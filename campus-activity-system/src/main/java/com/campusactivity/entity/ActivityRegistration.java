package com.campusactivity.entity;

import java.util.Date;

/**
 * 活动报名实体类
 */
public class ActivityRegistration {
    private Integer registrationId;     // 报名记录ID
    private Integer userId;              // 用户ID
    private Integer activityId;          // 活动ID
    private Date registrationTime;       // 报名时间
    private String status;               // 报名状态：pending(待审核)、approved(已通过)、rejected(已拒绝)
    private String remark;               // 备注信息
    
    // 关联对象（用于查询展示）
    private User user;                   // 报名用户
    private Activity activity;           // 报名活动

    public ActivityRegistration() {
    }

    public ActivityRegistration(Integer userId, Integer activityId) {
        this.userId = userId;
        this.activityId = activityId;
        this.status = "pending";
    }

    // Getters and Setters
    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ActivityRegistration{" +
                "registrationId=" + registrationId +
                ", userId=" + userId +
                ", activityId=" + activityId +
                ", registrationTime=" + registrationTime +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
