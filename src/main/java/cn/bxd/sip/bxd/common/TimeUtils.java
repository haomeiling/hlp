package cn.bxd.sip.bxd.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtils {
    /**
     * 将yyyy-MM-dd 格式转换为 yyyyMMdd
     *
     * @param dateStr yyyy-MM-dd
     * @return yyyyMMdd
     * @throws ParseException
     */
    public static Integer transDateStr2Int(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = df.parse(dateStr);
            int dateInt = Integer.parseInt(df2.format(date));
            return dateInt;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 将yyyyMMdd转换为yyyy-MM-dd
     *
     * @param dateInt yyyyMMdd
     * @return yyyy-MM-dd
     * @throws Exception
     */
    public static String transDateInt2Str(int dateInt) {
        String tmp = String.valueOf(dateInt);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = df2.parse(tmp);
            String dateStr = df.format(date);
            return dateStr;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将yyyyMMdd转换为日期格式
     *
     * @param dateInt yyyyMMdd
     * @return 日期 yyyy-MM-dd
     * @throws Exception
     */
    public static Date transDateInt2Date(int dateInt) {
        String tmp = String.valueOf(dateInt);
        DateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = df2.parse(tmp);

            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将yyyy-MM-dd 格式转换为 yyyyMMdd
     *
     * @param dateStr yyyy-MM-dd
     * @return yyyyMMdd
     * @throws ParseException
     */
    public static Date transDateStr2Date(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(dateStr);
            return date;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss 格式转换为 yyyyMMddHHmmss
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss
     * @return yyyyMMddHHmmss
     * @throws ParseException
     */
    public static Long transDateStr2Long(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = df.parse(dateStr);
            Long dateLong = Long.parseLong(df2.format(date));
            return dateLong;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 将yyyyMMddHHmmss转换为yyyy-MM-dd HH:mm:ss
     *
     * @param dateLong yyyy-MM-dd HH:mm:ss
     * @return yyyyMMddHHmmss
     * @throws Exception
     */
    public static String transDateLong2Str(Long dateLong) {
        String tmp = String.valueOf(dateLong);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = df2.parse(tmp);
            String dateStr = df.format(date);
            return dateStr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 提取时间格式的 yyyyMMdd
     *
     * @param date 时间
     * @return yyyyMMdd
     */
    public static int transDate2Int(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(dateFormat.format(date));
    }

    /**
     * 提取时间格式的 yyyy-MM-dd
     *
     * @param date 时间
     * @return yyyy-MM-dd
     */
    public static String transDate2Str(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * 提取时间格式的 yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String transDate2Str2(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }


    public static void main(String[] args) throws ParseException {
        //System.out.println(transDateStr2Long("2015-08-28 14:02:25"));
        System.out.println(transDateInt2Date(20181228));
    }
}
