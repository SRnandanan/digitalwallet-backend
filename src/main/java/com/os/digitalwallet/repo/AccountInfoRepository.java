package com.os.digitalwallet.repo;

import com.os.digitalwallet.models.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
    public AccountInfo findByUserId(String userId);
}
