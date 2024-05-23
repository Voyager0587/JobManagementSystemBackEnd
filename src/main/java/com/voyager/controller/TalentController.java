package com.voyager.controller;

import com.voyager.common.result.Result;
import com.voyager.domain.pojo.Talent;
import com.voyager.service.TalentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "人才信息接口")
@RestController
@RequestMapping("/api/talent")
public class TalentController {

    @Autowired
    private TalentService talentService;

    /**
     * 添加人才信息
     *
     * @param talent 待添加的Talent对象，包含人才详细信息
     * @return 包装插入操作影响的行数的Result对象
     */
    @Operation(summary = "添加人才信息")
    @PostMapping("/add")
    public Result<String> addTalent(@RequestBody Talent talent) {
        talentService.insertTalent(talent);
        return Result.success("添加成功");
    }

    /**
     * 根据用户ID删除人才信息
     *
     * @param userId 用户ID，用于定位要删除的人才记录
     * @return 包装删除操作影响的行数的Result对象
     */
    @Operation(summary = "根据用户ID删除人才信息")
    @Parameter(name = "userId", description = "用户ID")
    @DeleteMapping("/delete/{userId}")
    public Result<String> deleteTalent(@PathVariable int userId) {
        talentService.deleteByIdNumber(userId);
        return Result.success("删除成功");
    }

    /**
     * 更新人才信息
     *
     * @param talent 更新后的Talent对象，包含要修改的信息
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新人才信息")
    @PutMapping("/update")
    public Result<String> updateTalent(@RequestBody Talent talent) {
        talentService.updateTalent(talent);
        return Result.success("更新成功");
    }

    /**
     * 根据身份证号查找人才信息
     *
     * @param idNumber 身份证号，用于查询特定人才
     * @return 包装与身份证号匹配的Talent对象的Result对象
     */
    @Operation(summary = "根据身份证号查找人才信息")
    @Parameter(name = "idNumber", description = "身份证号")
    @GetMapping("/find/idNumber/{idNumber}")
    public Result<Talent> findByIdNumber(@PathVariable String idNumber) {
        Talent talent = talentService.findByIdNumber(idNumber);
        return Result.success(talent);
    }

    /**
     * 根据姓名查找人才信息
     *
     * @param name 姓名，用于查询人才
     * @return 包装与姓名匹配的Talent对象的Result对象
     */
    @Operation(summary = "根据姓名查找人才信息")
    @Parameter(name = "name", description = "姓名")
    @GetMapping("/find/name/{name}")
    public Result<Talent> findByName(@PathVariable String name) {
        Talent talent = talentService.findByName(name);
        return Result.success(talent);
    }

    /**
     * 根据用户ID查找人才信息
     *
     * @param userId 用户ID，用于查询人才
     * @return 包装与用户ID匹配的Talent对象的Result对象
     */
    @Operation(summary = "根据用户ID查找人才信息")
    @Parameter(name = "userId", description = "用户ID")
    @GetMapping("/find/userId/{userId}")
    public Result<Talent> findByUserId(@PathVariable int userId) {
        Talent talent = talentService.findByUserId(userId);
        return Result.success(talent);
    }
}
