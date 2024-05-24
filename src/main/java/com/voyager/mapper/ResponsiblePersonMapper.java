package com.voyager.mapper;

import com.voyager.domain.pojo.ResponsiblePerson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResponsiblePersonMapper {

    /**
     * 根据personId查询ResponsiblePerson记录
     * @param personId 负责人ID
     * @return 对应的ResponsiblePerson对象
     */
    @Select("SELECT * FROM responsibleperson WHERE person_id = #{personId}")
    ResponsiblePerson findById(int personId);

    /**
     * 根据userId查询ResponsiblePerson记录
     * @param userId 用户ID
     * @return 对应的ResponsiblePerson对象
     */
    @Select("SELECT * FROM responsibleperson WHERE user_id = #{userId}")
    ResponsiblePerson findByUserId(int userId);

    /**
     * 根据companyId查询所有ResponsiblePerson记录
     * @param companyId 公司ID
     * @return 对应的ResponsiblePerson对象列表
     */
    @Select("SELECT * FROM responsibleperson WHERE company_id = #{companyId}")
    List<ResponsiblePerson> findByCompanyId(int companyId);

    /**
     * 插入新的ResponsiblePerson记录
     * @param responsiblePerson ResponsiblePerson对象，包含需要插入的数据
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO responsibleperson (user_id, name, phone_number, email, company_id) VALUES (#{userId}, #{name}, #{phoneNumber}, #{email}, #{companyId})")
    @Options(useGeneratedKeys = true, keyProperty = "personId")
    int insert(ResponsiblePerson responsiblePerson);

    /**
     * 更新ResponsiblePerson记录
     * @param responsiblePerson ResponsiblePerson对象，包含需要更新的数据
     * @return 更新操作影响的行数
     */
    @Update("UPDATE responsibleperson SET user_id = #{userId}, name = #{name}, phone_number = #{phoneNumber}, email = #{email}, company_id = #{companyId} WHERE person_id = #{personId}")
    int update(ResponsiblePerson responsiblePerson);

    /**
     * 根据personId删除ResponsiblePerson记录
     * @param personId 负责人ID
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM responsibleperson WHERE person_id = #{personId}")
    int deleteById(int personId);
}