package com.voyager.service;

import com.voyager.domain.pojo.User;

public interface UserService {
    User findByUserId(int userId);
    User findByUsername(String username);
    int insert(User user);
    int update(User user);
    int deleteByUserId(int userId);
}
