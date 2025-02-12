package com.os.digitalwallet.repo;

import com.os.digitalwallet.models.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
    public AccountInfo findByUserId(String userId);

    public AccountInfo findByUpi(String upi);

    @Modifying
    @Transactional
    @Query("UPDATE AccountInfo a SET a.balance = :balance WHERE a.upi = :upi")
    public int updateBalanceByUpi(String upi, int balance);
}
