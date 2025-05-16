package com.os.digitalwallet.utils;

import com.os.digitalwallet.projections.UserProjection;
import com.os.digitalwallet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private final UserRepository userRepo;

    @Autowired
    public UserUtil(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserProjection validateUser(String userName) {
        UserProjection validateUser = userRepo.findIdByUserName(userName);
        if (validateUser == null) {
            throw new NullPointerException("User is null");
        }
        return validateUser;
    }

    public static boolean validateAuthenticatedUser(String token, String userName) {
        token = token.substring(7).trim();
        String loggedInUser = JwtUtil.extractUsername(token);
        return loggedInUser.equals(userName);
    }
}
