package com.voyager.domain.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 职位发布信息实体类，用于存储职位发布信息
 */
@Schema(description = "职位发布信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publish {

    /**
     * 发布ID，自动递增
     */
    @Schema(description = "发布ID，自动递增")
    private Long publishId;

    /**
     * 发布时间
     */
    @Schema(description = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    /**
     * 有效期
     */
    @Schema(description = "有效期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validTime;

    /**
     * 关联的职位ID
     */
    @Schema(description = "关联的职位ID")
    private Long jobId;
}
