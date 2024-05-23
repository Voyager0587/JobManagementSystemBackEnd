package com.voyager.service.impl;

import com.voyager.domain.pojo.ApplicationReview;
import com.voyager.mapper.ApplicationReviewMapper;
import com.voyager.service.ApplicationReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationReviewServiceImpl implements ApplicationReviewService {
    @Autowired
    private ApplicationReviewMapper applicationReviewMapper;

    public ApplicationReview findByApplyId(int applyId) {
        return applicationReviewMapper.findByApplyId(applyId);
    }

    public List<ApplicationReview> findByJobId(int jobId) {
        return applicationReviewMapper.findByJobId(jobId);
    }

    public List<ApplicationReview> findByIdNumber(String idNumber) {
        return applicationReviewMapper.findByIdNumber(idNumber);
    }

    public List<ApplicationReview> findByStatus(int status) {
        return applicationReviewMapper.findByStatus(status);
    }

    public void insert(ApplicationReview applicationReview) {
        applicationReviewMapper.insert(applicationReview);
    }

    public void update(ApplicationReview applicationReview) {
         applicationReviewMapper.update(applicationReview);
    }

    public void deleteByApplyId(int applyId) {
         applicationReviewMapper.deleteByApplyId(applyId);
    }

    public void deleteByJobId(int jobId) {
         applicationReviewMapper.deleteByJobId(jobId);
    }
}
