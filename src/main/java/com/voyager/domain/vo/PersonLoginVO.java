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
public class PersonLoginVO implements Serializable {
    @Schema(description="用户id")
    private Long id;

    @Schema(description="用户姓名")
    private String name;

    @Schema(description="token")
    private String token;
}
