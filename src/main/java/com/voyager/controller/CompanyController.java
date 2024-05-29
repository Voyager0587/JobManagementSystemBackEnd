package com.voyager.controller;

import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.CompanyDeleteDTO;
import com.voyager.domain.dto.CompanyInsertDTO;
import com.voyager.domain.dto.CompanyPageQueryDTO;
import com.voyager.domain.pojo.Company;
import com.voyager.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 公司接口
 *
 * @author Voyager
 * @date 2024/05/25
 */
@Tag(name = "公司接口")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 绑定公司
     *
     * @param companyInsertDTO CompanyInsertDTO
     * @return 包装插入操作影响的行数的Result对象
     */
    @Operation(summary = "添加公司")
    @PostMapping("/insert")
    public Result<String> insertCompany(@RequestBody CompanyInsertDTO companyInsertDTO) {
        if (companyService.insertCompany(companyInsertDTO) == 1) {
            return Result.success("绑定公司成功");
        }
        return Result.error("绑定公司失败");
    }

    /**
     * 根据公司名称删除公司记录
     *
     * @param companyDeleteDTO 公司名称
     * @return 包装删除操作影响的行数的Result对象
     */
    @Operation(summary = "根据公司名称删除公司记录")
    @PostMapping("/delete")
    @Parameter(description = "公司名称")
    public Result<String> deleteCompanyByName(@RequestBody CompanyDeleteDTO companyDeleteDTO) {
        if (companyService.deleteCompanyByName(companyDeleteDTO.getCompanyName()) >= 1)
            return Result.success("删除成功");
        return Result.error("删除失败或者不存在该公司");
    }

    /**
     * 根据公司名称查询公司
     *
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
     *
     * @param companyName  公司名称
     * @param contactName  联系人姓名
     * @param contactPhone 联系人电话
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新公司的联系人信息")
    @PostMapping("/updateContact")
    public Result<String> updateCompanyContact(@RequestParam String companyName, @RequestParam String contactName, @RequestParam String contactPhone) {
        if (companyService.updateCompanyContact(companyName, contactName, contactPhone) >= 1) {
            return Result.success("联系人信息更新成功");
        }
        return Result.error("联系人信息更新失败");
    }

    /**
     * 更新公司的网址
     *
     * @param companyName 公司名称
     * @param website     公司网址
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新公司的网址")
    @PutMapping("/updateWebsite")
    public Result<String> updateCompanyWebsite(@RequestParam String companyName, @RequestParam String website) {
        if (companyService.updateCompanyWebsite(companyName, website) >= 1)
            return Result.success("网址更新成功");
        return Result.error("网址更新失败");
    }

    /**
     * 分页查询公司信息
     *
     * @param companyPageQueryDTO 分页查询参数
     * @return {@link Result }<{@link PageResult }>
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询公司信息")
    public Result<PageResult> page(CompanyPageQueryDTO companyPageQueryDTO) {
        return Result.success(companyService.pageQuery(companyPageQueryDTO));
    }
}
