package com.voyager.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 人用于更新人才信息
 */
@Schema(description = "更新人才信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentUpdateDTO {

    /**
     * 关联到 User 表的用户ID
     */
    @Schema(description = "关联到 User 表的用户ID")
    private Long userId;

    /**
     * 身份证号，作为主键
     */
    @Schema(description = "身份证号，作为主键")
    private String idNumber;

    /**
     * 姓名，不能为空
     */
    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名，不能为空")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private char gender;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    private LocalDate birthDate;

    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    private String email;

    /**
     * 手机号码，不能为空
     */
    @NotBlank(message = "手机号码不能为空")
    @Schema(description = "手机号码，不能为空")
    private String phoneNumber;
}