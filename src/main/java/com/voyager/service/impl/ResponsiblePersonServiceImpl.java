package com.voyager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.PersonLoginDTO;
import com.voyager.domain.dto.ResponsiblePersonPageQueryDTO;
import com.voyager.domain.dto.ResponsiblePersonRegisterDTO;
import com.voyager.domain.dto.UserRegisterDTO;
import com.voyager.domain.pojo.ResponsiblePerson;
import com.voyager.domain.pojo.User;
import com.voyager.mapper.ResponsiblePersonMapper;
import com.voyager.mapper.UserMapper;
import com.voyager.service.ResponsiblePersonService;
import com.voyager.service.UserService;
import com.voyager.utills.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResponsiblePersonServiceImpl implements ResponsiblePersonService {

    @Autowired
    private ResponsiblePersonMapper responsiblePersonMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponsiblePerson findById(int personId) {
        return responsiblePersonMapper.findById(personId);
    }

    @Override
    public ResponsiblePerson findByUserId(int userId) {
        return responsiblePersonMapper.findByUserId(userId);
    }

    @Override
    public List<ResponsiblePerson> findByCompanyId(int companyId) {
        return responsiblePersonMapper.findByCompanyId(companyId);
    }

    @Transactional
    @Override
    public int insert(ResponsiblePersonRegisterDTO responsiblePersonRegisterDTO) {
        ResponsiblePerson responsiblePerson = new ResponsiblePerson();
        BeanUtil.copyProperties(responsiblePersonRegisterDTO, responsiblePerson);
        responsiblePerson.setUserId(UserHolder.getInfoByToken().getUserId());
        return responsiblePersonMapper.insert(responsiblePerson);
    }

    @Override
    public int update(ResponsiblePerson responsiblePerson) {
        return responsiblePersonMapper.update(responsiblePerson);
    }

    @Override
    public int deleteById(int personId) {
        return responsiblePersonMapper.deleteById(personId);
    }

    @Override
    public PageResult pageQuery(ResponsiblePersonPageQueryDTO responsiblePersonPageQueryDTO) {
        PageHelper.startPage(responsiblePersonPageQueryDTO.getPageIndex(), responsiblePersonPageQueryDTO.getPageSize());
        Page<ResponsiblePerson> page = responsiblePersonMapper.selectByCriteria(responsiblePersonPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Transactional
    @Override
    public ResponsiblePerson login(PersonLoginDTO personLoginDTO) {
        ResponsiblePerson responsiblePerson = responsiblePersonMapper.findByPhone(personLoginDTO.getPhone());
        if (responsiblePerson == null) {
            return null;
        }
        User user = userMapper.findByUserId(responsiblePerson.getUserId());
        if (user == null) {
            return null;
        }
        if (passwordEncoder.matches(personLoginDTO.getPassword(), user.getPassword())) {
            return responsiblePerson;
        }
        return null;
    }
}
