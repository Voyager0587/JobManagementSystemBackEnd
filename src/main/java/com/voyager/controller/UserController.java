package com.voyager.controller;

import cn.hutool.json.JSONUtil;
import com.voyager.common.constant.JwtClaimsConstant;
import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.UserLoginDTO;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.dto.UserRegisterDTO;
import com.voyager.domain.pojo.User;
import com.voyager.domain.vo.UserLoginVO;
import com.voyager.service.UserService;
import com.voyager.utills.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口
 *
 * @author Voyager
 * @date 2024/05/25
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 包装对应的User对象的Result对象
     */
    @Operation(summary = "根据用户ID查询用户")
    @GetMapping("/findByUserId/{userId}")
    @Parameter(description = "用户ID")
    public Result<User> findByUserId(@PathVariable int userId) {
        User user = userService.findByUserId((long) userId);
        return Result.success(user);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 包装对应的User对象的Result对象
     */
    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/findByUsername/{username}")
    @Parameter(description = "用户名")
    public Result<User> findByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    /**
     * 注册
     *
     * @return 包装插入操作结果的Result对象
     */
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if (userService.insert(userRegisterDTO) == 1) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }

    /**
     * 登录
     *
     * @param userLoginDTO
     * @return {@link Result }<{@link UserLoginVO }>
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        User user = userService.login(userLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        //获取impl中user对象，对比密码，之后查询是否有相关talent
        String token = jwtUtils.generateToken(claims, "user");
        // 将用户信息存储在Redis中
        String userJson = JSONUtil.toJsonStr(user);
        stringRedisTemplate.opsForValue().set("login:user:" + user.getUserId(), userJson, 30, TimeUnit.MINUTES);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }

    /**
     * 更新用户信息
     *
     * @param user User对象
     * @return 包装更新操作结果的Result对象
     */
    @Operation(summary = "更新用户信息")
    @PutMapping("/update")
    public Result<String> update(@RequestBody User user) {
        if (userService.update(user) == 1) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    /**
     * 根据用户ID删除用户
     *
     * @param userId 用户ID
     * @return 包装删除操作结果的Result对象
     */
    @Operation(summary = "根据用户ID删除用户")
    @DeleteMapping("/delete/{userId}")
    @Parameter(description = "用户ID")
    public Result<String> deleteByUserId(@PathVariable int userId) {
        if (userService.deleteByUserId(userId) == 1) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 分页查询用户信息
     *
     * @param userPageQueryDTO 分页查询参数
     * @return {@link Result }<{@link PageResult }>
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户信息")
    public Result<PageResult> page(UserPageQueryDTO userPageQueryDTO) {
        return Result.success(userService.pageQuery(userPageQueryDTO));
    }
}
