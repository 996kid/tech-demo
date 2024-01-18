package jdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description LocalDateTest
 * @date 2023/12/22
 */

public class LocalDateTest {

    public static void main(String[] args) throws ParseException {
        // 给定本地时间
        LocalDateTime localDateTime = LocalDateTime.of(2022, 1, 1, 12, 0); // 举例：2022年1月1日12:00

        // 给定本地时区
        ZoneId localZone = ZoneId.of("Asia/Shanghai"); // 举例：上海时区

        // 将本地时间和时区信息合并为 ZonedDateTime
        ZonedDateTime localZonedDateTime = ZonedDateTime.of(localDateTime, localZone);

        // 将 ZonedDateTime 转换为 UTC 时间
        ZonedDateTime utcZonedDateTime = localZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

        // 获取 UTC 时间
        LocalDateTime utcLocalDateTime = utcZonedDateTime.toLocalDateTime();

        System.out.println("Local DateTime: " + localDateTime);
        System.out.println("UTC DateTime: " + utcLocalDateTime);

//        long daysBetween = ChronoUnit.DAYS.between(date.toInstant().atZone(ZoneOffset.UTC).toLocalDate(), LocalDate.now(ZoneOffset.UTC));
//        System.out.println(daysBetween);
//        // 假设给定的时间戳为 1642214400000（2022-01-16T00:00:00）
//        long givenTimestamp = 1642214400000L;
//
//        // 将给定的时间戳转换为 LocalDateTime（考虑时区）
//        Instant instant = Instant.ofEpochMilli(givenTimestamp);
//        ZoneId sourceTimeZone = ZoneId.of("Asia/Shanghai"); // 你的给定时区
//        ZonedDateTime sourceZonedDateTime = ZonedDateTime.ofInstant(instant, sourceTimeZone);
//
//        // 将带有时区信息的时间转换为 UTC 时间
//        ZonedDateTime utcZonedDateTime = sourceZonedDateTime.withZoneSameInstant(ZoneId.of("UTC+0"));
//
//        // 获取 UTC 时间戳
//        long utcTimestamp = utcZonedDateTime.toInstant().toEpochMilli();
//
//        System.out.println("Given Timestamp: " + givenTimestamp);
//        System.out.println("UTC Timestamp: " + utcTimestamp);


        test1();
    }

    private static void test1() {
        System.out.println(Instant.now().toEpochMilli());
    }
}
