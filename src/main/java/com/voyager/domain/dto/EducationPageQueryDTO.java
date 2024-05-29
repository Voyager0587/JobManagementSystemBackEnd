package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 教育页面查询DTO，用于查询教育信息
 */
@Schema(description = "教育页面查询DTO")
@Data
public class EducationPageQueryDTO {

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
     * 学校名称
     */
    @Schema(description = "学校名称")
    private String schoolName;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idNumber;
}
