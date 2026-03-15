package com.campusactivity.service.impl;

import com.campusactivity.entity.Activity;
import com.campusactivity.mapper.ActivityMapper;
import com.campusactivity.service.ActivityService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public int save(Activity activity) {
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        if (activity.getStatus() == null) {
            activity.setStatus("draft");
        }
        return activityMapper.insert(activity);
    }

    @Override
    public int update(Activity activity) {
        activity.setUpdateTime(new Date());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public int delete(Integer activityId) {
        return activityMapper.deleteByPrimaryKey(activityId);
    }

    @Override
    public Activity getById(Integer activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public List<Activity> getAll() {
        return activityMapper.selectAll();
    }

    @Override
    public List<Activity> getByStatus(String status) {
        return activityMapper.selectByStatus(status);
    }

    @Override
    public List<Activity> getByOrganizerId(Integer organizerId) {
        return activityMapper.selectByOrganizerId(organizerId);
    }

    @Override
    public int approve(Integer activityId) {
        Activity activity = new Activity();
        activity.setActivityId(activityId);
        activity.setStatus("published");
        activity.setUpdateTime(new Date());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    public int reject(Integer activityId) {
        Activity activity = new Activity();
        activity.setActivityId(activityId);
        activity.setStatus("rejected");
        activity.setUpdateTime(new Date());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }
}