package com.campusactivity.mapper;

import com.campusactivity.entity.User;

import java.util.List;

public interface UserMapper {
    int insert(User user);
    int update(User user);
    int delete(Integer userId);
    User selectByPrimaryKey(Integer userId);
    User selectByUsername(String username);
    List<User> selectAll();
    List<User> selectByRole(String role);
    List<User> selectByStatus(String status);
}