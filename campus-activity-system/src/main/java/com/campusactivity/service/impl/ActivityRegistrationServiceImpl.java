package com.campusactivity.service.impl;

import com.campusactivity.entity.ActivityRegistration;
import com.campusactivity.mapper.ActivityRegistrationMapper;
import com.campusactivity.service.ActivityRegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动报名服务实现类
 */
@Service
public class ActivityRegistrationServiceImpl implements ActivityRegistrationService {
    
    @Resource
    private ActivityRegistrationMapper registrationMapper;
    
    @Override
    @Transactional
    public boolean register(Integer userId, Integer activityId, String remark) {
        // 检查是否已报名
        ActivityRegistration existing = registrationMapper.findByUserAndActivity(userId, activityId);
        if (existing != null) {
            return false; // 已报名，不能重复报名
        }
        
        // 创建报名记录
        ActivityRegistration registration = new ActivityRegistration(userId, activityId);
        registration.setRemark(remark);
        
        return registrationMapper.insert(registration) > 0;
    }
    
    @Override
    @Transactional
    public boolean cancelRegistration(Integer registrationId) {
        return registrationMapper.deleteById(registrationId) > 0;
    }
    
    @Override
    @Transactional
    public boolean approveRegistration(Integer registrationId) {
        return registrationMapper.updateStatus(registrationId, "approved") > 0;
    }
    
    @Override
    @Transactional
    public boolean rejectRegistration(Integer registrationId) {
        return registrationMapper.updateStatus(registrationId, "rejected") > 0;
    }
    
    @Override
    public boolean isRegistered(Integer userId, Integer activityId) {
        ActivityRegistration registration = registrationMapper.findByUserAndActivity(userId, activityId);
        return registration != null;
    }
    
    @Override
    public List<ActivityRegistration> getUserRegistrations(Integer userId) {
        return registrationMapper.findByUserId(userId);
    }
    
    @Override
    public List<ActivityRegistration> getActivityRegistrations(Integer activityId) {
        return registrationMapper.findByActivityId(activityId);
    }
    
    @Override
    public List<ActivityRegistration> getActivityRegistrationsByStatus(Integer activityId, String status) {
        return registrationMapper.findByActivityIdAndStatus(activityId, status);
    }
    
    @Override
    public List<ActivityRegistration> getAllRegistrations() {
        return registrationMapper.findAll();
    }
    
    @Override
    public List<ActivityRegistration> getAllRegistrationsByStatus(String status) {
        return registrationMapper.findAllByStatus(status);
    }
    
    @Override
    public int getRegistrationCount(Integer activityId) {
        return registrationMapper.countByActivityId(activityId);
    }
    
    @Override
    public ActivityRegistration getById(Integer registrationId) {
        return registrationMapper.findById(registrationId);
    }
}
