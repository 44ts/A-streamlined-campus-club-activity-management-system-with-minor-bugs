package com.campusactivity.mapper;

import com.campusactivity.entity.Activity;
import java.util.List;

public interface ActivityMapper {
    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectAll();

    List<Activity> selectByStatus(String status);

    int deleteByPrimaryKey(Integer activityId);

    List<Activity> selectByOrganizerId(Integer organizerId);
}