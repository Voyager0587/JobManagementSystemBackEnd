package com.voyager.domain.dto;

import lombok.Data;

@Data
public class CompanyPageQueryDTO {
    private int pageIndex;
    private int pageSize;
    private String companyName;
}
