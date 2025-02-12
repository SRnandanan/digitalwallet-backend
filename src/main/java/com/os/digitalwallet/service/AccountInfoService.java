package com.os.digitalwallet.service;

import com.os.digitalwallet.models.AccountInfo;

public interface AccountInfoService {

    public AccountInfo getAccountInfo(String userName);

    public String updateAccountInfo(AccountInfo accountInfo, String userName);


}
