package com.voyager.controller;

import cn.hutool.json.JSONUtil;
import com.voyager.common.constant.JwtClaimsConstant;
import com.voyager.common.result.PageResult;
import com.voyager.common.result.Result;
import com.voyager.domain.dto.PersonLoginDTO;
import com.voyager.domain.dto.TalentPageQueryDTO;
import com.voyager.domain.dto.TalentRegisterDTO;
import com.voyager.domain.dto.TalentUpdateDTO;
import com.voyager.domain.pojo.Talent;
import com.voyager.domain.pojo.User;
import com.voyager.domain.vo.PersonLoginVO;
import com.voyager.service.TalentService;
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
 * 人才信息接口
 *
 * @author Voyager
 * @date 2024/05/25
 */
@Tag(name = "人才信息接口")
@RestController
@RequestMapping("/api/talent")
public class TalentController {

    @Autowired
    private TalentService talentService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;


    /**
     * 登录
     *
     * @param personLoginDTO
     * @return {@link Result }<{@link PersonLoginVO }>
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<PersonLoginVO> login(@RequestBody PersonLoginDTO personLoginDTO) {
        Talent talent = talentService.login(personLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, talent.getUserId());
        //获取impl中user对象，对比密码，之后查询是否有相关talent
        String token = jwtUtils.generateToken(claims, "user");
        // 将用户信息存储在Redis中
        User user = userService.findByUserId(talent.getUserId());
        String userJson = JSONUtil.toJsonStr(user);
        stringRedisTemplate.opsForValue().set("login:user:" + user.getUserId(), userJson, 30, TimeUnit.MINUTES);
        PersonLoginVO personLoginVO = PersonLoginVO.builder()
                .id(talent.getUserId())
                .name(talent.getName())
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
    public Result<String> register(@RequestBody TalentRegisterDTO talentRegisterDTO) {
        if (talentService.insert(talentRegisterDTO) == 1) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }


    /**
     * 添加人才信息
     *
     * @param talent 待添加的Talent对象，包含人才详细信息
     * @return 包装插入操作影响的行数的Result对象
     */
    @Operation(summary = "添加人才信息")
    @PostMapping("/add")
    public Result<String> addTalent(@RequestBody Talent talent) {
        if (talentService.insertTalent(talent) == 1) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    /**
     * 根据用户ID删除人才信息
     *
     * @param userId 用户ID，用于定位要删除的人才记录
     * @return 包装删除操作影响的行数的Result对象
     */
    @Operation(summary = "根据用户ID级联删除人才信息")
    @Parameter(name = "userId", description = "用户ID")
    @PostMapping("/delete/{userId}")
    public Result<String> deleteTalent(@PathVariable int userId) {
        if (talentService.deleteByUserId(userId) == 1) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 更新人才信息
     *
     * @param talentUpdateDTO 更新后的Talent对象，包含要修改的信息
     * @return 包装更新操作影响的行数的Result对象
     */
    @Operation(summary = "更新人才信息")
    @PostMapping("/update")
    public Result<String> updateTalent(@RequestBody TalentUpdateDTO talentUpdateDTO) {
        if (talentService.updateTalent(talentUpdateDTO) == 1)
            return Result.success("更新成功");
        return Result.error("更新失败");
    }

    /**
     * 根据身份证号查找人才信息
     *
     * @param idNumber 身份证号，用于查询特定人才
     * @return 包装与身份证号匹配的Talent对象的Result对象
     */
    @Operation(summary = "根据身份证号查找人才信息")
    @Parameter(name = "idNumber", description = "身份证号")
    @GetMapping("/find/idNumber/{idNumber}")
    public Result<Talent> findByIdNumber(@PathVariable String idNumber) {
        Talent talent = talentService.findByIdNumber(idNumber);
        return Result.success(talent);
    }

    /**
     * 根据姓名查找人才信息
     *
     * @param name 姓名，用于查询人才
     * @return 包装与姓名匹配的Talent对象的Result对象
     */
    @Operation(summary = "根据姓名查找人才信息")
    @Parameter(name = "name", description = "姓名")
    @GetMapping("/find/name/{name}")
    public Result<Talent> findByName(@PathVariable String name) {
        Talent talent = talentService.findByName(name);
        return Result.success(talent);
    }

    /**
     * 根据用户ID查找人才信息
     *
     * @param userId 用户ID，用于查询人才
     * @return 包装与用户ID匹配的Talent对象的Result对象
     */
    @Operation(summary = "根据用户ID查找人才信息")
    @Parameter(name = "userId", description = "用户ID")
    @GetMapping("/find/userId/{userId}")
    public Result<Talent> findByUserId(@PathVariable int userId) {
        Talent talent = talentService.findByUserId(userId);
        return Result.success(talent);
    }


    /**
     * 分页查询人才信息
     *
     * @param talentPageQueryDTO 分页查询参数
     * @return {@link Result }<{@link PageResult }>
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询人才信息")
    public Result<PageResult> page(TalentPageQueryDTO talentPageQueryDTO) {
        return Result.success(talentService.pageQuery(talentPageQueryDTO));
    }
}
