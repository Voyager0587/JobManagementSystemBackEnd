package com.voyager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.CompanyPageQueryDTO;
import com.voyager.domain.pojo.Company;
import com.voyager.mapper.CompanyMapper;
import com.voyager.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int insertCompany(Company company) {
        return companyMapper.insertCompany(company);
    }

    @Override
    public int deleteCompanyByName(String companyName) {
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
