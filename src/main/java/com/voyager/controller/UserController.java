package com.voyager.controller;

import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.pojo.User;
import com.voyager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 * @author Voyager
 * @date 2024/05/25
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        User user = userService.findByUserId(userId);
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
     * 插入新的用户
     *
     * @param user User对象
     * @return 包装插入操作结果的Result对象
     */
    @Operation(summary = "插入新的用户")
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody User user) {
        userService.insert(user);
        return Result.success("插入成功");
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
        userService.update(user);
        return Result.success("更新成功");
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
        userService.deleteByUserId(userId);
        return Result.success("删除成功");
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
