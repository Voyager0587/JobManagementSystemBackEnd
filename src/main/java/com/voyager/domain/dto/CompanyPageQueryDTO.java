package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 公司页面查询DTO，用于查询公司信息
 */
@Schema(description = "公司页面查询DTO")
@Data
public class CompanyPageQueryDTO {

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
     * 公司名称
     */
    @Schema(description = "公司名称")
    private String companyName;
}
