package com.os.digitalwallet.service;

import com.os.digitalwallet.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> loginUser(User userData);

    String signUp(User userData);
}
