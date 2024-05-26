package com.voyager.controller;

import cn.hutool.json.JSONUtil;
import com.voyager.common.constant.JwtClaimsConstant;
import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.*;
import com.voyager.domain.pojo.ResponsiblePerson;
import com.voyager.domain.pojo.User;
import com.voyager.domain.vo.PersonLoginVO;
import com.voyager.domain.vo.UserLoginVO;
import com.voyager.service.ResponsiblePersonService;
import com.voyager.service.UserService;
import com.voyager.utills.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 负责人接口
 * @author Voyager
 * @date 2024/05/25
 */
@Tag(name = "负责人接口")
@RestController
@RequestMapping("/api/responsibleperson")
public class ResponsiblePersonController {

    @Autowired
    private ResponsiblePersonService responsiblePersonService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;


    /**
     * 登录
     * @param personLoginDTO
     * @return {@link Result }<{@link PersonLoginVO }>
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<PersonLoginVO> login(@RequestBody PersonLoginDTO personLoginDTO){
        ResponsiblePerson responsiblePerson = responsiblePersonService.login(personLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, responsiblePerson.getUserId());
        //获取impl中user对象，对比密码，之后查询是否有相关talent
        String token = jwtUtils.generateToken(claims, "user");
        User user = userService.findByUserId(responsiblePerson.getUserId());
        // 将用户信息存储在Redis中
        String userJson = JSONUtil.toJsonStr(user);
        stringRedisTemplate.opsForValue().set("login:user:" + user.getUserId(), userJson, 30, TimeUnit.MINUTES);
        PersonLoginVO personLoginVO = PersonLoginVO.builder()
                .id(responsiblePerson.getUserId())
                .name(responsiblePerson.getName())
                .token(token)
                .build();
        return Result.success(personLoginVO);
    }

    /**
     * 注册
     *
     * @return 包装插入操作结果的Result对象
     */
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody ResponsiblePersonRegisterDTO responsiblePersonRegisterDTO) {
        if (responsiblePersonService.insert(responsiblePersonRegisterDTO) == 1) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }

    /**
     * 根据负责人ID查询负责人信息
     *
     * @param personId 负责人id
     * @return 包装对应的ResponsiblePerson对象的Result对象
     */
    @Operation(summary = "根据负责人ID查询负责人信息")
    @GetMapping("/find/id/{personId}")
    public Result<ResponsiblePerson> findById(@Parameter(description = "负责人ID") @PathVariable int personId) {
        ResponsiblePerson responsiblePerson = responsiblePersonService.findById(personId);
        return Result.success(responsiblePerson);
    }

    /**
     * 根据用户ID查询负责人信息
     *
     * @param userId 用户ID
     * @return 包装对应的ResponsiblePerson对象的Result对象
     */
    @Operation(summary = "根据用户ID查询负责人信息")
    @Parameter(description = "用户ID")
    @GetMapping("/find/user/{userId}")
    public Result<ResponsiblePerson> findByUserId(@PathVariable int userId) {
        ResponsiblePerson responsiblePerson = responsiblePersonService.findByUserId(userId);
        return Result.success(responsiblePerson);
    }

    /**
     * 根据公司ID查询所有负责人信息
     *
     * @param companyId 公司ID
     * @return 包装对应的ResponsiblePerson对象列表的Result对象
     */
    @Operation(summary = "根据公司ID查询所有负责人信息")
    @GetMapping("/find/company/{companyId}")
    @Parameter(description = "公司ID")
    public Result<List<ResponsiblePerson>> findByCompanyId(@PathVariable int companyId) {
        List<ResponsiblePerson> responsiblePersonList = responsiblePersonService.findByCompanyId(companyId);
        return Result.success(responsiblePersonList);
    }

    /**
     * 添加新的负责人
     *
     * @param responsiblePersonRegisterDTO 新增负责人对象
     * @return 包装插入操作影响的行数的Result对象
     */
    @Operation(summary = "添加新的负责人")
    @PostMapping("/add")
    public Result<String> insert(@RequestBody ResponsiblePersonRegisterDTO responsiblePersonRegisterDTO) {
        responsiblePersonService.insert(responsiblePersonRegisterDTO);
        return Result.success("添加成功");
    }

    /**
     * 更新负责人信息
     *
     * @param responsiblePerson 更新后的负责人对象
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新负责人信息")
    @PutMapping("/update")
    public Result<String> update(@RequestBody ResponsiblePerson responsiblePerson) {
        responsiblePersonService.update(responsiblePerson);
        return Result.success("更新成功");
    }

    /**
     * 根据负责人ID删除负责人
     *
     * @param personId 负责人ID
     * @return 包装删除操作影响的行数的Result对象
     */
    @Operation(summary = "根据负责人ID删除负责人")
    @DeleteMapping("/delete/{personId}")
    @Parameter(description = "负责人ID")
    public Result<String> deleteById(@PathVariable int personId) {
        responsiblePersonService.deleteById(personId);
        return Result.success("删除成功");
    }

    /**
     * 分页查询负责人信息
     *
     * @param responsiblePersonPageQueryDTO 分页查询参数
     * @return {@link Result }<{@link PageResult }>
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询负责人信息")
    public Result<PageResult> page(ResponsiblePersonPageQueryDTO responsiblePersonPageQueryDTO) {
        return Result.success(responsiblePersonService.pageQuery(responsiblePersonPageQueryDTO));
    }
}
