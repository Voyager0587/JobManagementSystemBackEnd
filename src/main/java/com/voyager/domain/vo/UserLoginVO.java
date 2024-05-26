package com.voyager.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements Serializable {
    @Schema(description="用户id")
    private Long id;


    @Schema(description="用户名")
    private String username;

    @Schema(description="token")
    private String token;
}
