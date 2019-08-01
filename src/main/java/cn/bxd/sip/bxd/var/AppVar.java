package cn.bxd.sip.bxd.var;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * User: hml
 * Date: 2015-06-30
 */
@Component
public class AppVar implements ApplicationContextAware {
    public static ApplicationContext ac;
    //context
    public static String CONTEXT;
    //folder
    public static String FOLDER_APP;
    //setting
    public static Boolean IS_SETTING_STARTUP_SUCCESS;

    public static class Setting {
        /*
        支付系统服务器地址
         */
        public static String PAY_SERVICE_IP;

        /*
        消息服务ip
         */
        public static String MSG_IP;

        /*
        短消息服务地址
         */
        public static String SMS_IP;


        /*
        是否启动后台定时服务
         */
        public static Short OPEN_JOB;

        /*
        提醒时间
         */
        public static Integer NOTIFY_TIME_HH;
        public static Integer NOTIFY_TIME_MM;


        /*订单处理时间，每隔多少分钟执行一次，建议时间1分钟*/
        public static Integer INTERVAL_HANDEL_ORDER;
        /*支付超时执行时间，每隔多少分钟执行一次，建议时间1分钟*/
        public static Integer INTERVAL_HANDEL_PAYING;
        /*退款执行时间，没隔多久执行一次，建议时间1分钟*/
        public static Integer INTERVAL_HANDEL_REFUND;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
