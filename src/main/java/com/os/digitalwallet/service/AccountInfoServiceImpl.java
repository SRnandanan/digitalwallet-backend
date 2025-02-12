package com.os.digitalwallet.service;

import com.os.digitalwallet.models.AccountInfo;
import com.os.digitalwallet.projections.UserProjection;
import com.os.digitalwallet.repo.AccountInfoRepository;
import com.os.digitalwallet.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoServiceImpl implements  AccountInfoService{

    private final UserUtil userUtil;

    @Autowired
    public AccountInfoServiceImpl(UserUtil util){
        this.userUtil = util;
    }

    @Autowired
    private AccountInfoRepository accountRepo;

    @Override
    public AccountInfo getAccountInfo(String userName) {
        UserProjection user = userUtil.validateUser(userName);
        return accountRepo.findByUserId(user.getUserId());
    }

    @Override
    public String updateAccountInfo(AccountInfo accountInfo, String userName) {
        UserProjection user = userUtil.validateUser(userName);
        accountInfo.setUserId(user.getUserId());
        accountRepo.save(accountInfo);
        return "Account updated successfully";
    }
}
