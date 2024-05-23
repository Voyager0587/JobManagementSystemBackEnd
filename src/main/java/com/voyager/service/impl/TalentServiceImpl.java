package com.voyager.service.impl;

import com.voyager.domain.pojo.Talent;
import com.voyager.mapper.TalentMapper;
import com.voyager.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalentServiceImpl implements TalentService {

    @Autowired
    private TalentMapper talentMapper;

    @Override
    public int insertTalent(Talent talent) {
        return talentMapper.insertTalent(talent);
    }

    @Override
    public int deleteByIdNumber(int userId) {
        return talentMapper.deleteByIdNumber(userId);
    }

    @Override
    public int updateTalent(Talent talent) {
        return talentMapper.updateTalent(talent);
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
        return talentMapper.findByUserId(userId);
    }
}
