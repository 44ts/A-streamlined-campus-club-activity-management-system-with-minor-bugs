package com.campusactivity.service.impl;

import com.campusactivity.entity.User;
import com.campusactivity.mapper.UserMapper;
import com.campusactivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus("enabled");
        user.setRole("user");
        userMapper.insert(user);
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            // 先尝试密码加密验证，如果失败则直接比较明文（兼容现有数据）
            if (passwordEncoder.matches(password, user.getPassword()) || user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userMapper.update(user);
        return user;
    }

    @Override
    public User getById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean isUserDisabled(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null && !"enabled".equals(user.getStatus());
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getByRole(String role) {
        return userMapper.selectByRole(role);
    }

    @Override
    public List<User> getByStatus(String status) {
        return userMapper.selectByStatus(status);
    }

    @Override
    public void enableUser(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setStatus("enabled");
        userMapper.update(user);
    }

    @Override
    public void disableUser(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setStatus("disabled");
        userMapper.update(user);
    }
}
