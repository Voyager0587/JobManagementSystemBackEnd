package com.voyager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

@Tag(name = "用户注册信息")
@Data
public class UserRegisterDTO implements Serializable {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
