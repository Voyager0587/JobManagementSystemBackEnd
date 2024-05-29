package com.voyager.service;

import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.EducationPageQueryDTO;
import com.voyager.domain.pojo.Education;

import java.util.List;

public interface EducationService {
    int insertEducation(Education education);
    int deleteEducationById(int educationId);
    int updateEducation(Education education);
    Education findEducationById(int educationId);
    List<Education> findEducationByIdNumber(String idNumber);
    PageResult pageQuery(EducationPageQueryDTO educationPageQueryDTO);
}