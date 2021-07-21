package cargarregistros.utils;

import monitor.Registro;

import java.util.Calendar;
import java.util.Date;

public class RecordDaysControl {

    public static int differenceDays(Registro f, Date date){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(f.getFecha());
        int dayRecord = c1.get(Calendar.DAY_OF_MONTH);
        long dayRecordMillis = c1.getTimeInMillis();
        c1.setTime(date);
        int dayOfDate = c1.get(Calendar.DAY_OF_MONTH);
        long dayOfDateMillis = c1.getTimeInMillis();
        int daysDifference = dayOfDate - dayRecord;
        if(daysDifference < 0 ){
            long dayMilliseconds = 86400000;
            long difference = dayOfDateMillis - dayRecordMillis;
            return  (int)(difference/dayMilliseconds);
        }else return daysDifference;
    }
}
