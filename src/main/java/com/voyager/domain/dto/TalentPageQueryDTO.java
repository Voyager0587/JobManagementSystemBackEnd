package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * 人才页面查询DTO，用于查询人才信息
 */
@Schema(description = "人才页面查询DTO")
@Data
public class TalentPageQueryDTO {

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
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idNumber;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String phoneNumber;
}
