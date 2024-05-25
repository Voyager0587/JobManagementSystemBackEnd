package com.voyager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.pojo.User;
import com.voyager.mapper.UserMapper;
import com.voyager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserId(int userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteByUserId(int userId) {
        return userMapper.deleteByUserId(userId);
    }

    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPageIndex(), userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.selectByCriteria(userPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
