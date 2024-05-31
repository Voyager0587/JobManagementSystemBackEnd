package com.voyager.service;


import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.CompanyInsertDTO;
import com.voyager.domain.dto.CompanyPageQueryDTO;
import com.voyager.domain.dto.CompanyUpdateDTO;
import com.voyager.domain.pojo.Company;

public interface CompanyService {
    int insertCompany(CompanyInsertDTO companyInsertDTO);
    int deleteCompanyByName(String companyName);
    Company findCompanyByName(String companyName);
    int updateCompanyContact(String companyName, String contactName, String contactPhone);
    int updateCompanyWebsite(String companyName, String website);
    PageResult pageQuery(CompanyPageQueryDTO companyPageQueryDTO);

    int updateCompany(CompanyUpdateDTO companyUpdateDTO);
}
