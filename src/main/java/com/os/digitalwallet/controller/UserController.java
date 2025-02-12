package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.User;
import com.os.digitalwallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * This service is used to create new accounts and store user information
     */
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User userData){
        String response = userService.loginUser(userData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody User userData){
        String response = userService.signUp(userData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}