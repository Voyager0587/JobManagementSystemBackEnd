package com.voyager.service.impl;

import com.voyager.domain.pojo.JobRequirement;
import com.voyager.mapper.JobRequirementMapper;
import com.voyager.service.JobRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRequirementServiceImpl implements JobRequirementService {

    @Autowired
    private JobRequirementMapper jobRequirementMapper;

    @Override
    public int insertJobRequirement(JobRequirement jobRequirement) {
        return jobRequirementMapper.insertJobRequirement(jobRequirement);
    }

    @Override
    public int deleteJobRequirementByNameAndCompanyId(String jobName, int companyId) {
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
}
