package com.amanaggarwal1.instafire.Utils;


public class StringManipulation {

    public static String expandUsername(String username){
        return username.replaceAll(".", " ");
    }

    public static String condenseUsername(String username){
        return username.replaceAll(" ", ".");
    }

    public static String removeSpaces(String string){
        return string.trim().replaceAll("\\s+", " ");
    }

    public static boolean isAlpha(String s) {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c == ' ')) {
                return false;
            }
        }
        return true;
    }
}
