package com.voyager.domain.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Company 实体类，用于存储公司信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    /**
     * 公司ID，自动递增
     */
    private Long companyId;

    /**
     * 公司名称，不能为空
     */
    @NotBlank(message = "公司名称不能为空")
    private String companyName;

    /**
     * 公司简介
     */
    private String companyIntro;

    /**
     * 联系人姓名，不能为空
     */
    @NotBlank(message = "联系人姓名不能为空")
    private String contactName;

    /**
     * 联系人电话，不能为空
     */
    @NotBlank(message = "联系人电话不能为空")
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    @Email(message = "请输入有效的电子邮件地址")
    private String email;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 公司网站
     */
    private String website;

}
