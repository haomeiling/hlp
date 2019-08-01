package cn.bxd.sip.his.hisclient;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class Cxfclient {

	// webservice接口地址

	private static String address = "http://localhost:7777/services/sip?wsdl";

	private static Client client = null;

	// 测试
	public static void main(String[] args) {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		client = dcf.createClient(address);
		// 需要密码的情况需要加上用户名和密码
		client.getOutInterceptors().add(new LoginInterceptor("root", "admin"));

		/** 【医疗业务】 310 **/
		send_310999("310999");
	}

	/* his接口服务，统一交易入口（310999） */
	public static void send_310999(String MsgType) {
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			System.out.println("======client" + client);
			String input = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n"
					+ "<SIMSG>\n" 
					+ "<HEADER>\n"+
					"<Version>V1.0</Version>\n" + 
					"<MsgType>[MsgType]</MsgType>\n" + 
					"<MsgID>20180211091143958624911669</MsgID>\n" + 
					"<AppID>45090001</AppID>\n" + 
					"<MsgTime>20180211091143</MsgTime>\n" 
					+ "</HEADER>\n"
					+ "<BODY>\n"
					+ "</BODY>\n"
					+"<DSIGN></DSIGN>\n" 
					+ "</SIMSG>";
			input=input.replace( "[MsgType]", MsgType  );
			objects = client.invoke("RecvMsg", input);
			System.out.println("请求数据:\n" + input);
			System.out.println("返回数据:\n" + objects[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
