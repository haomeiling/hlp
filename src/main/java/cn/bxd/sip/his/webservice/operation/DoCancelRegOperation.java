package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.his.QueryRegBypatientNoItemRes;
import cn.bxd.sip.his.model.dto.his.QueryRegBypatientNoRes;
import cn.bxd.sip.his.webservice.hisws.invoke.HISInterfaceQkbSoap;
import cn.bxd.sip.his.webservice.hisws.invoke2.ServiceSoap;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 方便一件取消 1.5 预约挂号
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Deprecated
@Slf4j
@Component
public class DoCancelRegOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        //ws客户端：1.5 预约挂号
        Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
        String queryRegByPatientNo;
        try {
            if (hosWsClient4 instanceof HISInterfaceQkbSoap) {
                HISInterfaceQkbSoap hisInterfaceQkbSoap = (HISInterfaceQkbSoap) hosWsClient4;
                queryRegByPatientNo = hisInterfaceQkbSoap.queryRegBypatientNo(tRhipConnectParm.getUserName(), tRhipConnectParm.getCheckCode(), "000000002200");
            } else {
                ServiceSoap serviceSoap = (ServiceSoap) hosWsClient4;
                queryRegByPatientNo = serviceSoap.queryRegBypatientNo(tRhipConnectParm.getUserName(), tRhipConnectParm.getCheckCode(), "000000002200");
            }
            log.info(" 0001 查询未取号的预约挂号 HIS返回：" + queryRegByPatientNo);
        } catch (Exception e) {
            log.info(" 0001 查询未取号的预约挂号 HIS返回有误：" + e.getMessage());
            return errMsgReturn("连接有误");
        }

        //解析：his返回成对象
        QueryRegBypatientNoRes queryRegBypatientNoRes = JSON.parseObject(queryRegByPatientNo, QueryRegBypatientNoRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryRegBypatientNoRes.getResultCode())) {
            return errMsgReturn(queryRegBypatientNoRes.getResultMsg());
        }

        List<QueryRegBypatientNoItemRes> hiRegisterRecordList = queryRegBypatientNoRes.getHiRegisterRecordList();

        for (QueryRegBypatientNoItemRes q : hiRegisterRecordList) {
            String doRegCancelStr;
            if (hosWsClient4 instanceof HISInterfaceQkbSoap) {
                HISInterfaceQkbSoap hisInterfaceQkbSoap = (HISInterfaceQkbSoap) hosWsClient4;
                doRegCancelStr= hisInterfaceQkbSoap.doRegCancel(HisConvertConst.SYS_USER_NAME, HisConvertConst.SYS_KEY, q.getSourceMark(), "000000002200", q.getSourceDate(), q.getDepartmentorganId());
            } else {
                ServiceSoap serviceSoap = (ServiceSoap) hosWsClient4;
                doRegCancelStr= serviceSoap.doRegCancel(HisConvertConst.SYS_USER_NAME, HisConvertConst.SYS_KEY, q.getSourceMark(), "000000002200", q.getSourceDate(), q.getDepartmentorganId());
            }

            log.info("doRegCancelStr:" + doRegCancelStr);
        }

        return null;
    }

}