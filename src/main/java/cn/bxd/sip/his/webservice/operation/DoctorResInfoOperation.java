package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListByDoctorIdRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListItemRes;
import cn.bxd.sip.his.model.dto.reservation.GetQueueDoctorInfoReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetQueueDoctorInfoResDates;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description: 查询医生队列号源 3011 根据医生ID查询号源 1.5 预约挂号
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-11
 */
@Slf4j
@Component
public class DoctorResInfoOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        //入参：掌上医院请求入参
        GetQueueDoctorInfoReqDates getQueueDoctorInfoReqDates = JSON.parseObject(reqMsg, GetQueueDoctorInfoReqDates.class);
        String startTime = getQueueDoctorInfoReqDates.getStartDate();
        String eMeiNo = getQueueDoctorInfoReqDates.getEMeiNo();

        SimpleDateFormat hSdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = hSdf.format(DateUtils.getNextDay(new Date(), 30));

        //封装：向HIS发送请求
        String queryToRegDoctorListByDoctorIdStr;
        try {
            //ws客户端：1.5 预约挂号
            Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();


            queryToRegDoctorListByDoctorIdStr = HisWSClient.invoke(hosWsClient4, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST_BY_DOCTOR_ID, sysUserName, sysKey,
                    startTime, endTime, eMeiNo);
            log.debug(" 3011 根据医生查询号源 HIS返回：" + queryToRegDoctorListByDoctorIdStr);
        } catch (Exception e) {
            log.debug(" 3011 根据医生查询号源 HIS返回有误：" + e.getMessage());
            return errMsgReturn("连接有误");
        }

        //解析：his返回成对象
        QueryToRegDoctorListByDoctorIdRes queryToRegDoctorListByDoctorIdRes = JSON.parseObject(queryToRegDoctorListByDoctorIdStr, QueryToRegDoctorListByDoctorIdRes.class);

        //判断：是否返回有错误

        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryToRegDoctorListByDoctorIdRes.getResultCode())) {
            return errMsgReturn(queryToRegDoctorListByDoctorIdRes.getResultMsg());
        }

        List<QueryToRegDoctorListItemRes> doctorList = queryToRegDoctorListByDoctorIdRes.getRegDoctor();
        if (doctorList == null || doctorList.size() == 0) {
            GetQueueDoctorInfoResDates getQueueDoctorInfoResDates = new GetQueueDoctorInfoResDates();
            getQueueDoctorInfoResDates.setOperCode(HisConvertConst.Operation.GET_QUEUE_DOCTOR_INFO_REQ_CODE);
            getQueueDoctorInfoResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
            getQueueDoctorInfoResDates.setHosId(getQueueDoctorInfoReqDates.getHosId());
            getQueueDoctorInfoResDates.setFlag((byte) 1);
            //修复出错不返回flag状态bug
            //return noDataMsgReturn(queryToRegDoctorListByDoctorIdRes.getResultCode(), queryToRegDoctorListByDoctorIdRes.getResultMsg());
            return JSON.toJSONString(getQueueDoctorInfoResDates);
        }

        //转换：进行参数转换
        QueryToRegDoctorListItemRes queryToRegDoctorListItemRes = queryToRegDoctorListByDoctorIdRes.getRegDoctor().get(0);
        int morningNum = queryToRegDoctorListItemRes.getMorningNum();
        int afternoonNum = queryToRegDoctorListItemRes.getAfternoonNum();
        int eveningNum = queryToRegDoctorListItemRes.getEveningNum();
        //0：可预约  1：无排班  2：已约满
        byte flag = 1;
        // >0 可预约
        if (morningNum > 0 || afternoonNum > 0 || eveningNum > 0) {
            flag = 0;
            //0显示约满
        } else if (morningNum == 0 || afternoonNum == 0 || eveningNum == 0) {
            flag = 2;
            //-1显示未排班
        } else if (morningNum == -1 || afternoonNum == -1 || eveningNum == -1) {
            flag = 1;
        }

        GetQueueDoctorInfoResDates getQueueDoctorInfoResDates = new GetQueueDoctorInfoResDates();
        getQueueDoctorInfoResDates.setOperCode(HisConvertConst.Operation.GET_QUEUE_DOCTOR_INFO_REQ_CODE);
        getQueueDoctorInfoResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getQueueDoctorInfoResDates.setHosId(getQueueDoctorInfoReqDates.getHosId());
        getQueueDoctorInfoResDates.setQueueId(queryToRegDoctorListItemRes.getOrgandoctorId() + "|" + queryToRegDoctorListItemRes.getDepartmentorganId());
        getQueueDoctorInfoResDates.setClinicDate(queryToRegDoctorListItemRes.getSourceDate());
        getQueueDoctorInfoResDates.setFlag(flag);
        return JSON.toJSONString(getQueueDoctorInfoResDates);
    }

}