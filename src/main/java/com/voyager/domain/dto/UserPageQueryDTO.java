package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户页面查询DTO，用于查询用户信息
 */
@Schema(description = "用户页面查询DTO")
@Data
public class UserPageQueryDTO {

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
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 用户类型
     */
    @Schema(description = "用户类型")
    private Character userType;
}
