package com.voyager.domain.pojo;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类，用于存储用户信息
 */
@Schema(description = "用户信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final Character USER_TYPE_RESPONSIBLE_PERSON = 1;
    /**
     * 用户ID，主键，自动递增
     */
    @Schema(description = "用户ID，主键，自动递增")
    private Long userId;

    /**
     * 用户名，不能为空
     */
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名，不能为空")
    private String username;

    /**
     * 密码，不能为空
     */
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码，不能为空")
    private String password;

    /**
     * 注册时间，不能为空
     */
    @NotNull(message = "注册时间不能为空")
    @Schema(description = "注册时间，不能为空")
    private LocalDateTime registerTime;

    /**
     * 用户类型，O-求职者，1-HR
     */
    @Pattern(regexp = "[O1]", message = "用户类型必须为 'O' 或 '1'")
    @Schema(description = "用户类型，O-求职者，1-HR")
    private char userType;

}