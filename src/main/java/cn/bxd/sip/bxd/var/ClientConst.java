package cn.bxd.sip.bxd.var;

/**
 * @author：liangshangsong
 *
 * @Description：客户端常量
 *
 * @Date：2017年3月7日 下午3:19:00
 *
 */
public class ClientConst {
	
	/**
	 * 账户编码
	 */
	public static final String ACCOUNT_NO = "admin";
	
	public static final String PUBLIC_KEY = "Aa123456789Bb123456789Cc123456789";
	
	public static final String ACCOUNT_SECRET = "123456";
	
	/**
	 * 
	 * @author：liangshangsong
	 *
	 * @Description：调用地址
	 *
	 * @Date：2017年3月8日 下午2:12:47
	 *
	 */
	public class ReqUrl{
		/**
		 * 支付平台域名
		 */
		public static final String IP = "/";
		
		/**
		 * token调用地址
		 */
		public static final String TOKEN_REQ_URL = IP+"Token";
		
		/**
		 * 微信扫描调用地址
		 */
		public static final String WXPAYSCAN_URL = IP+"WXPayScan";
		
		/**
		 * 统一扫描调用地址
		 */
		public static final String PAYSCAN_URL = IP + "PayScan";
		
		/**
		 * 订单取消
		 */
		public static final String PAY_CANCEL_URL = IP + "PayCancel";
		
		/**
		 * 订单查询
		 */
		public static final String PAY_QUERY_URL = IP + "PayQuery";
		
		/**
		 * 订单查询
		 */
		public static final String PAY_QUERY_TRANS_URL = IP + "query/queryTrans";

		/**
		 * 刷卡支付
		 */
		public static final String PAY_MICRO_URL = IP + "PayMicro";

		/**
		 * 人脸初始化
		 */
		public static final String SMILEPAY_INITIALIZE = IP + "smilepayInitialize";
	}
}
