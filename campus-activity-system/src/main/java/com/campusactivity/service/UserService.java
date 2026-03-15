package com.campusactivity.service;

import com.campusactivity.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);
    User login(String username, String password);
    boolean isUserDisabled(String username);
    User update(User user);
    User getById(Integer userId);
    User getByUsername(String username);
    List<User> getAll();
    List<User> getByRole(String role);
    List<User> getByStatus(String status);
    void enableUser(Integer userId);
    void disableUser(Integer userId);
}