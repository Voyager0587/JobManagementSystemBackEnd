package com.voyager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.JobRequirementPageQueryDTO;
import com.voyager.domain.pojo.JobRequirement;
import com.voyager.mapper.ApplicationReviewMapper;
import com.voyager.mapper.JobRequirementMapper;
import com.voyager.service.JobRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JobRequirementServiceImpl implements JobRequirementService {

    @Autowired
    private JobRequirementMapper jobRequirementMapper;
    @Autowired
    private ApplicationReviewMapper applicationReviewMapper;

    @Override
    public int insertJobRequirement(JobRequirement jobRequirement) {
        return jobRequirementMapper.insertJobRequirement(jobRequirement);
    }

    @Transactional
    @Override
    public int deleteJobRequirementByNameAndCompanyId(String jobName, int companyId) {
        JobRequirement job = jobRequirementMapper.findJobRequirementByNameAndCompanyId(jobName, companyId);
        applicationReviewMapper.deleteByJobId(job.getJobId());
        return jobRequirementMapper.deleteJobRequirementByNameAndCompanyId(jobName, companyId);
    }

    @Override
    public JobRequirement findJobRequirementByNameAndCompanyId(String jobName, int companyId) {
        return jobRequirementMapper.findJobRequirementByNameAndCompanyId(jobName, companyId);
    }

    @Override
    public int updateJobRequirementNumberRequired(String jobName, int companyId, int numberRequired) {
        return jobRequirementMapper.updateJobRequirementNumberRequired(jobName, companyId, numberRequired);
    }

    @Override
    public int updateJobRequirementDescription(String jobName, int companyId, String jobDescription) {
        return jobRequirementMapper.updateJobRequirementDescription(jobName, companyId, jobDescription);
    }

    @Override
    public PageResult pageQuery(JobRequirementPageQueryDTO jobRequirementPageQueryDTO) {
        PageHelper.startPage(jobRequirementPageQueryDTO.getPageIndex(), jobRequirementPageQueryDTO.getPageSize());
        Page<JobRequirement> page = jobRequirementMapper.selectByCriteria(jobRequirementPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
