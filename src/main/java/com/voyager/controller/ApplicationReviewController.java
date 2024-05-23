package com.voyager.controller;

import com.voyager.common.result.Result;
import com.voyager.domain.pojo.ApplicationReview;
import com.voyager.service.ApplicationReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "申请审核接口")
@RestController
@RequestMapping("/api/application-review")
public class ApplicationReviewController {
    @Autowired
    private ApplicationReviewService applicationReviewService;

    /**
     * 根据申请ID查询申请信息
     *
     * @param applyId 申请ID
     * @return {@link ApplicationReview }
     */
    @GetMapping("/{applyId}")
    @Operation(summary = "根据申请ID查询申请信息")
    @Parameter(name = "applyId", description = "申请ID")
    public Result<ApplicationReview> findByApplyId(@PathVariable int applyId) {
        return Result.success(applicationReviewService.findByApplyId(applyId));
    }

    /**
     * 根据职位ID查询审核和申请
     *
     * @param jobId 职位ID，用于筛选申请信息
     * @return 返回一个包含与给定职位ID匹配的ApplicationReview对象的列表
     */
    @Operation(summary = "根据职位ID查询审核和申请")
    @Parameter(name = "jobId", description = "职位ID")
    @GetMapping("/job/{jobId}")
    public
    Result<List<ApplicationReview>> findByJobId(@PathVariable int jobId) {
        return Result.success(applicationReviewService.findByJobId(jobId));
    }

    /**
     * 根据身份证号查询申请信息
     *
     * @param idNumber 身份证号，用于查询特定申请人的信息
     * @return 返回一个包含与给定身份证号匹配的ApplicationReview对象的列表
     */
    @Operation(summary = "根据身份证号查询申请信息")
    @Parameter(name = "idNumber", description = "身份证号")
    @GetMapping("/idNumber/{idNumber}")
    public
    Result<List<ApplicationReview>> findByIdNumber(@PathVariable String idNumber) {
        return Result.success(applicationReviewService.findByIdNumber(idNumber));
    }

    /**
     * 根据审核状态查询申请信息
     *
     * @param status 审核状态，用于筛选不同状态的申请
     * @return 返回一个包含与给定状态匹配的ApplicationReview对象的列表
     */
    @Operation(summary = "根据审核状态查询申请信息")
    @Parameter(name = "status", description = "审核状态")
    @GetMapping("/status/{status}")
    public Result<List<ApplicationReview>> findByStatus(@PathVariable int status) {
        return Result.success(applicationReviewService.findByStatus(status));
    }

    /**
     * 插入新的申请信息
     *
     * @param applicationReview 新的ApplicationReview对象，包含要插入的数据
     * @return 返回插入操作影响的行数
     */
    @Operation(summary = "插入新的申请信息")
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody ApplicationReview applicationReview) {
        applicationReviewService.insert(applicationReview);
        return Result.success("插入成功");
    }

    /**
     * 更新申请信息
     *
     * @param applicationReview 包含更新数据的ApplicationReview对象
     * @return 返回更新操作影响的行数
     */
    @Operation(summary = "更新申请信息")
    @Parameter(name = "applicationReview", description = "包含更新数据的ApplicationReview对象")
    @PostMapping("/update")
    public Result<String> update(@RequestBody ApplicationReview applicationReview) {
        applicationReviewService.update(applicationReview);
        return Result.success("更新成功");
    }

    /**
     * 删除申请信息
     *
     * @param applyId 申请ID，用于指定要删除的申请记录
     * @return 返回删除操作影响的行数
     */
    @Operation(summary = "删除申请信息")
    @Parameter(name = "applyId", description = "申请ID")
    @DeleteMapping("/{applyId}")
    public Result<String> deleteByApplyId(@PathVariable int applyId) {
        applicationReviewService.deleteByApplyId(applyId);
        return Result.success("删除成功");
    }

    /**
     * 根据职位ID删除相关的申请信息
     *
     * @param jobId 职位ID，所有与该职位相关的申请都将被删除
     * @return 返回一个表示操作成功的结果对象
     */
    @Operation(summary = "根据职位ID删除相关的申请信息")
    @Parameter(name = "jobId", description = "职位ID")
    @DeleteMapping("/job/{jobId}")
    public Result<String> deleteByJobId(@PathVariable int jobId) {
        applicationReviewService.deleteByJobId(jobId);
        return Result.success("删除成功");
    }


}
