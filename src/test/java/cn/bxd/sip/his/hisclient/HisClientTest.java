package cn.bxd.sip.his.hisclient;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
 

public class HisClientTest {
	
	// 【测试】  综合外联平台
	private static String address = "http://116.1.180.22:9081/services/sip?wsdl";
	private static Client client = null;
	
	// 测试
	public static void main(String[] args) {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		client = dcf.createClient(address);
		// 需要密码的情况需要加上用户名和密码
		client.getOutInterceptors().add(new LoginInterceptor("root", "admin"));
		
		String Dynamic="230058AAAAAA";// 网卡地址
		String HospitalNO ="230058";
		String CardInfo = "K13822465||||450900|||452528198009278777|陈翔|NEW|";
//		String InputData = "2100^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^^1^";
//		String InputData = "2240^230058^88888^0058-00088888-20180626888^20180724143519-230058-1^new^M500004|测试员|450923|^1^";
		String InputData = "2320^230058^88888^0058-00088888-20180626888^20180724143519-230058-2^new^M500004|20180724143519-230058-1|测试员|450923|^1^";

		send_310999("310999",HospitalNO,Dynamic,CardInfo,InputData);

	}

	/* his接口服务，统一交易入口（310999）  */
	public static void send_310999(String MsgType,String HospitalNO,String Dynamic,String CardInfo,String InputData) {
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			System.out.println("======client" + client);
			String input = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n"
					+ "<SIMSG>\n" 
					+ "<HEADER>\n"
					+ "<Version>V1.0</Version>\n" 
					+ "<MsgType>[MsgType]</MsgType>\n" 
					+ "<MsgID>20180211091143958624911669</MsgID>\n" 
					+ "<AppID>45090001</AppID>\n" 
					+ "<MsgTime>20180211091143</MsgTime>\n" 
					+ "</HEADER>\n"
					+ "<BODY>\n"
					//+ "<Dynamic>[Dynamic]</Dynamic>\n" 
					+ "<HospitalNO>[HospitalNO]</HospitalNO>\n" 
					+ "<CardInfo>[CardInfo]</CardInfo>\n" 
					+ "<InputData>[InputData]</InputData>\n"
					+ "</BODY>\n"
					+"<DSIGN></DSIGN>\n" 
					+ "</SIMSG>";
			input=input.replace( "[MsgType]", MsgType  );
			input=input.replace( "[Dynamic]", Dynamic  );
			input=input.replace( "[HospitalNO]", HospitalNO  );
			input=input.replace( "[CardInfo]", CardInfo  );
			input=input.replace( "[InputData]", InputData  );
			System.out.println("请求数据:\n" + input);
			objects = client.invoke("RecvMsg", input);
			String OutputData = (String) objects[0];
			System.out.println("返回数据:\n" + OutputData);
			String xml = StringUtils.substringBetween(OutputData, "<OutputData>", "</OutputData>");
			xml = StringEscapeUtils.unescapeHtml4(xml);
			System.out.println("社保返回数据:\n" +   StringEscapeUtils.unescapeHtml4(xml) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
    // --4、 门诊/住院登记 2210
	public final static String _2210 = "2210^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290002|21|20170610145821|J06.903|上呼吸道感染||综合内科|3|601088|陈智博|0|0|管理员|4509010090806|450901|^1^";
	// --5、登记修改 2230
	public final static String _2230 = "2230^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290001||||||管理员1|||450901|^1^";
	// --6、登记撤销 2240
	public final static String _2240 = "2240^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290001|管理员|450901|^1^";
	// --7、处方明细上报 2310
	public final static String _2310 = "2310^230058^88888^0058-00088888-20180626888^0058-20180129162239-318^new^ZY201801290002|1|11|ZY201801290002|20150615092028|0604006IJ0A376201|YLYP10001557|注射用12种复合维生素|2.5680|1.00||50mg*30#|||包叶林|包叶林||片|门诊部门||0|管理员|1|450901||^1^";
	// --8、处方明细撤销 2320
	public final static String _2320 = "2320^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290002|0058-20180611124030-101|管理员|450901||^1^";
	// --9、费用预结算 2420
//	public final static String _2420 = "2420^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290002|20180129180101|21|20170610145821|20170610145821||J06.903|上呼吸道感染|1|0|管理员|2.57|450901|^1^";
	public final static String _2420 = "2420^230058^88888^0058-00088888-20180626888^20180713013258-230058-5675^new^M99170|504|11|20180614110730|20180614110730|1|460.05|感冒|1||自助机|5|450923 ^1^";
	// --10、费用结算 2410
	public final static String _2410 = "2410^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290002|20180129180101|21|20170610145821|20170610145821||||1|0|管理员|2.57|450901|^1^";
	// --11、费用结算撤销 2430
	public final static String _2430 = "2430^230058^88888^0058-00088888-20180626888^0058-20180611124030-101^new^ZY201801290002|20180129180101|201506011100214|管理员|1|450901|^1^";
	
	
	社保返回报文XML
	<reponseEnvelope>
	<header>
	<appCode>0</appCode>
	<errorMessage briefMessage="" detailMessage=""/>
	</header>
	<body>
	<parameters>
	<parameter paraName="$YW_PASS_TIME_RESET$" paraValue="true"/>
	<parameter paraName="$ARCHIVEFLAG$" paraValue="FALSE"/>
	<parameter paraName="busiConfig" paraValue=""/>
	<parameter paraName="$BUSINESSREQUESTID$" paraValue="REQ-KF-M-011-00"/>
	<parameter paraName="$SAVEPAGEFLAG$" paraValue="0"/>
	<parameter paraName="cardConfig" paraValue=""/>
	<parameter paraName="outputData" paraValue="0058-20180714152739-920^1^4509231358171|9235003236|博白县人民医院(医疗、工伤、生育、失业）|452528198009278777|陈翔|1|01|19800927|K13822465|11|4509231358171|1|0|0|450923|3132|2018|0|3139.47|14.09|5.09|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|50|6|0.0|0.0|0.00|0.0|^0|||^"/>
	<parameter paraName="writeCardInfo" paraValue=""/>
	<parameter paraName="BARCODE" paraValue=""/>
	<parameter paraName="$OPERATEID$" paraValue="3000000607967004"/>
	<parameter paraName="$BUSINESSID$" paraValue="UCK106"/>
	</parameters>
	<dataStores>
	</dataStores>
	</body>
	</reponseEnvelope>
	*/
}
