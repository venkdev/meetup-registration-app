package com.k15t.pat.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegistrationUtils {

    public static String doBCrypt(String value) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(value);
    }
}
