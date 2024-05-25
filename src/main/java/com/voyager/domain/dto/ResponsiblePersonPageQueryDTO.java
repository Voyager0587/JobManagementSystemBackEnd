package com.voyager.domain.dto;

import lombok.Data;

@Data
public class ResponsiblePersonPageQueryDTO {
    private int pageIndex;
    private int pageSize;
    private Long companyId;
    private String name;
    private String phoneNumber;
}
