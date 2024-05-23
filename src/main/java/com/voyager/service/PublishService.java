package com.voyager.service;


import com.voyager.domain.pojo.Publish;

import java.util.List;

public interface PublishService {
    List<Publish> findByJobId(int jobId);
    Publish findByPublishId(int publishId);
    int insert(Publish publish);
    int update(Publish publish);
    int deleteByPublishId(int publishId);
}
