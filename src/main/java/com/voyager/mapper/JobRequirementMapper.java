package com.voyager.mapper;
import com.github.pagehelper.Page;
import com.voyager.domain.dto.JobRequirementPageQueryDTO;
import com.voyager.domain.pojo.JobRequirement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JobRequirementMapper {

    /**
     * 插入新的JobRequirement记录
     * @param jobRequirement JobRequirement对象，包含需要插入的数据
     * @return 插入操作影响的行数
     */
    @Insert("INSERT INTO jobrequirement (job_id, job_name, job_description, number_required, company_id) " +
            "VALUES (null, #{jobName}, #{jobDescription}, #{numberRequired}, #{companyId})")
    int insertJobRequirement(JobRequirement jobRequirement);

    /**
     * 根据职位名称和公司ID删除JobRequirement记录
     * @param jobName 职位名称
     * @param companyId 公司ID
     * @return 删除操作影响的行数
     */
    @Delete("DELETE FROM jobrequirement WHERE job_name = #{jobName} AND company_id = #{companyId}")
    int deleteJobRequirementByNameAndCompanyId(@Param("jobName") String jobName, @Param("companyId") int companyId);

    /**
     * 根据职位名称和公司ID查询JobRequirement记录
     * @param jobName 职位名称
     * @param companyId 公司ID
     * @return 对应的JobRequirement对象
     */
    @Select("SELECT * FROM jobrequirement WHERE job_name = #{jobName} AND company_id = #{companyId}")
    JobRequirement findJobRequirementByNameAndCompanyId( String jobName, int companyId);

    /**
     * 更新JobRequirement的number_required字段
     * @param jobName 职位名称
     * @param companyId 公司ID
     * @param numberRequired 更新后的需求人数
     * @return 更新操作影响的行数
     */
    @Update("UPDATE jobrequirement SET number_required = #{numberRequired} WHERE job_name = #{jobName} AND company_id = #{companyId}")
    int updateJobRequirementNumberRequired(@Param("jobName") String jobName, @Param("companyId") int companyId, @Param("numberRequired") int numberRequired);

    /**
     * 更新JobRequirement的job_description字段
     * @param jobName 职位名称
     * @param companyId 公司ID
     * @param jobDescription 更新后的职位描述
     * @return 更新操作影响的行数
     */
    @Update("UPDATE jobrequirement SET job_description = #{jobDescription} WHERE job_name = #{jobName} AND company_id = #{companyId}")
    int updateJobRequirementDescription(@Param("jobName") String jobName, @Param("companyId") int companyId, @Param("jobDescription") String jobDescription);

    Page<JobRequirement> selectByCriteria(JobRequirementPageQueryDTO jobRequirementPageQueryDTO);

    @Delete("DELETE FROM jobrequirement WHERE company_id = #{companyId}")
    void deleteJobRequirementsCompanyId(Long companyId);

    @Select("SELECT * FROM jobrequirement WHERE company_id = #{companyId}")
    List<JobRequirement> findJobRequirementByCompanyId(Long companyId);

    @Delete("DELETE FROM jobrequirement WHERE job_id = #{jobId}")
    int deleteJobRequirementsByJobId(long jobId);
}
