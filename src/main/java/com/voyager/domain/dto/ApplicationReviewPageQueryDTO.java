package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 应用审核页面查询DTO，用于查询应用审核信息
 */
@Schema(description = "应用审核页面查询DTO")
@Data
public class ApplicationReviewPageQueryDTO {

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
     * 审核状态
     */
    @Schema(description = "审核状态")
    private Integer reviewStatus;

    /**
     * 工作ID
     */
    @Schema(description = "工作ID")
    private Integer jobId;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idNumber;
}
