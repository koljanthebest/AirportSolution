package util;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class TimeUtils {

    public static Timestamp toTimeStamp(String timeString) {
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = LocalTime.parse(timeString);
        LocalDateTime localDateTimeTruncated = now.with(time).truncatedTo(MINUTES);
        return Timestamp.valueOf(localDateTimeTruncated);
    }

    public static Timestamp toTimeStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime.truncatedTo(MINUTES));
    }

    public static LocalDateTime toLocalDateTime(Timestamp timeStamp) {
        return timeStamp.toLocalDateTime();
    }

    public static LocalTime toLocalTime(Timestamp timeStamp) {
        return timeStamp.toLocalDateTime().toLocalTime();
    }

    public static LocalDateTime toLocalDateTime(String timeString) {
        return LocalDateTime.now().with(LocalTime.parse(timeString));
    }

    public static LocalTime toLocalTime(String timeString) {
        return LocalTime.parse(timeString);
    }
}
