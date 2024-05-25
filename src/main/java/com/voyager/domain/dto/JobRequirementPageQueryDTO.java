package com.voyager.domain.dto;

import lombok.Data;

@Data
public class JobRequirementPageQueryDTO {
    private int pageIndex;
    private int pageSize;
    private String jobName;
    private Long companyId;
}
