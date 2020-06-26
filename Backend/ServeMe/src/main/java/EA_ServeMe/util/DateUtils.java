package EA_ServeMe.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date asDate(LocalDate ld){
        return  Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime ldt){
        return  Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date){
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static  LocalDateTime asLocalDateTime(Date date){
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String asString(Date data,int t) {
        if (t==1){
            LocalDateTime ldt = DateUtils.asLocalDateTime(data);
            String day = ldt.getDayOfMonth()+"";
            String month = ldt.getMonth()+"";
            String year = ldt.getYear()+"";
            String hour = ldt.getHour()+"";
            String minute = ldt.getMinute()+"";

            String res = day + "/" + month + "/" + year + "-" + hour + ":" + minute;
            return res;
        }
        else{
            LocalDate ld = DateUtils.asLocalDate(data);
            String day = ld.getDayOfMonth()+"";
            String month = ld.getMonth()+"";
            String year = ld.getYear()+"";

            String res = day + "/" + month + "/" + year;
            return res;
        }
    }

    public static Date toDate(String str) {
//      DD/MM/YY-hh:mm to Date
//        str = "27/06/2020-18:07";
        String[] split = str.split("-");
        String data = split[0];
        String time = split[1];

//      Take care of date
        split = data.split("/");
        int day = Integer.valueOf(split[0]);
        int month = Integer.valueOf(split[1]);
        int year = Integer.valueOf(split[2]);

//      Take care of hour and minutes
        split = time.split(":");
        int hour = Integer.valueOf(split[0]);
        int minute = Integer.valueOf(split[1]);

//       Create the date
        LocalDateTime ldt = LocalDateTime.of(year,month,day,hour,minute);
        return DateUtils.asDate(ldt);
    }
}
