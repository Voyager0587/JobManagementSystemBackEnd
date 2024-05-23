package com.voyager.domain.pojo;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User 实体类，用于存储用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 用户ID，主键，自动递增
     */
    private Long userId;

    /**
     * 用户名，不能为空
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码，不能为空
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 注册时间，不能为空
     */
    @NotNull(message = "注册时间不能为空")
    private LocalDateTime registerTime;

    /**
     * 用户类型，O-求职者，1-HR
     */
    private char userType;

    // 如果需要，可以添加getter和setter，但Lombok会自动生成
}
