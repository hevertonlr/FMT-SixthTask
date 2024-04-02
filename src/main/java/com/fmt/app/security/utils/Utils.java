package com.fmt.app.security.utils;

public class Utils {
    public static String getParsedToken(String headerToken){
        return headerToken.split(" ")[1];
    }
}
