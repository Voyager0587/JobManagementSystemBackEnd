package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDeleteDTO {
    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    @Schema(description = "公司名称，不能为空")
    String companyName;
}
