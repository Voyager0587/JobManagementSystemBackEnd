package com.voyager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.ApplicationReviewDTO;
import com.voyager.domain.dto.ApplicationReviewPageQueryDTO;
import com.voyager.domain.dto.ApplicationReviewQueryDTO;
import com.voyager.domain.dto.ApplicationReviewUpdateDTO;
import com.voyager.domain.pojo.ApplicationReview;
import com.voyager.domain.pojo.Talent;
import com.voyager.mapper.ApplicationReviewMapper;
import com.voyager.mapper.TalentMapper;
import com.voyager.service.ApplicationReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationReviewServiceImpl implements ApplicationReviewService {
    @Autowired
    private ApplicationReviewMapper applicationReviewMapper;
    @Autowired
    private TalentMapper talentMapper;

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

    @Transactional
    public int insert(ApplicationReviewDTO applicationReviewDTO) {
        Talent talent = talentMapper.findByUserId(applicationReviewDTO.getUserId());
        ApplicationReview applicationReview = new ApplicationReview();
        BeanUtil.copyProperties(applicationReviewDTO, applicationReview);
        applicationReview.setApplicationTime(LocalDateTime.now());
        applicationReview.setIdNumber(talent.getIdNumber());
        applicationReview.setReviewStatus(0);
        return applicationReviewMapper.insert(applicationReview);
    }

    public int update(ApplicationReviewUpdateDTO applicationReviewUpdateDTO) {
        ApplicationReview applicationReview = new ApplicationReview();
        BeanUtil.copyProperties(applicationReviewUpdateDTO, applicationReview);
        // 改为使用触发器自动填充
        // applicationReview.setReviewTime(LocalDateTime.now());
        return applicationReviewMapper.update(applicationReview);
    }

    public int deleteByApplyId(int applyId) {
        return applicationReviewMapper.deleteByApplyId(applyId);
    }

    @Transactional
    @Override
    public PageResult pageQuery(ApplicationReviewPageQueryDTO applicationReviewPageQueryDTO) {
        Talent talent = talentMapper.findByUserId(applicationReviewPageQueryDTO.getUserId());
        ApplicationReviewQueryDTO applicationReviewQueryDTO = new ApplicationReviewQueryDTO();
        BeanUtil.copyProperties(applicationReviewPageQueryDTO, applicationReviewQueryDTO);
        if (talent != null) {
            applicationReviewQueryDTO.setIdNumber(talent.getIdNumber());
        }
        PageHelper.startPage(applicationReviewQueryDTO.getPageIndex(), applicationReviewQueryDTO.getPageSize());
        Page<ApplicationReview> page = applicationReviewMapper.selectByCriteria(applicationReviewQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());

    }

    @Override
    public Map<String, Object> getPendingReviewsByJobId(int jobId) {
        return applicationReviewMapper.countPendingReviewsBySpecificJob(jobId);
    }

    public int deleteByJobId(int jobId) {
        return applicationReviewMapper.deleteByJobId((long) jobId);
    }
}
