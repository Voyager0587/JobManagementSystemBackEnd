package com.voyager.domain.pojo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Education 实体类，用于存储教育信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    /**
     * 教育信息ID，自动递增
     */
    private Long educationId;

    /**
     * 学校名称，不能为空
     */
    private String schoolName;

    /**
     * 专业
     */
    private String major;

    /**
     * 入学日期
     */
    private LocalDate enrollmentDate;

    /**
     * 毕业日期
     */
    private LocalDate graduationDate;

    /**
     * 关联的人才身份证号
     */
    private Integer idNumber;

    // 如果需要，可以添加getter和setter，但Lombok会自动生成
}
