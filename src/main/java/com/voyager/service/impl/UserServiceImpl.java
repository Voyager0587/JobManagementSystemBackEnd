package com.voyager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.constant.MessageConstant;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.UserLoginDTO;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.dto.UserRegisterDTO;
import com.voyager.domain.pojo.User;
import com.voyager.mapper.UserMapper;
import com.voyager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserId(Long userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int insert(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        BeanUtil.copyProperties(userRegisterDTO, user);
        user.setRegisterTime(LocalDateTime.now());
        user.setUserType('0'); // 求职者
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

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getUsername() != null && userLoginDTO.getPassword() != null) {
            User user = userMapper.findByUsername(userLoginDTO.getUsername());
            if (user==null){
//                throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
            }else
            if ( user.getPassword().equals(userLoginDTO.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
