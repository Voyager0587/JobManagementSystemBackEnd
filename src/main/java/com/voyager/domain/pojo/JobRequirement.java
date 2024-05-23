package com.voyager.domain.pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JobRequirement 实体类，用于存储职位需求信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequirement {

    /**
     * 职位ID，自动递增
     */
    private Long jobId;

    /**
     * 职位名称，不能为空
     */
    @NotBlank(message = "职位名称不能为空")
    private String jobName;

    /**
     * 职位描述，VARCHAR 类型最大长度为 255 个字符,而 TEXT 类型可以存储更大的文本数据,最大长度可达 65,535 个字符
     */
    private String jobDescription;

    /**
     * 需求人数
     */
    @Min(value = 1, message = "需求人数不能小于1")
    private Integer numberRequired;

    /**
     * 关联的公司ID，不能为空
     */
    @NotNull(message = "公司ID不能为空")
    private Long companyId;

}

