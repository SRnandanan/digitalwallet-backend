package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.PersonalInfo;
import com.os.digitalwallet.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @GetMapping("/get")
    public ResponseEntity<PersonalInfo> getPersonalInfo(@RequestParam(required = true) String userName){
        PersonalInfo response = personalInfoService.getPersonalInfo(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePersonalInfo( @RequestParam(required = true) String userName,
                                                      @RequestBody PersonalInfo personalInfo){
        String response = personalInfoService.updatePersonalInfo(personalInfo, userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
