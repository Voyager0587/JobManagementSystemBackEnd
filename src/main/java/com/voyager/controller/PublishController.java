package com.voyager.controller;

import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.PublishPageQueryDTO;
import com.voyager.domain.pojo.Publish;
import com.voyager.service.PublishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "发布接口")
@RestController
@RequestMapping("/api/publish")
public class PublishController {

    @Autowired
    private PublishService publishService;

    /**
     * 根据职位ID查询发布信息
     *
     * @param jobId 职位ID
     * @return 包装对应的Publish对象列表的Result对象
     */
    @Operation(summary = "根据职位ID查询发布信息")
    @GetMapping("/findByJobId/{jobId}")
    @Parameter(description = "职位ID")
    public Result<List<Publish>> findByJobId(@PathVariable int jobId) {
        List<Publish> publishList = publishService.findByJobId(jobId);
        return Result.success(publishList);
    }

    /**
     * 根据发布ID查询发布信息
     *
     * @param publishId 发布ID
     * @return 包装对应的Publish对象的Result对象
     */
    @Operation(summary = "根据发布ID查询发布信息")
    @GetMapping("/findByPublishId/{publishId}")
    @Parameter(description = "发布ID")
    public Result<Publish> findByPublishId(@PathVariable int publishId) {
        Publish publish = publishService.findByPublishId(publishId);
        return Result.success(publish);
    }

    /**
     * 插入新的发布信息
     *
     * @param publish Publish对象
     * @return 包装插入操作结果的Result对象
     */
    @Operation(summary = "插入新的发布信息")
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Publish publish) {
        if (publishService.insert(publish) == 1) {
            return Result.success("插入成功");
        }
        return Result.error("插入失败");
    }

    /**
     * 更新发布信息的有效时间
     *
     * @param publish Publish对象
     * @return 包装更新操作结果的Result对象
     */
    @Operation(summary = "更新发布信息的有效时间")
    @PutMapping("/update")
    public Result<String> update(@RequestBody Publish publish) {
        if (publishService.update(publish) == 1) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    /**
     * 根据发布ID删除发布信息
     *
     * @param publishId 发布ID
     * @return 包装删除操作结果的Result对象
     */
    @Operation(summary = "根据发布ID删除发布信息")
    @DeleteMapping("/delete/{publishId}")
    @Parameter(description = "发布ID")
    public Result<String> deleteByPublishId(@PathVariable int publishId) {
        if (publishService.deleteByPublishId(publishId) == 1) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 分页查询发布信息
     *
     * @param publishPageQueryDTO 分页查询参数
     * @return {@link Result }<{@link PageResult }>
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询发布信息")
    public Result<PageResult> page(PublishPageQueryDTO publishPageQueryDTO) {
        return Result.success(publishService.pageQuery(publishPageQueryDTO));
    }
}
