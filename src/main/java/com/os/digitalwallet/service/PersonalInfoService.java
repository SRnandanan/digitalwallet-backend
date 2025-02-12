package com.os.digitalwallet.service;

import com.os.digitalwallet.models.PersonalInfo;
import com.os.digitalwallet.models.User;

public interface PersonalInfoService {

    public PersonalInfo getPersonalInfo(String userName);

    public String updatePersonalInfo(PersonalInfo userData, String userName);
}
