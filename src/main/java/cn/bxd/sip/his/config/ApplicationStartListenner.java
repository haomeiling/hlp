package cn.bxd.sip.his.config;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.service.IConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisHosIdListConst;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 启动时，初始化医院his wsdl
 * Package: com.bxd.sip.reservation.config
 *
 * @author Leeves
 * @version 1.0.0  2018-07-11
 * 已对该类进行了重构 20181008  haomeiling
 */
@Slf4j
@Component
public class ApplicationStartListenner implements CommandLineRunner {

    @Autowired
    private HisConvertConst mHisConvertConst;

    @Autowired
    private IConnectParm iTRhipConnectParm;

    @Autowired
    private HisHosIdListConst hisHosIdListConst;

    @Override
    public void run(String... strings) {
        mHisConvertConst.inti();
        //获取所有医院的配置信息
        List<ConnectParm> allConnectParamList = iTRhipConnectParm.getAllHosConnectParamList();
        log.debug("allConnectParamList:" + allConnectParamList);
        //初始化调用类
        ExecutorService executorService = null;
        try {
        	executorService = Executors.newFixedThreadPool(8);
        	for(ConnectParm connectParm :allConnectParamList) {
        		executorService.submit(new InitThread(connectParm));
        	}
        }
        catch (Exception e) {
        	log.info("异步初始化异常");
        }finally{
        	executorService.shutdown();
        }
    }

     class InitThread implements Runnable{
		
    	private ConnectParm allConnectParam; 
    	 
    	public void run() {
    		ConnectParm parm = initReflectClass(allConnectParam);
    		if(parm != null) {
    			HisConvertConst.allHosWsMap.put(String.valueOf(allConnectParam.getHospitalId()), parm);
    			log.info(allConnectParam.getHospitalId() + "医院配置信息:" + HisConvertConst.allHosWsMap);
    		}
		}
    	
    	public InitThread(ConnectParm allConnectParam) {
    		this.allConnectParam = allConnectParam;
    	}
    	
        /**
         * 采用反射机制生成类，动态获取类的方法
         *
         * @param t
         */
    	 private ConnectParm initReflectClass(ConnectParm t) {
    	        //如果后台启用了医院，则预先实例化wsdl。
    	            if (t.getWsFlag() == 1) {
    	                Map<String, Object> hosWsMap = new HashMap<>();
    	                log.info("----开始实例化客户端------医院ID-----" + t.getHospitalId());

    	                if (t.getWsUrl() != null) {
    	                    hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_HIS_USER, HisWSClient.getHisWSClientSoap(t.getHisUserSoap(), String.valueOf(t.getWsUrl())));
    	                }
    	                if (t.getWsUrl2() != null) {
    	                    hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_HIS_COM, HisWSClient.getHisWSClientSoap(t.getHisComSoap(), String.valueOf(t.getWsUrl2())));
    	                }
    	                if (t.getWsUrl3() != null) {
    	                    hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_PAY, HisWSClient.getHisWSClientSoap(t.getPaySoap(), String.valueOf(t.getWsUrl3())));
    	                }
    	                if (t.getWsUrl4() != null) {
    	                    hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_REG, HisWSClient.getHisWSClientSoap(t.getRegSoap(), String.valueOf(t.getWsUrl4())));
    	                }
    	                if (t.getWsUrl5() != null) {
    	                    hosWsMap.put(HisConvertConst.WS_CLIENT_KEY_IN_HOS, HisWSClient.getHisWSClientSoap(t.getInHosSoap(), String.valueOf(t.getWsUrl5())));
    	                }
    	                t.setHosWsClientMap(hosWsMap);

    	                if (t.getUserName() == null) {
    	                    t.setUserName("");
    	                }
    	                if (t.getCheckCode() == null) {
    	                    t.setCheckCode("");
    	                }
    	                return t;
    	            }
    	            return null;
    	  }
	}
   
}