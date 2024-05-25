package com.voyager.domain.dto;

import lombok.Data;

@Data
public class TalentPageQueryDTO {
    private int pageIndex;
    private int pageSize;
    private String name;
    private String idNumber;
    private String phoneNumber;
}
