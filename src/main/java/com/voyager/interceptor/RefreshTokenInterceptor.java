package com.voyager.interceptor;

import cn.hutool.json.JSONUtil;
import com.voyager.domain.pojo.User;
import com.voyager.utills.JwtUtils;
import com.voyager.utills.UserHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用来刷新token
 * @author voyager
 * @date 2024/05/03
 */
@Slf4j
@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;
    private final StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(JwtUtils jwtUtils, StringRedisTemplate stringRedisTemplate) {
        this.jwtUtils = jwtUtils;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1、从请求头中获取token
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorizationHeader)) {
            return true;
        }
        // 2.解析token
        Claims claims = jwtUtils.parseToken(authorizationHeader);
        if (Objects.isNull(claims)) {
            return true;
        }

        // 3.获取用户信息
        Integer userId = claims.get("userId", Integer.class);

        String userInfoJson = stringRedisTemplate.opsForValue().get("login:user:" + userId);
        if (StringUtils.isBlank(userInfoJson)) {
            return true;
        }


        // 4.刷新token
        String refreshToken = jwtUtils.refreshToken(authorizationHeader);

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", refreshToken);
        stringRedisTemplate.expire("login:user:" + userId, 30, TimeUnit.MINUTES);

        // 5.将用户信息存入本地线程方便获取
        User user = JSONUtil.toBean(userInfoJson, User.class);
        UserHolder.setInfoByToken(user);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 清理本地线程
        UserHolder.clear();
    }
}