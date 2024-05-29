package com.voyager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.*;
import com.voyager.domain.pojo.Talent;
import com.voyager.domain.pojo.User;
import com.voyager.mapper.TalentMapper;
import com.voyager.mapper.UserMapper;
import com.voyager.service.TalentService;
import com.voyager.utills.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TalentServiceImpl implements TalentService {

    @Autowired
    private TalentMapper talentMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int insertTalent(Talent talent) {
        return talentMapper.insertTalent(talent);
    }

    @Override
    public int deleteByIdNumber(int userId) {
        return talentMapper.deleteByIdNumber(userId);
    }

    @Override
    public int updateTalent(TalentUpdateDTO talentUpdateDTO) {
        return talentMapper.updateTalent(talentUpdateDTO);
    }

    @Override
    public Talent findByIdNumber(String idNumber) {
        return talentMapper.findByIdNumber(idNumber);
    }

    @Override
    public Talent findByName(String name) {
        return talentMapper.findByName(name);
    }

    @Override
    public Talent findByUserId(int userId) {
        return talentMapper.findByUserId((long) userId);
    }

    @Override
    public PageResult pageQuery(TalentPageQueryDTO talentPageQueryDTO) {
        PageHelper.startPage(talentPageQueryDTO.getPageIndex(), talentPageQueryDTO.getPageSize());
        Page<Talent> page = talentMapper.selectByCriteria(talentPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Transactional
    @Override
    public Talent login(PersonLoginDTO personLoginDTO) {
        Talent talent = talentMapper.findByPhone(personLoginDTO.getPhone());
        if (talent==null){
            return null;//TODO 人才不存在
        }
        User user = userMapper.findByUserId(talent.getUserId());
        if (user==null){
            return null;//TODO 用户不存在
        }else if (passwordEncoder.matches(personLoginDTO.getPassword(), user.getPassword())){
            return talent;
        }else{
            return null;// TODO 密码错误
        }

    }

    @Override
    public int insert(TalentRegisterDTO talentRegisterDTO) {
        User user = UserHolder.getInfoByToken();
        //查看有相关无人才信息
        Talent talent=new Talent();
        if (talentMapper.findByUserId(user.getUserId())==null){
            BeanUtil.copyProperties(talentRegisterDTO, talent);
            talent.setUserId(user.getUserId());
            return talentMapper.insertTalent(talent);
        }
        return -1;
    }
}
