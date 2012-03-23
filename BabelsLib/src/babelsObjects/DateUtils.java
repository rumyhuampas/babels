package babelsObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public static Date now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return cal.getTime();
    }
}