package com.voyager.controller;

import com.voyager.domain.pojo.ApplicationReview;
import com.voyager.mapper.ApplicationReviewMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationReviewControllerTest {
    @Autowired
    ApplicationReviewMapper applicationReviewMapper;
    @Test
    void findByApplyId() {
        ApplicationReview byApplyId = applicationReviewMapper.findByApplyId(42);
        System.out.println("byApplyId = " + byApplyId);
    }

    @Test
    void findByJobId() {
    }

    @Test
    void findByIdNumber() {
    }

    @Test
    void findByStatus() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteByApplyId() {
    }

    @Test
    void deleteByJobId() {
    }

    @Test
    void page() {
//        Result<PageResult> page = applicationReviewController.page(new ApplicationReviewPageQueryDTO(
//                1,
//                10,
//                null,
//                null,
//                25L
//
//        ));
    }
}