package com.os.digitalwallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.os.digitalwallet.models.PersonalInfo;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

     public PersonalInfo findByUserId(String userId);
}
