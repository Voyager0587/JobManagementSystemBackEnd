package com.voyager.service;

import com.voyager.common.result.PageResult;
import com.voyager.domain.dto.*;
import com.voyager.domain.pojo.Talent;
import com.voyager.domain.pojo.User;

public interface TalentService {
    int insertTalent(Talent talent);
    int deleteByIdNumber(int userId);
    int updateTalent(Talent talent);
    Talent findByIdNumber(String idNumber);
    Talent findByName(String name);
    Talent findByUserId(int userId);
    PageResult pageQuery(TalentPageQueryDTO talentPageQueryDTO);

    Talent login(PersonLoginDTO personLoginDTO);

    int insert(TalentRegisterDTO talentRegisterDTO);
}
