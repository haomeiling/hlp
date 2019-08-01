package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.respond.common.QueryDoctorListRespond;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.reservation.GetClinicRecordReqData;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 1.2.1.	查询医生列表信息
 * Description:
 * User: lisheng
 * Date: 2019/4/27
 */
@Slf4j
@Component
public class QueryDoctorListOperation extends AbstractOperationProcessor {
    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        GetClinicRecordReqData getClinicRecordReqData = JSON.parseObject(reqMsg, GetClinicRecordReqData.class);
        //封装：向HIS发送请求
        QueryDoctorListRespond respond = new QueryDoctorListRespond();
        try {
            //ws客户端：1.2.1.	查询医生列表信息 queryDoctorList
            Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_HIS_USER, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();

            //利用反射动态根据类名请求
            String queryDoctorList = HisWSClient.invoke(hosWsClient5, HisFunNameConst.QUERY_DOCTOR_LIST, sysUserName, sysKey, "");
            //返回对象
            respond = JSON.parseObject(String.valueOf(queryDoctorList), QueryDoctorListRespond.class);

            log.debug(" 1.2.1 查询医生列表信息 queryDoctorList" + queryDoctorList);


            return queryDoctorList;
        } catch (Exception e) {
            log.debug("1.2.1 查询医生列表信息 queryDoctorList 错误" + e.getMessage());
            log.error("", e);
            return errMsgReturn("系统错误");
        }
    }
}
