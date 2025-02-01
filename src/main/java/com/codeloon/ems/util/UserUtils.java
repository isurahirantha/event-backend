package com.codeloon.ems.util;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserUtils {

    private UserUtils() {
        throw new UnsupportedOperationException("UserUtils class cannot be instantiated");
    }

    public static String generateCustomUUID(String role, String userName) {

        String rolePrefix = switch (role.toUpperCase()) {
            case "ADMIN" -> "ADM";
            case "EMPLOYEE" -> "EMP";
            case "CLIENT" -> "CLI";
            default -> "USR";
        };

        //userName
        String namePrefix = userName.substring(0,3).toUpperCase();

        String timestamp = new SimpleDateFormat("yyMMdd").format(new Date());

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder randomPart = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            randomPart.append(characters.charAt(random.nextInt(characters.length())));
        }

        return rolePrefix + "-" + namePrefix + timestamp + randomPart;
    }
}
