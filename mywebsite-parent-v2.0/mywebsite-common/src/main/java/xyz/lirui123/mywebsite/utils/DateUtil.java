package xyz.lirui123.mywebsite.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static String dateToStamp(String bgtime, String edtime) throws ParseException {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long from = simpleFormat.parse(bgtime).getTime();
        long to = simpleFormat.parse(edtime).getTime();
        long hour =(to - from) % nd / nh;
        long minute =(to - from) % nd % nh/nm;
        double hours=(double)hour;
        double minutes=(double)minute;
        int a=(int)hours;
        int b=(int)minutes;
        return a+":"+b;
    }


    public static String getNowTime(){

        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleFormat.format(date);
    }
    @Test
    public void test(){

        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleFormat.format(date);
        String  time = null;
        try {
            time = DateUtil.dateToStamp("2019-11-27 12:20:30",s);
            System.out.println(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        System.out.println(getNowTime());
    }
}
