package com.voyager.service.impl;

import com.voyager.domain.pojo.ResponsiblePerson;
import com.voyager.mapper.ResponsiblePersonMapper;
import com.voyager.service.ResponsiblePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsiblePersonServiceImpl implements ResponsiblePersonService {

    @Autowired
    private ResponsiblePersonMapper responsiblePersonMapper;

    @Override
    public ResponsiblePerson findById(int personId) {
        return responsiblePersonMapper.findById(personId);
    }

    @Override
    public ResponsiblePerson findByUserId(int userId) {
        return responsiblePersonMapper.findByUserId(userId);
    }

    @Override
    public List<ResponsiblePerson> findByCompanyId(int companyId) {
        return responsiblePersonMapper.findByCompanyId(companyId);
    }

    @Override
    public int insert(ResponsiblePerson responsiblePerson) {
        return responsiblePersonMapper.insert(responsiblePerson);
    }

    @Override
    public int update(ResponsiblePerson responsiblePerson) {
        return responsiblePersonMapper.update(responsiblePerson);
    }

    @Override
    public int deleteById(int personId) {
        return responsiblePersonMapper.deleteById(personId);
    }
}
