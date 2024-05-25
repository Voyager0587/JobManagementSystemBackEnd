package com.voyager.domain.dto;

import lombok.Data;

@Data
public class ApplicationReviewPageQueryDTO {
    //int pageIndex, int pageSize, Integer reviewStatus, Integer jobId, String idNumber
    private int pageIndex;
    private int pageSize;
    private Integer reviewStatus;
    private Integer jobId;
    private String idNumber;
}
