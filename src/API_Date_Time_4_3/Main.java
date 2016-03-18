package API_Date_Time_4_3;

import java.time.*;

/**
 * 4.3 Date/Time API(JSR 310)
 * Java 8引入了新的Date-Time API(JSR 310)来改进时间、日期的处理。
 * <p>
 * 新的java.time包包含了所有关于日期、时间、时区、Instant（跟日期类似但是精确到纳秒）、
 * duration（持续时间）和时钟操作的类。
 * 新设计的API认真考虑了这些类的不变性（从java.util.Calendar吸取的教训），
 * 如果某个实例需要修改，则返回一个新的对象。
 */
public class Main {
    public static void main(String[] args) {
        //Clock可以替代System.currentTimeMillis()和TimeZone.getDefault()
        final Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.getZone());
        System.out.println(clock.instant());//跟日期类似但是精确到纳秒
        System.out.println(clock.millis());

        System.out.println("----------");

        //LocalDate仅仅包含ISO-8601日历系统中的日期部分；
        //LocalTime则仅仅包含该日历系统中的时间部分。这两个类的对象都可以使用Clock对象构建得到。
        // Get the local date and local time
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dateFromClock);
        // Get the local date and local time
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);

        System.out.println("----------");

        //LocalDateTime类包含了LocalDate和LocalTime的信息，但是不包含ISO-8601日历系统中的时区信息。
        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        System.out.println("----------");

        //如果你需要特定时区的data/time信息，则可以使用ZoneDateTime，
        //它保存有ISO-8601日期系统的日期和时间，而且有时区信息。
        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);

        System.out.println("----------");

        //最后看下Duration类，它持有的时间精确到秒和纳秒。
        //这使得我们可以很容易得计算两个日期之间的不同
        //Q:计算2014年4月16日和2015年4月16日之间的天数和小时数
        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2013, Month.JUNE, 22, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.now();

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }
}
