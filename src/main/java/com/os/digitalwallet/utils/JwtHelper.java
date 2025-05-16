package com.os.digitalwallet.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {

    private static JwtUtil jwtUtil;

    @Autowired
    public JwtHelper(JwtUtil jwtUtil) {
        JwtHelper.jwtUtil = jwtUtil;
    }

    public static String getSecretKey() {
        return jwtUtil.getSecretKey();
    }
}

