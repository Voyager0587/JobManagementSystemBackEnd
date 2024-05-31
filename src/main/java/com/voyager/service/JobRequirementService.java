package com.voyager.service;


import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.JobRequirementPageQueryDTO;
import com.voyager.domain.pojo.JobRequirement;

public interface JobRequirementService {
    int insertJobRequirement(JobRequirement jobRequirement);
    int deleteJobRequirementByNameAndCompanyId(String jobName, int companyId);
    JobRequirement findJobRequirementByNameAndCompanyId(String jobName, int companyId);
    int updateJobRequirementNumberRequired(String jobName, int companyId, int numberRequired);
    int updateJobRequirementDescription(String jobName, int companyId, String jobDescription);
    PageResult pageQuery(JobRequirementPageQueryDTO jobRequirementPageQueryDTO);

    int deleteJobRequirementByJobId(int jobId);
}
