package com.voyager.service;


import com.github.pagehelper.PageInfo;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.ApplicationReviewDTO;
import com.voyager.domain.dto.ApplicationReviewPageQueryDTO;
import com.voyager.domain.dto.ApplicationReviewUpdateDTO;
import com.voyager.domain.pojo.ApplicationReview;

import java.util.List;
import java.util.Map;

public interface ApplicationReviewService {
    ApplicationReview findByApplyId(int applyId);
    List<ApplicationReview> findByJobId(int jobId);
    List<ApplicationReview> findByIdNumber(String idNumber);
    List<ApplicationReview> findByStatus(int status);
    int deleteByJobId(int jobId);
     int insert(ApplicationReviewDTO applicationReviewDTO) ;
     int update(ApplicationReviewUpdateDTO applicationReviewUpdateDTO) ;
     int deleteByApplyId(int applyId);
    PageResult pageQuery(ApplicationReviewPageQueryDTO applicationReviewPageQueryDTO);
//     Map<String, Object> getPendingReviewsByJobId(int jobId);
}
