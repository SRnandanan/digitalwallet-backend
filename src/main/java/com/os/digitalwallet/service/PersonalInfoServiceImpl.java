package com.os.digitalwallet.service;

import com.os.digitalwallet.models.PersonalInfo;
import com.os.digitalwallet.models.User;
import com.os.digitalwallet.projections.UserProjection;
import com.os.digitalwallet.repo.PersonalInfoRepository;
import com.os.digitalwallet.repo.UserRepository;
import com.os.digitalwallet.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService{

    private final UserUtil userUtil;

    @Autowired
    public PersonalInfoServiceImpl(UserUtil util){
        this.userUtil = util;
    }

    @Autowired
    private PersonalInfoRepository personalInfoRepo;

    @Override
    public PersonalInfo getPersonalInfo(String userName) {
        UserProjection validateUser = userUtil.validateUserPresent(userName);
        return personalInfoRepo.findByUserId(validateUser.getUserId());
    }

    @Override
    public String updatePersonalInfo(PersonalInfo userData, String userName) {
        UserProjection validateUser = userUtil.validateUserPresent(userName);
        userData.setUserId(validateUser.getUserId());
        PersonalInfo savedPersonalInfo = personalInfoRepo.save(userData);
        return "Personal Info updated successfully!";

    }

}
