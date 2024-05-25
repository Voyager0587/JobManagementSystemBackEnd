package com.voyager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.TalentPageQueryDTO;
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

    @Override
    public PageResult pageQuery(TalentPageQueryDTO talentPageQueryDTO) {
        PageHelper.startPage(talentPageQueryDTO.getPageIndex(), talentPageQueryDTO.getPageSize());
        Page<Talent> page = talentMapper.selectByCriteria(talentPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
