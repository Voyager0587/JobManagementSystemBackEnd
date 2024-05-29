package com.voyager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.CompanyInsertDTO;
import com.voyager.domain.dto.CompanyPageQueryDTO;
import com.voyager.domain.pojo.Company;
import com.voyager.domain.pojo.JobRequirement;
import com.voyager.domain.pojo.ResponsiblePerson;
import com.voyager.mapper.*;
import com.voyager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private JobRequirementMapper jobRequirementMapper;

    @Autowired
    private ApplicationReviewMapper applicationReviewMapper;

    @Autowired
    private PublishMapper publishMapper;

    @Autowired
    private ResponsiblePersonMapper responsiblePersonMapper;
    @Override
    public int insertCompany(CompanyInsertDTO companyInsertDTO) {
        Company company = new Company();
        BeanUtil.copyProperties(companyInsertDTO,company);
        return companyMapper.insertCompany(company);
    }

    @Transactional
    @Override
    public int deleteCompanyByName(String companyName) {
        Company company = companyMapper.findCompanyByName(companyName);
        List<ResponsiblePerson> personList = responsiblePersonMapper.findByCompanyId(company.getCompanyId());
        personList.forEach(responsiblePerson -> {
            responsiblePersonMapper.updateCompanyIdById((long) -1,responsiblePerson.getPersonId());
        });
        List<JobRequirement> jobRequirementList = jobRequirementMapper.findJobRequirementByCompanyId(company.getCompanyId());
        jobRequirementList.forEach(jobRequirement -> {
            applicationReviewMapper.deleteByJobId(jobRequirement.getJobId());
            publishMapper.deleteByJobId(jobRequirement.getJobId());
        });
        jobRequirementMapper.deleteJobRequirementsCompanyId(company.getCompanyId());
        //TODO 负责人公司更新不成功，外键约束问题
        return companyMapper.deleteCompanyByName(companyName);
    }

    @Override
    public Company findCompanyByName(String companyName) {
        return companyMapper.findCompanyByName(companyName);
    }

    @Override
    public int updateCompanyContact(String companyName, String contactName, String contactPhone) {
        return companyMapper.updateCompanyContact(companyName, contactName, contactPhone);
    }

    @Override
    public int updateCompanyWebsite(String companyName, String website) {
        return companyMapper.updateCompanyWebsite(companyName, website);
    }

    @Override
    public PageResult pageQuery(CompanyPageQueryDTO companyPageQueryDTO) {
        PageHelper.startPage(companyPageQueryDTO.getPageIndex(), companyPageQueryDTO.getPageSize());
        Page<Company> page = companyMapper.selectByCriteria(companyPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
