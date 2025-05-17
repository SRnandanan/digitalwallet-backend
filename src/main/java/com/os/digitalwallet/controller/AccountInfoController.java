package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.AccountInfo;
import com.os.digitalwallet.service.AccountInfoService;
import com.os.digitalwallet.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-info")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping
    public ResponseEntity<?> getAccountData(
            @RequestParam(required = true) String userName,
            @RequestHeader("Authorization") String token){
        if(!UserUtil.validateAuthenticatedUser(token, userName)){
            return new ResponseEntity<>("You do not have permissions to access this resource", HttpStatus.FORBIDDEN);
        }
        AccountInfo response = accountInfoService.getAccountInfo(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> updateAccountInfo(
            @RequestParam(required = true) String userName,
            @RequestHeader("Authorization") String token,
            @RequestBody AccountInfo accountData){
        if(!UserUtil.validateAuthenticatedUser(token, userName)){
            return new ResponseEntity<>("You do not have permissions to access this resource", HttpStatus.FORBIDDEN);
        }
        String response = accountInfoService.updateAccountInfo(accountData, userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
