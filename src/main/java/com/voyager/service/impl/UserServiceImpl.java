package com.voyager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.UserLoginDTO;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.dto.UserRegisterDTO;
import com.voyager.domain.pojo.User;
import com.voyager.domain.vo.UserVO;
import com.voyager.mapper.TalentMapper;
import com.voyager.mapper.UserMapper;
import com.voyager.service.ResponsiblePersonService;
import com.voyager.service.TalentService;
import com.voyager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResponsiblePersonService responsiblePersonService;

    @Autowired
    private TalentService talentService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUserId(Long userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User insert(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        BeanUtil.copyProperties(userRegisterDTO, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegisterTime(LocalDateTime.now());
        user.setUserType(userRegisterDTO.getUserType());
        if (userMapper.insert(user) == 1) {
            return user;
        }
        return null;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteByUserId(int userId) {
        return userMapper.updateUserTypeByUserId((long) userId);
        //改为使用触发器级联删除
//        User user = userMapper.findByUserId((long) userId);
//        if (user.getUserType()=='1') {
//            return responsiblePersonService.deleteByUserId(userId);
//        }else if (user.getUserType()=='0') {
//            return talentService.deleteByUserId(userId);
//        }else {
//
//            return 0;
//        }

    }

    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPageIndex(), userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.selectByCriteria(userPageQueryDTO);
        //将List<User>转换为List<UserVO>
        List<UserVO> userVOList = BeanUtil.copyToList(page.getResult(), UserVO.class);
        return new PageResult(page.getTotal(), userVOList);
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getUsername() != null && userLoginDTO.getPassword() != null) {
            User user = userMapper.findByUsername(userLoginDTO.getUsername());
            if (user == null) {
//                throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
            } else if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                return user;
            }

        }
        return null;
    }
}
