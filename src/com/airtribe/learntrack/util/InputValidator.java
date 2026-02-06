package com.airtribe.learntrack.util;

public class InputValidator {

    private InputValidator(){};

    public static boolean isValidString(String str){
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidMenuOption(int option, int min, int max) {
        return option >= min && option <= max;
    }
}
