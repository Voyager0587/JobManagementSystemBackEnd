package com.voyager.domain.pojo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教育信息实体类，用于存储教育信息
 */
@Schema(description = "教育信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    /**
     * 教育信息ID，自动递增
     */
    @Schema(description = "教育信息ID，自动递增")
    private Long educationId;

    /**
     * 学校名称，不能为空
     */
    @NotBlank(message = "学校名称不能为空")
    @Schema(description = "学校名称，不能为空")
    private String schoolName;

    /**
     * 专业
     */
    @Schema(description = "专业")
    private String major;

    /**
     * 入学日期
     */
    @Schema(description = "入学日期")
    private LocalDate enrollmentDate;

    /**
     * 毕业日期
     */
    @Schema(description = "毕业日期")
    private LocalDate graduationDate;

    /**
     * 关联的人才身份证号
     */
    @Schema(description = "关联的人才身份证号")
    private Integer idNumber;
}