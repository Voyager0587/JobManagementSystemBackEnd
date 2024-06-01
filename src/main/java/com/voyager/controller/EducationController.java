package com.voyager.controller;

import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.EducationPageQueryDTO;
import com.voyager.domain.pojo.Education;
import com.voyager.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    /**
     * 插入新的教育信息记录
     *
     * @param education 教育信息对象
     * @return 包装插入操作结果的Result对象
     */
    @Operation(summary = "插入新的教育信息记录")
    @PostMapping("/insert")
    public Result<String> insertEducation(@RequestBody Education education) {
        if (educationService.insertEducation(education) == 1)
            return Result.success("插入成功");
        return Result.error("插入失败");
    }

    /**
     * 根据教育信息ID删除记录
     *
     * @param educationId 教育信息ID
     * @return 包装删除操作结果的Result对象
     */
    @Operation(summary = "根据教育信息ID删除记录")
    @PostMapping("/delete/{educationId}")
    @Parameter(description = "教育信息ID")
    public Result<String> deleteEducationById(@PathVariable int educationId) {
        if (educationService.deleteEducationById(educationId) == 1)
            return Result.success("删除成功");
        return Result.error("删除失败");
    }

    /**
     * 更新教育信息记录
     *
     * @param education 教育信息对象
     * @return 包装更新操作结果的Result对象
     */
    @Operation(summary = "更新教育信息记录")
    @PostMapping("/update")
    public Result<String> updateEducation(@RequestBody Education education) {
        if (educationService.updateEducation(education) == 1)
            return Result.success("更新成功");
        return Result.error("更新失败");
    }

    /**
     * 根据教育信息ID查询记录
     *
     * @param educationId 教育信息ID
     * @return 包装对应的教育信息对象的Result对象
     */
    @Operation(summary = "根据教育信息ID查询记录")
    @GetMapping("/find/{educationId}")
    @Parameter(description = "教育信息ID")
    public Result<Education> findEducationById(@PathVariable int educationId) {
        Education education = educationService.findEducationById(educationId);
        return Result.success(education);
    }

    /**
     * 根据身份证号查询所有教育信息记录
     *
     * @param idNumber 人才身份证号
     * @return 包装对应的教育信息对象列表的Result对象
     */
    @Operation(summary = "根据身份证号查询所有教育信息记录")
    @GetMapping("/findByIdNumber/{idNumber}")
    @Parameter(description = "人才身份证号")
    public Result<List<Education>> findEducationByIdNumber(@PathVariable String idNumber) {
        List<Education> educationList = educationService.findEducationByIdNumber(idNumber);
        return Result.success(educationList);
    }

    /**
     * 分页查询教育信息
     *
     * @param educationPageQueryDTO 分页查询参数
     * @return {@link Result }<{@link PageResult }>
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询教育信息")
    public Result<PageResult> page(EducationPageQueryDTO educationPageQueryDTO) {
        return Result.success(educationService.pageQuery(educationPageQueryDTO));
    }
}