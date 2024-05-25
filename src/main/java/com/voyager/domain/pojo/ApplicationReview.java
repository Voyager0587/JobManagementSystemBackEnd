package com.voyager.domain.pojo;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ApplicationReview 实体类，用于存储应聘信息和审核结果
 */
@Schema(description = "应聘审核信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationReview {

    /**
     * 申请ID，自动递增
     */
    @Schema(description = "申请ID，自动递增")
    private Integer applyId;

    /**
     * 应聘时间
     */
    @Schema(description = "应聘时间")
    private LocalDateTime applicationTime;

    /**
     * 审核时间
     */
    @Schema(description = "审核时间")
    private LocalDateTime reviewTime;

    /**
     * 审核结果
     */
    @Schema(description = "审核结果")
    private String reviewResult;

    /**
     * 审核状态，0表示未审核，1表示审核通过，2表示审核不通过
     */
    @Schema(description = "审核状态，0表示未审核，1表示审核通过，2表示审核不通过")
    private Integer reviewStatus;

    /**
     * 关联的人才身份证号
     */
    @Schema(description = "关联的人才身份证号")
    private String idNumber;

    /**
     * 关联的职位ID
     */
    @Schema(description = "关联的职位ID")
    private Integer jobId;

}
