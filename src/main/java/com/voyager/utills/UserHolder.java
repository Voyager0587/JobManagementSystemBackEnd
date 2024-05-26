package com.voyager.utills;


import com.voyager.domain.pojo.User;

/**
 * 用户信息存储工具类
 * @author Voyager
 * @date 2024/05/03
 */
public class UserHolder {

    // 使用 ThreadLocal 存储用户信息，确保线程安全
    // 假设 User 是一个已经定义好了的实体类
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();


    /**
     * 将用户信息存入当前线程
     * @param user
     */
    public static void setInfoByToken(User user) {
        userThreadLocal.set(user);
    }

    /**
     * 从当前线程获取用户信息
     * @return {@link User}
     */
    public static User getInfoByToken() {
        return userThreadLocal.get();
    }


    /**
     * 清除当前线程中的用户信息
     */
    public static void clear() {
        userThreadLocal.remove();
    }
}
