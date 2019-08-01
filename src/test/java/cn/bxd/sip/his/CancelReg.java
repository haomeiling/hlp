package cn.bxd.sip.his;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.his.QueryRegBypatientNoItemRes;
import cn.bxd.sip.his.model.dto.his.QueryRegBypatientNoRes;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.his.webservice.hisws.invoke.HISInterfaceQkbSoap;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Package: cn.bxd.sip.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CancelReg {


    @Test
    public void Cancel() throws Exception {

      /*  HISInterfaceQkbSoap wsClient = HisWSClient.getHisWSClientSoap("file:/G:/BXD_SVN/sip/src/main/resources/schema/HISInterface_qkb.wsdl");

        String queryRegByPatientNo;
        try {
            queryRegByPatientNo = wsClient.queryRegBypatientNo(HisConvertConst.SYS_USER_NAME, HisConvertConst.SYS_KEY, "000000002200");
            System.out.println(" 0001 查询未取号的预约挂号 HIS返回：" + queryRegByPatientNo);
        } catch (Exception e) {
            System.out.println(" 0001 查询未取号的预约挂号 HIS返回有误：" + e.getMessage());
            return ;
        }

        //解析：his返回成对象
        QueryRegBypatientNoRes queryRegBypatientNoRes = JSON.parseObject(queryRegByPatientNo, QueryRegBypatientNoRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryRegBypatientNoRes.getResultCode())) {
            System.out.println(queryRegBypatientNoRes.getResultMsg());
            return;
        }

        List<QueryRegBypatientNoItemRes> hiRegisterRecordList = queryRegBypatientNoRes.getHiRegisterRecordList();

        for (QueryRegBypatientNoItemRes q : hiRegisterRecordList) {
            String doRegCancelStr = wsClient.doRegCancel(HisConvertConst.SYS_USER_NAME, HisConvertConst.SYS_KEY, q.getSourceMark(), "000000002200", q.getSourceDate(), q.getDepartmentorganId());
            System.out.println("doRegCancelStr:" + doRegCancelStr);
        }*/
    }

}