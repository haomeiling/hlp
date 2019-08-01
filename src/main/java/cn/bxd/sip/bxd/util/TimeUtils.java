package cn.bxd.sip.bxd.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static Calendar getTodayZeroCalendar() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static String second2Time(long seconds) {
        long hours = TimeUnit.SECONDS.toHours(seconds);
        long minis = TimeUnit.SECONDS.toMinutes(seconds - TimeUnit.HOURS.toSeconds(hours));
        int secs = (int) (seconds % 60);
        return String.format("%02d:%02d:%02d", hours, minis, secs);
    }

    public static long getMilsecTimeByDate(String date) throws ParseException {
        if (date.trim().length() == 0) return -1;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date).getTime();
    }

    public static long getMilsecTimeByDateTime(String date) throws ParseException {
        if (date.trim().length() == 0) return -1;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(date).getTime();
    }

    public static String getShowDate(long milsec) {
        if (milsec == -1) return "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date(milsec));
    }

    public static int transDateStr2Int(String dateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = df.parse(dateStr);
            int dateInt = Integer.parseInt(df2.format(date));
            return dateInt;

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 将8位数转换成时间类型字符串yyyy-MM-dd
     *
     * @param dateInt
     * @return
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

    public static int transDate2Int(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(dateFormat.format(date));
    }

    /**
     * 将string类型的date 转化为 Date 类型的date
     *
     * @param date
     * @return
     */
    public static Date getDateFromStr(String date) throws ParseException {
        if (date.equals("")) return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }

    public static String getShowDateTime(long milsec) {
        if (milsec == -1) return "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(milsec));
    }

    public static String getShowDetail(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 将时间转化为20150101格式
     *
     * @param milsec
     * @return
     */
    public static int getSimpleIntegerDate(long milsec) {
        if (milsec == -1) return 20150101;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = dateFormat.format(new Date(milsec));

        String date = formatDate.replace("-", "");
        int intDate = Integer.parseInt(date);
        return intDate;
    }

    /**
     * 将20091225091010转化为 2009-12-25 09:10:10
     *
     * @param date
     * @return
     */
    public static Date getDateFromSimpleFormat(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(date);
    }




    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.MINUTE, minutes);
        return cd.getTime();
    }


    /**
     * 格式化时间，yyyyMMdd格式 String 转 Date

     * @param time 时间字符串
     * @return Data
     * @throws ParseException 解析异常
     */
    public static Date formatInParam(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(time);
    }
    
    public static Date formatDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
			return sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    /**
     * 	当前时间
     * @Description: 
     * @date:   2018年11月5日 上午9:22:32
     */
    public static Date now() {
    	return new Date();
    }

    /**
     * 	yyyy-MM-dd HH:mm:ss转long型YYYYMMDDHHMMSS
     * @Description:
     * @date:   2018年11月5日 上午9:22:32
     */
    public static long getLongDateTime(String date) throws ParseException {
        if (date.trim().length() == 0) return -1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = dateFormat.parse(date);
        String formatDate = longSdf.format(parse);
        String date2 = formatDate.replace("-", "");
        return Long.valueOf(date2);
    }

    /**
     * 获取当前日期 yyyy-MM-dd
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        try {
            System.out.print(getLongDateTime("2019-01-02 14:15:16"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
