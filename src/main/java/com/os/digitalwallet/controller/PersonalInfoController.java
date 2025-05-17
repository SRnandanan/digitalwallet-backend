package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.PersonalInfo;
import com.os.digitalwallet.service.PersonalInfoService;
import com.os.digitalwallet.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @GetMapping
    public ResponseEntity<?> getPersonalInfo(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = true) String userName){
        if(!UserUtil.validateAuthenticatedUser(token, userName)){
            return new ResponseEntity<>("You do not have permissions to access this resource", HttpStatus.FORBIDDEN);
        }
        PersonalInfo response = personalInfoService.getPersonalInfo(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> updatePersonalInfo(
            @RequestParam(required = true) String userName,
            @RequestBody PersonalInfo personalInfo,
            @RequestHeader("Authorization") String token){
        if(!UserUtil.validateAuthenticatedUser(token, userName)){
            return new ResponseEntity<>("You do not have permissions to access this resource", HttpStatus.FORBIDDEN);
        }
        String response = personalInfoService.updatePersonalInfo(personalInfo, userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
