package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Tag(name = "用户注册信息")
@Data
public class TalentRegisterDTO implements Serializable {

    /**
     * 身份证号，作为主键
     */
    @Schema(description = "身份证号，作为主键")
    private String idNumber;

    /**
     * 姓名，不能为空
     */
    @Schema(description = "姓名，不能为空")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Character gender;

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
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phoneNumber;
}
