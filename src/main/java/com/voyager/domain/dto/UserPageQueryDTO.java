package com.voyager.domain.dto;

import lombok.Data;

@Data
public class UserPageQueryDTO {
    private int pageIndex;
    private int pageSize;
    private String username;
    private Character userType;
}
