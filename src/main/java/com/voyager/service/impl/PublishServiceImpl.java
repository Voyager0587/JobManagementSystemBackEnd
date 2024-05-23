package com.voyager.service.impl;

import com.voyager.domain.pojo.Publish;
import com.voyager.mapper.PublishMapper;
import com.voyager.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishMapper publishMapper;

    @Override
    public List<Publish> findByJobId(int jobId) {
        return publishMapper.findByJobId(jobId);
    }

    @Override
    public Publish findByPublishId(int publishId) {
        return publishMapper.findByPublishId(publishId);
    }

    @Override
    public int insert(Publish publish) {
        return publishMapper.insert(publish);
    }

    @Override
    public int update(Publish publish) {
        return publishMapper.update(publish);
    }

    @Override
    public int deleteByPublishId(int publishId) {
        return publishMapper.deleteByPublishId(publishId);
    }
}
