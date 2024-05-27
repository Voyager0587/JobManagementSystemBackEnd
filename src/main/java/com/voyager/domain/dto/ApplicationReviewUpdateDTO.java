package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用于更新应聘信息和审核结果
 */
@Schema(description = "更新应聘审核信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationReviewUpdateDTO {

    /**
     * 申请ID，自动递增
     */
    @Schema(description = "申请ID，自动递增")
    private Integer applyId;


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


}
