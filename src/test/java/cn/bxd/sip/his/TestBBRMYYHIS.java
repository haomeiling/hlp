package cn.bxd.sip.his;

import cn.bxd.sip.his.webservice.hisws.invoke2.Service;
import cn.bxd.sip.his.webservice.hisws.invoke2.ServiceSoap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-09-19
 * Time: 12:08
 */
@SpringBootTest
@Slf4j
public class TestBBRMYYHIS {
    @Test
    public void testHis(){
        Service service=new Service();
        ServiceSoap serviceSoap = service.getServiceSoap();
        String s = serviceSoap.queryToRegDoctorList("", "", "2018-09-19", "2018-09-26");
        log.info("结果返回：\n" + s);
    }
}
