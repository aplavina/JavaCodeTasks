package com.aplavina.core;

import java.time.LocalDateTime;

public class TimeRepresentation {
    private static String represent(LocalDateTime time) {
        return String.format("%d:%d:%d##:%d:%d:%d:%d",
                time.getYear(),
                time.getMonth().getValue(),
                time.getDayOfMonth(),
                time.getHour(),
                time.getMinute(),
                time.getSecond(),
                time.getNano() / 1000000
        );
    }

    public static void main(String[] args) {
        System.out.println(represent(LocalDateTime.now()));
    }
}
