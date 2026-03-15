package com.campusactivity.service;

import com.campusactivity.entity.Activity;
import java.util.List;

public interface ActivityService {
    int save(Activity activity);

    int update(Activity activity);

    int delete(Integer activityId);

    Activity getById(Integer activityId);

    List<Activity> getAll();

    List<Activity> getByStatus(String status);

    List<Activity> getByOrganizerId(Integer organizerId);

    int approve(Integer activityId);

    int reject(Integer activityId);
}