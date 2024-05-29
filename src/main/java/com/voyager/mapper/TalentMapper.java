package com.voyager.mapper;

import com.github.pagehelper.Page;
import com.voyager.domain.dto.TalentPageQueryDTO;
import com.voyager.domain.dto.TalentUpdateDTO;
import com.voyager.domain.pojo.Talent;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TalentMapper {

    /**
     * 插入新的Talent记录，通过关联User表中的user_id
     * @param talent Talent对象，包含需要插入的数据
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO Talent (user_id, name, gender, birth_date, email, phone_number, id_number) " +
            "VALUES (#{userId}, #{name}, #{gender}, #{birthDate}, #{email}, #{phoneNumber}, #{idNumber})")
    int insertTalent(Talent talent);

    /**
     * 根据user_id删除User表中的记录
     * @param userId 用户ID
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM job_portal.talent WHERE user_id = #{userId}")
    int deleteByIdNumber(int userId);

    /**
     * 更新Talent表中的记录，通过关联User表中的user_id
     * @param talentUpdateDTO Talent对象，包含需要更新的数据
     * @return 更新操作影响的行数
     */
    int updateTalent(TalentUpdateDTO talentUpdateDTO);

    /**
     * 根据user_id查询Talent表中的记录
     * @param idNumber 身份证号
     * @return 对应的Talent对象
     */
    @Select("SELECT * FROM Talent WHERE id_number = #{idNumber}")
    Talent findByIdNumber(String idNumber);

    /**
     * 根据name查询Talent表中的记录
     * @param name 用户名
     * @return 对应的Talent对象
     */
    @Select("SELECT * FROM Talent WHERE name = #{name}")
    Talent findByName(String name);

    /**
     * 根据user_id查询Talent表中的记录
     * @param userId 用户ID
     * @return 对应的Talent对象
     */
    @Select("SELECT * FROM Talent WHERE user_id = #{userId}")
    Talent findByUserId(Long userId);

    Page<Talent> selectByCriteria(TalentPageQueryDTO talentPageQueryDTO);

    @Select("SELECT * FROM Talent WHERE phone_number = #{phone}")
    Talent findByPhone(String phone);

    @Delete("DELETE FROM Talent WHERE user_id = #{userId}")
    int deleteByUserId(int userId);
}
