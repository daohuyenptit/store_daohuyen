package com.example.daohuyen.constants;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String getTimeAndDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR) +
                ":" +
                calendar.get(Calendar.MINUTE) +
                "  " +
                calendar.get(Calendar.DAY_OF_MONTH) +
                "/" +

                (calendar.get(Calendar.MONTH)+1) +
                "/" +
                calendar.get(Calendar.YEAR);
    }


    public static long milliseconds(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            Date mDate = sdf.parse(date);
            return mDate.getTime();
        } catch (ParseException ignored) {
            return 0;
        }
    }
    public static Timestamp convertDate(String dateAsString){
        //2018-11-10
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            // you can change format of date
            Date date = formatter.parse(dateAsString);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }


    }
    public static Timestamp convertDate2(String dateAsString){
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // you can change format of date
            Date date = formatter.parse(dateAsString);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }


    }

}
