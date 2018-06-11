package com.winter.utils;

import java.util.UUID;

public class StringUtil {

    public final static String winUserSession = "winUserSession";

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
