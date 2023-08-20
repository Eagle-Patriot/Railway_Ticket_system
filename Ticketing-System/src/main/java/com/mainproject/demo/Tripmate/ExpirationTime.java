package com.mainproject.demo.Tripmate;

import java.util.Calendar;
import java.util.Date;

public class ExpirationTime {
    private static final int EXPIRATION_TIME = 1;

    public static Date getExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
