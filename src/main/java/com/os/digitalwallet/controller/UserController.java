package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.Response;
import com.os.digitalwallet.models.User;
import com.os.digitalwallet.service.UserService;
import com.os.digitalwallet.utils.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData){
        Response response = userService.loginUser(userData);
        if (response.getStatusCode() == HttpStatus.OK) {
            String token = jwtUtil.generateToken(userData.getUserName());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(response.getMessage(), response.getStatusCode());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUpUser(@RequestBody User userData){
        String response = userService.signUp(userData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}