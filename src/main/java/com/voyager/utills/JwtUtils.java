package com.voyager.utills;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author voyager
 * @date 2024/05/26
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private String secret;//密钥
    private long expiration;//过期时间


    /**
     * 生成token令牌
     *
     * @param claims  JWT第二部分负载 payload 中存储的内容
     * @param subject 主题(用户类型)
     * @return token
     */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setId(Claims.ID)//设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为令牌的唯一标识。
                .setSubject(subject)//设置主题,一般为用户类型
                .setIssuedAt(new Date())//设置签发时间
                .addClaims(claims)//设置负载
                .signWith(SignatureAlgorithm.HS256, secret)//设置签名算法
                .setExpiration(new Date(System.currentTimeMillis() + expiration))//设置令牌过期时间
                .compact();//生成令牌
    }

    /**
     * 解析token令牌
     *
     * @param token token令牌
     * @return 负载
     */
    public Claims parseToken(String token) {

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证token令牌
     *
     * @param token 令牌
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新Token
     *
     * @param token 旧的Token令牌
     * @return 新的Token令牌
     */
    public String refreshToken(String token) {
        try {
            // 解析旧的Token，获取负载信息
            Claims claims = parseToken(token);
            // 生成新的Token，设置过期时间和签名算法等参数
            return generateToken(claims, claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException("无法刷新令牌！", e);
        }
    }


    /**
     * 从令牌中获取主题信息
     *
     * @param token 令牌
     * @return 主题信息(用户类型)
     */
    public String getSubjectFromToken(String token) {
        try {
            // 解析令牌，获取负载信息
            Claims claims = parseToken(token);
            // 返回主题信息
            return claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("无法从令牌中获取主题。", e);
        }
    }

}