package com.voyager.service;

import com.voyager.domain.pojo.Talent;

public interface TalentService {
    int insertTalent(Talent talent);
    int deleteByIdNumber(int userId);
    int updateTalent(Talent talent);
    Talent findByIdNumber(String idNumber);
    Talent findByName(String name);
    Talent findByUserId(int userId);
}
