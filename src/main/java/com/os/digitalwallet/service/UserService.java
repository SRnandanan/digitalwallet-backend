package com.os.digitalwallet.service;

import com.os.digitalwallet.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<String> loginUser(User userData);

    public String logoutUser(String userSession);

    public String signUp(User userData);
}
