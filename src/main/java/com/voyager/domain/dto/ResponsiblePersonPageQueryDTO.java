package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 负责人页面查询DTO，用于查询负责人信息
 */
@Schema(description = "负责人页面查询DTO")
@Data
public class ResponsiblePersonPageQueryDTO {

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
     * 公司ID
     */
    @Schema(description = "公司ID")
    private Long companyId;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String phoneNumber;
}
