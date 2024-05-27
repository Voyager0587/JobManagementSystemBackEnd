package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用于插入应聘信息和审核结果
 */
@Schema(description = "插入应聘审核信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationReviewDTO {

    /**
     * 关联到 User 表的用户ID
     */
    @Schema(description = "关联到 User 表的用户ID")
    private Long userId;




//    /**
//     * 关联的人才身份证号
//     */
//    @Schema(description = "关联的人才身份证号")
//    private String idNumber;

    /**
     * 关联的职位ID
     */
    @Schema(description = "关联的职位ID")
    private Integer jobId;

}
