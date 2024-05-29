package com.voyager.interceptor;

import com.voyager.domain.pojo.User;
import com.voyager.utills.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


/**
 * 人才用户身份验证
 * @author voyager
 * @date 2024/05/03
 */
@Component
public class TalentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //目标资源方法执行前执行。 返回true：放行    返回false：不放行
        User user = UserHolder.getInfoByToken();

        if (Objects.isNull(user)) {
            response.setStatus(401);
            return false;
        }
        return user.getUserType() == 0 || user.getUserType() == 2;
    }


    //目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ... ");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .... ");
    }
}