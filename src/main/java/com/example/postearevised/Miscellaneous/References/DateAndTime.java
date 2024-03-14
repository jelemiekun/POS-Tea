package com.example.postearevised.Miscellaneous.References;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.ONE_SECOND;

public class DateAndTime {
    public static LocalDateTime localDateTime;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - hh:mm:ss a");
    String formattedDateTime = localDateTime.format(formatter);

    public static void createAndStartDaemonThreadForDateAndTime() {
        Thread daemonThreadForDateAndTime = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    localDateTime = LocalDateTime.now();
                    try {
                        Thread.sleep(ONE_SECOND);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        daemonThreadForDateAndTime.setDaemon(true);

        daemonThreadForDateAndTime.start();
    }
}
