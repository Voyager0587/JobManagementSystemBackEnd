package com.voyager.domain.pojo;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Talent 实体类，用于存储人才信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talent {

    /**
     * 关联到 User 表的用户ID
     */
    private Long userId;

    /**
     * 身份证号，作为主键
     */
    private String idNumber;

    /**
     * 姓名，不能为空
     */
    private String name;

    /**
     * 性别
     */
    private char gender;

    /**
     * 出生日期
     */
    private LocalDate birthDate;

    /**
     * 电子邮箱
     */
    @Email(message = "请输入有效的电子邮件地址")
    private String email;

    /**
     * 手机号码，不能为空
     */
    private String phoneNumber;


}
