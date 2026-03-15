package com.campusactivity.mapper;

import com.campusactivity.entity.ActivityRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动报名Mapper接口
 */
@Mapper
public interface ActivityRegistrationMapper {
    
    /**
     * 插入报名记录
     */
    int insert(ActivityRegistration registration);
    
    /**
     * 根据ID删除报名记录
     */
    int deleteById(Integer registrationId);
    
    /**
     * 更新报名状态
     */
    int updateStatus(@Param("registrationId") Integer registrationId, 
                     @Param("status") String status);
    
    /**
     * 根据ID查询报名记录
     */
    ActivityRegistration findById(Integer registrationId);
    
    /**
     * 查询用户是否已报名某活动
     */
    ActivityRegistration findByUserAndActivity(@Param("userId") Integer userId, 
                                                @Param("activityId") Integer activityId);
    
    /**
     * 查询用户的所有报名记录
     */
    List<ActivityRegistration> findByUserId(Integer userId);
    
    /**
     * 查询某活动的所有报名记录
     */
    List<ActivityRegistration> findByActivityId(Integer activityId);
    
    /**
     * 根据状态查询某活动的报名记录
     */
    List<ActivityRegistration> findByActivityIdAndStatus(@Param("activityId") Integer activityId, 
                                                          @Param("status") String status);
    
    /**
     * 统计某活动的报名人数
     */
    int countByActivityId(Integer activityId);
    
    /**
     * 统计某活动指定状态的报名人数
     */
    int countByActivityIdAndStatus(@Param("activityId") Integer activityId, 
                                    @Param("status") String status);
    
    /**
     * 查询所有报名记录
     */
    List<ActivityRegistration> findAll();
    
    /**
     * 根据状态查询所有报名记录
     */
    List<ActivityRegistration> findAllByStatus(String status);
}
