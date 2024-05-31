package com.voyager.mapper;

import com.github.pagehelper.Page;
import com.voyager.domain.dto.ApplicationReviewQueryDTO;
import com.voyager.domain.pojo.ApplicationReview;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface ApplicationReviewMapper {

    /**
     * 根据申请ID查询申请信息
     *
     * @param applyId 申请id
     * @return {@link ApplicationReview }
     */
    @Select("SELECT * FROM ApplicationReview WHERE apply_id = #{applyId}")
    ApplicationReview findByApplyId(int applyId);

    /**
     * 根据职位ID查询审核和申请
     *
     * @param jobId 职位id
     * @return {@link List }<{@link ApplicationReview }>
     */
    @Select("SELECT * FROM ApplicationReview WHERE job_id = #{jobId}")
    List<ApplicationReview> findByJobId(int jobId);

    /**
     * 根据身份证号查询申请信息
     *
     * @param idNumber 身份证号
     * @return {@link List }<{@link ApplicationReview }>
     */
    @Select("SELECT * FROM ApplicationReview WHERE id_number = #{idNumber}")
    List<ApplicationReview> findByIdNumber(String idNumber);

    /**
     * 根据审核状态查询申请信息
     *
     * @param status 审核状态
     * @return {@link List }<{@link ApplicationReview }>
     */
    @Select("SELECT * FROM ApplicationReview WHERE review_status = #{status}")
    List<ApplicationReview> findByStatus(int status);

    /**
     * 插入新的申请信息
     *
     * @param applicationReview 申请
     */
    @Insert("INSERT INTO ApplicationReview(application_time, review_time, review_result, review_status, id_number, job_id) " +
            "VALUES(#{applicationTime}, #{reviewTime}, #{reviewResult}, #{reviewStatus}, #{idNumber}, #{jobId})")
    @Options(useGeneratedKeys = true, keyProperty = "applyId")
    int insert(ApplicationReview applicationReview);

    /**
     * 更新申请信息
     *
     * @param applicationReview 申请
     */
    int update(ApplicationReview applicationReview);

    /**
     * 删除申请信息
     *
     * @param applyId 申请id
     */
    @Delete("DELETE FROM ApplicationReview WHERE apply_id = #{applyId}")
    int deleteByApplyId(int applyId);

    /**
     * 根据职位ID删除相关的申请信息
     *
     * @param jobId 职位id
     */
    @Delete("DELETE FROM ApplicationReview WHERE job_id = #{jobId}")
    int deleteByJobId(Long jobId);


    /**
     * 根据输入参数查询
     *
     * @return {@link Page }<{@link ApplicationReview }>
     */
    Page<ApplicationReview> selectByCriteria(ApplicationReviewQueryDTO applicationReviewQueryDTO
    );

//
//    /**
//     * 根据职位ID统计待审核的申请数量
//     *
//     * @param jobId 职位id
//     * @return {@link Map }<{@link String }, {@link Object }>
//     */
//    @Select("CALL CountPendingReviewsBySpecificJob(#{jobId})")
//    Map<String, Object> countPendingReviewsBySpecificJob(int jobId);
    /**
     * 根据身份证号删除申请信息
     * @param idNumber 身份证号
     */
    @Delete("DELETE FROM ApplicationReview WHERE id_number = #{idNumber}")
    void deleteByIdNumber(String idNumber);
}

