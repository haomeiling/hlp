package cn.bxd.sip.si.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Random;


/**
 * 
 * @Description：系统消息应答
 *
 * @author：liangshangsong
 *
 * 2015年10月27日 下午4:48:57
 */
public class SystemResponseMsg {

	/**
	 * 获取消息唯一编码
	 * @return
	 */
	public static String getMsgNo(String msgType){
		return getTimeEndSs()+msgType+getRandomNum();
	}

    /**
     * 获取消息唯一编码
     * @return
     */
    public static String getTestMsgNo(){
        return getTimeEndDd()+"000"+getRandomNum();
    }

    public static String getMsgID(String msgId) {
        StringBuilder s = new StringBuilder(msgId);
        int addZeroSize = 8 - s.length();
        for (int i = 0; i < addZeroSize; i++) {
            s.insert(0, "0");
        }
        return SystemResponseMsg.getTimeEndDd() + s;
    }

	/**
	 * 获取时间到秒s
	 * @return
	 */
	public static String getTimeEndSs(){
		Calendar cal = Calendar.getInstance();
		return DateFormatUtils.format(cal, "yyyyMMddHHmmss");
	}
	
	/**
	 * 获取时间到天d
	 * @return
	 */
	public static String getTimeEndDd(){
		Calendar cal = Calendar.getInstance();
		return DateFormatUtils.format(cal, "yyyyMMdd");
	}
	
	/**
	 * 获取10000-99999随机数
	 * @return
	 */
	public static int getRandomNum(){
		Random rand = new Random();
        return rand.nextInt(89999)+10000;
	}

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getRandomNum());
        }
    }
}
