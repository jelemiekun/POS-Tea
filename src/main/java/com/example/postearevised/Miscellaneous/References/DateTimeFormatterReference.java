package com.example.postearevised.Miscellaneous.References;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterReference {
    public static LocalDateTime localDateTime;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - hh:mm:ss a");

    public static DateTimeFormatter yearMonthDayFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd");
    public static DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy MMMM");
    public static DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
    public static DateTimeFormatter transactionIDFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
}
