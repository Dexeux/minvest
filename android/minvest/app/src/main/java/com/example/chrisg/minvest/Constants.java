package com.example.chrisg.minvest;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chrisg on 11/12/16.
 */

public class Constants {
    public static final String WEB_URL = "http://173.255.232.42:80";

    private static String sessionID;
    public static String getSessionID(){
        return sessionID;
    }
}
