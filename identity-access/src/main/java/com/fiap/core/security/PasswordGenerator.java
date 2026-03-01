package com.fiap.core.security;

public class PasswordGenerator {

//    private static final String CHARS =
//            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//    private static final int SIZE = 10;
//
//    private static final SecureRandom random = new SecureRandom();
//
//    public static String generate() {
//        StringBuilder sb = new StringBuilder(SIZE);
//        for (int i = 0; i < SIZE; i++) {
//            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
//        }
//        return sb.toString();
//    }

    private static final String DEFAULT_PASSWORD = "senha123";

    public static String generate() {
        return DEFAULT_PASSWORD;
    }
}
