package com.voyager.service;

import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.UserLoginDTO;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.dto.UserRegisterDTO;
import com.voyager.domain.pojo.User;

public interface UserService {
    User findByUserId(Long userId);
    User findByUsername(String username);
    int insert(UserRegisterDTO user);
    int update(User user);
    int deleteByUserId(int userId);
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    User login(UserLoginDTO userLoginDTO);
}
