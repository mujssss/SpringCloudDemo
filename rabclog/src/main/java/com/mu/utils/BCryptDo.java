package com.mu.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptDo {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("mu1996421"));
    }
}
