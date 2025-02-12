package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.AccountInfo;
import com.os.digitalwallet.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-info")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping("/get")
    public ResponseEntity<AccountInfo> getAccountData(@RequestParam(required = true) String userName){
        AccountInfo response = accountInfoService.getAccountInfo(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAccountInfo(@RequestParam(required = true) String userName,
                                                    @RequestBody AccountInfo accountData){
        String response = accountInfoService.updateAccountInfo(accountData, userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
