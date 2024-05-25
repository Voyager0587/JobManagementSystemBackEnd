package com.voyager.mapper;

import com.github.pagehelper.Page;
import com.voyager.domain.dto.UserPageQueryDTO;
import com.voyager.domain.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return 对应的User对象
     */
    @Select("SELECT * FROM User WHERE user_id = #{userId}")
    User findByUserId(int userId);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 对应的User对象
     */
    @Select("SELECT * FROM User WHERE username = #{username}")
    User findByUsername(String username);

    /**
     * 插入新的User记录
     * @param user User对象，包含需要插入的数据
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO User (username, password, register_time, user_type) VALUES (#{username}, #{password}, #{registerTime}, #{userType})")
    int insert(User user);

    /**
     * 更新User记录
     * @param user User对象，包含需要更新的数据
     * @return 更新操作影响的行数
     */
    @Update("UPDATE User SET password = #{password}, user_type = #{userType} WHERE user_id = #{userId}")
    int update(User user);

    /**
     * 根据用户ID删除User记录
     * @param userId 用户ID
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM User WHERE user_id = #{userId}")
    int deleteByUserId(int userId);

    /**
     * 根据条件查询User记录
     * @param userPageQueryDTO 查询条件
     * @return {@link Page }<{@link User }>
     */
    Page<User> selectByCriteria(UserPageQueryDTO userPageQueryDTO);
}
