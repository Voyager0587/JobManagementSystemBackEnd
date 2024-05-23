package com.voyager.service;


import com.voyager.domain.pojo.JobRequirement;

public interface JobRequirementService {
    int insertJobRequirement(JobRequirement jobRequirement);
    int deleteJobRequirementByNameAndCompanyId(String jobName, int companyId);
    JobRequirement findJobRequirementByNameAndCompanyId(String jobName, int companyId);
    int updateJobRequirementNumberRequired(String jobName, int companyId, int numberRequired);
    int updateJobRequirementDescription(String jobName, int companyId, String jobDescription);
}
