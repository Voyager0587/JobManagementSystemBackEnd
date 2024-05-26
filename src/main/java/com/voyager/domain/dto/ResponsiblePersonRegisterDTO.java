package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Tag(name = "用户注册信息")
@Data
public class ResponsiblePersonRegisterDTO implements Serializable {

    /**
     * 关联到 User 表的用户ID
     */
    @Schema(description = "关联到 User 表的用户ID，可为空")
    private Long userId;

    /**
     * 姓名，不能为空
     */
    @Schema(description = "姓名，不能为空")
    private String name;

    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phoneNumber;

    @Schema(description = "公司ID")
    private Long companyId;
}
