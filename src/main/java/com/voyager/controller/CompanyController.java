package com.voyager.controller;

import com.voyager.common.result.Result;
import com.voyager.domain.pojo.Company;
import com.voyager.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "公司接口")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 插入新的公司记录
     * @param company Company对象
     * @return 包装插入操作影响的行数的Result对象
     */
    @Operation(summary = "插入新的公司记录")
    @PostMapping("/insert")
    public Result<String> insertCompany(@RequestBody Company company) {
        companyService.insertCompany(company);
        return Result.success("插入成功");
    }

    /**
     * 根据公司名称删除公司记录
     * @param companyName 公司名称
     * @return 包装删除操作影响的行数的Result对象
     */
    @Operation(summary = "根据公司名称删除公司记录")
    @DeleteMapping("/delete/{companyName}")
    @Parameter(description = "公司名称")
    public Result<String> deleteCompanyByName(@PathVariable String companyName) {
        companyService.deleteCompanyByName(companyName);
        return Result.success("删除成功");
    }

    /**
     * 根据公司名称查询公司
     * @param companyName 公司名称
     * @return 包装对应的Company对象的Result对象
     */
    @Operation(summary = "根据公司名称查询公司")
    @GetMapping("/findByName/{companyName}")
    @Parameter(description = "公司名称")
    public Result<Company> findCompanyByName(@PathVariable String companyName) {
        Company company = companyService.findCompanyByName(companyName);
        return Result.success(company);
    }

    /**
     * 更新公司的联系人信息
     * @param companyName 公司名称
     * @param contactName 联系人姓名
     * @param contactPhone 联系人电话
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新公司的联系人信息")
    @PutMapping("/updateContact")
    public Result<String> updateCompanyContact(@RequestParam String companyName, @RequestParam String contactName, @RequestParam String contactPhone) {
        companyService.updateCompanyContact(companyName, contactName, contactPhone);
        return Result.success("联系人信息更新成功");
    }

    /**
     * 更新公司的网址
     * @param companyName 公司名称
     * @param website 公司网址
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新公司的网址")
    @PutMapping("/updateWebsite")
    public Result<String> updateCompanyWebsite(@RequestParam String companyName, @RequestParam String website) {
        companyService.updateCompanyWebsite(companyName, website);
        return Result.success("网址更新成功");
    }
}