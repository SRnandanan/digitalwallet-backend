package com.os.digitalwallet.service;

import com.os.digitalwallet.models.User;

public interface UserService {

    public String loginUser(User userData);

    public String logoutUser(String userSession);

    public String signUp(User userData);
}
