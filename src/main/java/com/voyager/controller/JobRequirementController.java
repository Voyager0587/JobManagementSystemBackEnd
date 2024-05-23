package com.voyager.controller;

import com.voyager.common.result.Result;
import com.voyager.domain.pojo.JobRequirement;
import com.voyager.service.JobRequirementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "职位需求接口")
@RestController
@RequestMapping("/api/jobrequirement")
public class JobRequirementController {

    @Autowired
    private JobRequirementService jobRequirementService;

    /**
     * 插入新的JobRequirement记录
     *
     * @param jobRequirement JobRequirement对象
     * @return 包装插入操作影响的行数的Result对象
     */
    @Operation(summary = "插入新的JobRequirement记录")
    @PostMapping("/insert")
    public Result<String> insertJobRequirement(@RequestBody JobRequirement jobRequirement) {
        jobRequirementService.insertJobRequirement(jobRequirement);
        return Result.success("插入成功");
    }

    /**
     * 根据职位名称和公司ID删除JobRequirement记录
     *
     * @param jobName   职位名称
     * @param companyId 公司ID
     * @return 包装删除操作影响的行数的Result对象
     */
    @Operation(summary = "根据职位名称和公司ID删除JobRequirement记录")
    @DeleteMapping("/delete")
    @Parameter(description = "职位名称")
    @Parameter(description = "公司ID")
    public Result<String> deleteJobRequirementByNameAndCompanyId(@RequestParam String jobName, @RequestParam int companyId) {
        jobRequirementService.deleteJobRequirementByNameAndCompanyId(jobName, companyId);
        return Result.success("删除成功");
    }

    /**
     * 根据职位名称和公司ID查询JobRequirement记录
     *
     * @param jobName   职位名称
     * @param companyId 公司ID
     * @return 包装对应的JobRequirement对象的Result对象
     */
    @Operation(summary = "根据职位名称和公司ID查询JobRequirement记录")
    @GetMapping("/find")
    @Parameter(description = "职位名称")
    @Parameter(description = "公司ID")
    public Result<JobRequirement> findJobRequirementByNameAndCompanyId(@RequestParam String jobName, @RequestParam int companyId) {
        JobRequirement jobRequirement = jobRequirementService.findJobRequirementByNameAndCompanyId(jobName, companyId);
        return Result.success(jobRequirement);
    }

    /**
     * 更新JobRequirement的number_required字段
     *
     * @param jobName        职位名称
     * @param companyId      公司ID
     * @param numberRequired 更新后的需求人数
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新JobRequirement的number_required字段")
    @PutMapping("/update/numberRequired")
    @Parameter(description = "职位名称")
    @Parameter(description = "公司ID")
    @Parameter(description = "更新后的需求人数")
    public Result<String> updateJobRequirementNumberRequired(@RequestParam String jobName, @RequestParam int companyId, @RequestParam int numberRequired) {
        jobRequirementService.updateJobRequirementNumberRequired(jobName, companyId, numberRequired);
        return Result.success("需求人数更新成功");
    }

    /**
     * 更新JobRequirement的job_description字段
     *
     * @param jobName        职位名称
     * @param companyId      公司ID
     * @param jobDescription 更新后的职位描述
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新JobRequirement的job_description字段")
    @PutMapping("/update/description")
    @Parameter(description = "职位名称")
    @Parameter(description = "公司ID")
    @Parameter(description = "更新后的职位描述")
    public Result<String> updateJobRequirementDescription(@RequestParam String jobName, @RequestParam int companyId, @RequestParam String jobDescription) {
        jobRequirementService.updateJobRequirementDescription(jobName, companyId, jobDescription);
        return Result.success("职位描述更新成功");
    }
}
