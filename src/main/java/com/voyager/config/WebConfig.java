package com.voyager.config;

import com.voyager.interceptor.LoginInterceptor;
import com.voyager.interceptor.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author voyager
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RefreshTokenInterceptor refreshTokenInterceptor;

    private final LoginInterceptor loginInterceptor;

    public WebConfig(RefreshTokenInterceptor refreshTokenInterceptor, LoginInterceptor loginInterceptor) {
        this.refreshTokenInterceptor = refreshTokenInterceptor;
        this.loginInterceptor = loginInterceptor;
    }


    @Override
    public void addInterceptors( InterceptorRegistry registry) {
        registry.addInterceptor(refreshTokenInterceptor)
                .addPathPatterns("/**").order(0);//设置拦截器对所有路径生效,执行顺序为0

        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/captcha/graph-captcha","/api/user/register")//排除用户登录获取验证码接口
                .excludePathPatterns("/","/login","*.html","/images/**","/doc.html","/api/user/login","/api/responsibleperson/login","/api/user/register"
                        ,"/webjars/**","/swagger-resources","/swagger-resources/**","/v3/**")//排除登录获取静态资源、swagger接口文档等。
                .order(1);//设置拦截器对所有路径生效,执行顺序为1

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有路径生效
                .allowedOrigins("*") //允许所有源地址
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
                .allowedHeaders("*"); // 允许的请求头
    }
}
