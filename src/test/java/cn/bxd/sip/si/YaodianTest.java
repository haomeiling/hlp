package cn.bxd.sip.si;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;


public class YaodianTest {
 
//	public final static String url = "http://10.153.151.112:6002/eapdomain/SiInterfaceAction.do?method=siInterface";
	public final static String url = "http://ylbxd.siservice.gxhealth.xin/eapdomain/SiInterfaceAction.do?method=siInterface";
	
	//public final static String  dynamic = "019065AAAAAA";
	public final static String  dynamic = "010001AAAAAA";
	//public final static String  hospitalNO = "010019065";
	public final static String  hospitalNO = "010001";
	public final static String  operatorNo = "88888";
	//public final static String  cycleNo = "9065-00088888-20181015888";//业务周期号
	//public final static String  cycleNo = "9065-00088888-20181015888";//业务周期号
	public final static String  cycleNo = "0001-00088888-20181115888";//业务周期号
	public final static String dllParam = dynamic + "||";
	public final static String dllparm = "|||||||||||";
	//public final static String dllparm = "123";

	//社保卡信息：卡号｜卡识别码｜1｜1｜发卡地行政区划代码｜发卡日期｜｜身份证号码｜姓名｜联机标志｜
	//public final static String cardInfo = "K13822465||||450923|||452528198009278777|陈翔|NEW|";
	public final static String cardInfo = "K00045454||||450902|||450902196909088036|谢华平|NEW|";
	//public final static String cardInfo = "A40021793||||450100|||452628198511222424|郝美玲|NEW|";

	/**
	 * @param args
	 */
	public static void main( String[] args ){

		Random random = new Random();
		//流水号
		String transactionNo = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + "-" + hospitalNO + "-" + (random.nextInt(9999) % (9999 - 1000 + 1) + 1000); 
		
		// 业务编号^医疗机构编号^操作员编号^业务周期号^医院交易流水号^中心编码^入参^联机标志^
		String  inputData = hospitalNO + "^" + operatorNo + "^" + cycleNo + "^" + transactionNo + "^NEW^";

//		--获取中心端时间查询(1800)
		//inputData = "1800^" + inputData + "^1^";
		
		// --1、签到交易 
		//inputData = "9100^" + hospitalNO + "^^" + cycleNo + "^" + transactionNo + "^NEW^^1^";
		// --2、签退交易 9110
    	//inputData = "9110^" + inputData + "^1^";
		
		// --3、读卡交易 2100
    	//inputData = "2100^" + inputData + "^1^";
		// --4、 门诊/住院登记 2210
		//inputData = "2210^" + inputData + "201807051456345569|11|20180705145634|J06.901|病毒性上呼吸道感染||保健科门诊||3288|梁海燕|0|0|自助机|4509010060733||||" + "^1^";
		// --5、登记修改 2230
//		inputData = "2230^" + inputData + "YT201811240001||||||测试员|||450923|" + "^1^";
		// --6、处方明细上报 2310
		//10019065	10130170	维生素C	Y8550606	维生素C
//		inputData = "2310^" + inputData + "YD201811240001|1|11|YT201811240001|20181124100701|Y8550606|10130170|维生素C片|0.0295|1||0.1g×100片||qd|||口服|片|19|1|0|测试员||450923|0|" + "^1^";
		// --7、费用预结算 2420
//		inputData = "2420^" + inputData + "YD201811240001|20181124000001|12|20181124110130|20181124110130||||1|0|测试员|0.03|450923|" + "^1^";
		// --8、费用结算 2410
//		inputData = "2410^" + inputData + "YD201811240001|20181124000001|12|20181124110130|20181124110130||||1|0|测试员|0.03|450923|" + "^1^";
		//结算查询（1101）
		//inputData = "1101^" + inputData + "YD201811240001|20181124000001|" + "^1^";
		
		
		// --9、费用结算撤销 2430
//		inputData = "2430^" + inputData + "YT201811240001|20181124000001|20181124110130|测试员|1|450923|" + "^1^";
		// --10、处方明细撤销 2320
//		inputData = "2320^" + inputData + "YT201811240001|20181124022258-010019065-9960|测试员|450923||" + "^1^";
		// --11、登记撤销 2240
		//inputData = "2240^" + inputData + "201807051456345569|自助机||" + "^1^";
		
		
		System.out.println( "cardInfo=\"" + cardInfo + "\"");
		System.out.println( "inputData=\"" + inputData + "\"");
		String xml = HttpTookit.httpPost( url, inputData, dllParam, dllparm, cardInfo );
		System.out.println( xml );
		if ( StringUtils.isNotBlank( xml ) ){
			Document dom = DOMTool.loadDocumentFromStr( xml );
			Element header = DOMTool.getHeaderOrBody( dom, "header" );
			String appCode = DOMTool.getElement( header, "appCode" ).getText( );
		}
	}
}
