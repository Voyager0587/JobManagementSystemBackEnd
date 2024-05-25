package com.voyager.domain.dto;

import lombok.Data;

@Data
public class PublishPageQueryDTO {
    private int pageIndex;
    private int pageSize;
    private Long jobId;
}
