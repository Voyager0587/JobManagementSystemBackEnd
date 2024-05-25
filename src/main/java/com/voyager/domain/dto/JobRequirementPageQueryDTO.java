package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 工作要求页面查询DTO，用于查询工作要求信息
 */
@Schema(description = "工作要求页面查询DTO")
@Data
public class JobRequirementPageQueryDTO {

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
     * 工作名称
     */
    @Schema(description = "工作名称")
    private String jobName;

    /**
     * 公司ID
     */
    @Schema(description = "公司ID")
    private Long companyId;
}
