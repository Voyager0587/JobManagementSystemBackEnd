package com.voyager.mapper;

import com.github.pagehelper.Page;
import com.voyager.domain.dto.EducationPageQueryDTO;
import com.voyager.domain.pojo.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EducationMapper {

    /**
     * 插入新的教育信息记录
     * @param education 教育信息对象
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO Education (school_name, major, enrollment_date, graduation_date, id_number) " +
            "VALUES (#{schoolName}, #{major}, #{enrollmentDate}, #{graduationDate}, #{idNumber})")
    int insertEducation(Education education);

    /**
     * 根据教育信息ID删除记录
     * @param educationId 教育信息ID
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM Education WHERE education_id = #{educationId}")
    int deleteEducationById(int educationId);

    /**
     * 更新教育信息记录
     * @param education 教育信息对象
     * @return 更新操作影响的行数
     */
    int updateEducation(Education education);

    /**
     * 根据教育信息ID查询记录
     * @param educationId 教育信息ID
     * @return 对应的教育信息对象
     */
    @Select("SELECT * FROM Education WHERE education_id = #{educationId}")
    Education findEducationById(int educationId);

    /**
     * 根据身份证号查询所有教育信息记录
     * @param idNumber 人才身份证号
     * @return 对应的教育信息对象列表
     */
    @Select("SELECT * FROM Education WHERE id_number = #{idNumber}")
    List<Education> findEducationByIdNumber(String idNumber);

    Page<Education> selectByCriteria(EducationPageQueryDTO educationPageQueryDTO);

    @Delete("DELETE FROM Education WHERE id_number = #{idNumber}")
    void deleteEducationByIdNumber(String idNumber);
}
