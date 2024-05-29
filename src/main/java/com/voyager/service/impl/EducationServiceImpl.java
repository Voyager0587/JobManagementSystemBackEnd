package com.voyager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.EducationPageQueryDTO;
import com.voyager.domain.pojo.Education;
import com.voyager.mapper.EducationMapper;
import com.voyager.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationMapper educationMapper;

    @Override
    public int insertEducation(Education education) {
        return educationMapper.insertEducation(education);
    }

    @Override
    public int deleteEducationById(int educationId) {
        return educationMapper.deleteEducationById(educationId);
    }

    @Override
    public int updateEducation(Education education) {
        return educationMapper.updateEducation(education);
    }

    @Override
    public Education findEducationById(int educationId) {
        return educationMapper.findEducationById(educationId);
    }

    @Override
    public List<Education> findEducationByIdNumber(String idNumber) {
        return educationMapper.findEducationByIdNumber(idNumber);
    }

    @Override
    public PageResult pageQuery(EducationPageQueryDTO educationPageQueryDTO) {
        PageHelper.startPage(educationPageQueryDTO.getPageIndex(), educationPageQueryDTO.getPageSize());
        Page<Education> page = educationMapper.selectByCriteria(educationPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}