package com.voyager.service;


import com.voyager.domain.pojo.Company;

public interface CompanyService {
    int insertCompany(Company company);
    int deleteCompanyByName(String companyName);
    Company findCompanyByName(String companyName);
    int updateCompanyContact(String companyName, String contactName, String contactPhone);
    int updateCompanyWebsite(String companyName, String website);
}
