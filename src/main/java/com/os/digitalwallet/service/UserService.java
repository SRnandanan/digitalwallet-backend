package com.os.digitalwallet.service;

import com.os.digitalwallet.models.Response;
import com.os.digitalwallet.models.User;

public interface UserService {

    Response loginUser(User userData);

    String logoutUser(String userSession);

    String signUp(User userData);
}
