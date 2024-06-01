package com.voyager.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationReviewMapperTest {

    @Autowired
    ApplicationReviewMapper applicationReviewMapper;
    @Test
    void countPendingReviews() {
        System.out.println(applicationReviewMapper.countPendingReviews());
    }
}