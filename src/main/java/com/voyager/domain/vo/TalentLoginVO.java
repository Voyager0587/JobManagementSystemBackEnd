package com.voyager.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentLoginVO implements Serializable {
    @Schema(description="用户id")
    private Long id;

    @Schema(description="用户名")
    private String userName;

    @Schema(description="手机号")
    private String phone;

    @Schema(description="token")
    private String token;
}
