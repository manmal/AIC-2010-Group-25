package aic2010.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Alex
 */
public class Log {

    public static void println(String message)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        System.out.println(sdf.format(new Date()));
        System.out.println(message);
    }
}
