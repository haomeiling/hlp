package cn.bxd.sip.bxd.var;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-09-17
 * Time: 13:45
 */
public class SyncType {
    /*订单取消通知
     */
    public static final short CANCEL_ORDER_NOTIFY = 1;
    /*
    订单支付完成通知
    */
    public static final short PAY_SUCCESS_NOTIFY = 2;
    /*
    订单退款完成通知
    */
    public static final short REFUND_SUCCESS_NOTIFY = 3;
    /*
    还号通知
    */
    public static final short BACK_NO_NOTIFY = 4;
    /*
    订单信息通知
    */
    public static final short ORDER_CONFIRM_NOTIFY = 5;


    /*
  异步事务状态类型
   */
    public static class SyncStatusCode {
        /*.
        未处理状态
         */
        public static final String UNDO = "UNDO";

        /*.
       已处理状态
        */
        public static final String DONE = "DONE";

    }

}
