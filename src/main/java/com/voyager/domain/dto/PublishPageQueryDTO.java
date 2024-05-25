package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 发布页面查询DTO，用于查询发布信息
 */
@Schema(description = "发布页面查询DTO")
@Data
public class PublishPageQueryDTO {

    /**
     * 当前页码
     */
    @Schema(description = "当前页码")
    private int pageIndex;

    /**
     * 每页大小
     */
    @Schema(description = "每页大小")
    private int pageSize;

    /**
     * 工作ID
     */
    @Schema(description = "工作ID")
    private Long jobId;
}
