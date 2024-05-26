package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 负责人信息实体类，用于存储负责人信息
 */
@Schema(description = "负责人信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsiblePersonInsertDTO {


    /**
     * 关联到 User 表的用户ID
     */
    @Schema(description = "关联到 User 表的用户ID")
    private Long userId;

    /**
     * 负责人姓名，不能为空
     */
    @NotBlank(message = "负责人姓名不能为空")
    @Schema(description = "负责人姓名，不能为空")
    private String name;

    /**
     * 负责人电话，不能为空
     */
    @NotBlank(message = "负责人电话不能为空")
    @Schema(description = "负责人电话，不能为空")
    private String phoneNumber;

    /**
     * 负责人邮箱
     */
    @Email(message = "请输入有效的电子邮件地址")
    @Schema(description = "负责人邮箱")
    private String email;

    /**
     * 关联的公司ID
     */
    @NotNull(message = "公司ID不能为空")
    @Schema(description = "关联的公司ID")
    private Long companyId;
}