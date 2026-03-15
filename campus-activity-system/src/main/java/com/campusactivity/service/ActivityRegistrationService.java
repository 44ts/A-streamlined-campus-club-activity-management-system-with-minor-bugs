package com.campusactivity.service;

import com.campusactivity.entity.ActivityRegistration;

import java.util.List;

/**
 * 活动报名服务接口
 */
public interface ActivityRegistrationService {
    
    /**
     * 用户报名活动
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param remark 备注
     * @return 是否成功
     */
    boolean register(Integer userId, Integer activityId, String remark);
    
    /**
     * 取消报名
     * @param registrationId 报名记录ID
     * @return 是否成功
     */
    boolean cancelRegistration(Integer registrationId);
    
    /**
     * 审核报名（通过）
     * @param registrationId 报名记录ID
     * @return 是否成功
     */
    boolean approveRegistration(Integer registrationId);
    
    /**
     * 审核报名（拒绝）
     * @param registrationId 报名记录ID
     * @return 是否成功
     */
    boolean rejectRegistration(Integer registrationId);
    
    /**
     * 检查用户是否已报名
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 是否已报名
     */
    boolean isRegistered(Integer userId, Integer activityId);
    
    /**
     * 获取用户的报名记录
     * @param userId 用户ID
     * @return 报名记录列表
     */
    List<ActivityRegistration> getUserRegistrations(Integer userId);
    
    /**
     * 获取活动的报名记录
     * @param activityId 活动ID
     * @return 报名记录列表
     */
    List<ActivityRegistration> getActivityRegistrations(Integer activityId);
    
    /**
     * 获取活动指定状态的报名记录
     * @param activityId 活动ID
     * @param status 状态
     * @return 报名记录列表
     */
    List<ActivityRegistration> getActivityRegistrationsByStatus(Integer activityId, String status);
    
    /**
     * 获取所有报名记录
     * @return 报名记录列表
     */
    List<ActivityRegistration> getAllRegistrations();
    
    /**
     * 获取所有指定状态的报名记录
     * @param status 状态
     * @return 报名记录列表
     */
    List<ActivityRegistration> getAllRegistrationsByStatus(String status);
    
    /**
     * 获取活动报名人数
     * @param activityId 活动ID
     * @return 报名人数
     */
    int getRegistrationCount(Integer activityId);
    
    /**
     * 根据ID获取报名记录
     * @param registrationId 报名记录ID
     * @return 报名记录
     */
    ActivityRegistration getById(Integer registrationId);
}
