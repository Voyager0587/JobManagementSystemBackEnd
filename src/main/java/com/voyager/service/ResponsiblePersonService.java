package com.voyager.service;


import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.PersonLoginDTO;
import com.voyager.domain.dto.ResponsiblePersonPageQueryDTO;
import com.voyager.domain.dto.ResponsiblePersonRegisterDTO;
import com.voyager.domain.dto.UserLoginDTO;
import com.voyager.domain.pojo.ResponsiblePerson;

import java.util.List;

public interface ResponsiblePersonService {
    ResponsiblePerson findById(int personId);
    ResponsiblePerson findByUserId(int userId);
    List<ResponsiblePerson> findByCompanyId(int companyId);
    int insert(ResponsiblePersonRegisterDTO responsiblePersonRegisterDTO);
    int update(ResponsiblePerson responsiblePerson);
    int deleteById(int personId);
    PageResult pageQuery(ResponsiblePersonPageQueryDTO responsiblePersonPageQueryDTO);
    int deleteByUserId(Long userId);
    ResponsiblePerson login(PersonLoginDTO personLoginDTO);
}
