package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.util.TimeUtils;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryRegBypatientNoItemRes;
import cn.bxd.sip.his.model.dto.his.QueryRegBypatientNoRes;
import cn.bxd.sip.his.model.dto.reservation.GetArrivalListItemResDatas;
import cn.bxd.sip.his.model.dto.reservation.GetArrivalListReqDatas;
import cn.bxd.sip.his.model.dto.reservation.GetArrivalListResDatas;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 查询待签到订单列表 2024 1.5 预约挂号
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Slf4j
@Component
public class GetArrivalListOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {

        GetArrivalListReqDatas getArrivalListReqDatas = JSON.parseObject(reqMsg, GetArrivalListReqDatas.class);

        //封装：向HIS发送请求
        String queryRegByPatientNo;
        try {
            //ws客户端：1.5 预约挂号
            Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();
            queryRegByPatientNo = HisWSClient.invoke(hosWsClient4, HisFunNameConst.QUERY_REG_BYPATIENT_NO, sysUserName, sysKey,
                    getArrivalListReqDatas.getEmpi());

            log.debug(" 2024 查询未取号的预约挂号 HIS返回：" + queryRegByPatientNo);
        } catch (Exception e) {
            log.debug(" 2024 查询未取号的预约挂号 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn("连接有误");
        }

        //判断：是否是json格式
        QueryRegBypatientNoRes queryRegBypatientNoRes;
        //解析：his返回成对象
        try {
            queryRegBypatientNoRes = JSON.parseObject(queryRegByPatientNo, QueryRegBypatientNoRes.class);
        } catch (Exception e) {
            log.error("",e);
            return errMsgReturn(queryRegByPatientNo);
        }

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryRegBypatientNoRes.getResultCode())) {
            return errMsgReturn(queryRegBypatientNoRes.getResultMsg());
        }


        List<QueryRegBypatientNoItemRes> hiRegisterRecordList = queryRegBypatientNoRes.getHiRegisterRecordList();

        List<GetArrivalListItemResDatas> getArrivalListItemResDatasList = new ArrayList<>();

        String nowDate = TimeUtils.getNowDate();
        for (QueryRegBypatientNoItemRes q : hiRegisterRecordList) {
            //过滤非今天的记录  lisheng 2019/7/18
            if (!nowDate.equals(q.getSourceDate())){
                continue;
            }
            GetArrivalListItemResDatas getArrivalListItemResDatas = new GetArrivalListItemResDatas();
            getArrivalListItemResDatasList.add(getArrivalListItemResDatas);
            getArrivalListItemResDatas.setPeerOrderNo(q.getSourceMark());
            getArrivalListItemResDatas.setAmount(Double.valueOf(q.getConsultationFee()));
            getArrivalListItemResDatas.setSubjectName(q.getDepartmentName());
            getArrivalListItemResDatas.setDoctorName(q.getOrgandoctorName());
            getArrivalListItemResDatas.setDoctorCode(q.getOrgandoctorId());
            getArrivalListItemResDatas.setQueueId(q.getOrgandoctorId() + "|" + q.getDepartmentorganId());
            getArrivalListItemResDatas.setClinicDate(Integer.valueOf(StringUtils.replaceAll(q.getSourceDate(), "-", "")));
            getArrivalListItemResDatas.setPeriodId(q.getSourceTimeType());
            getArrivalListItemResDatas.setOrderSourceId(3);//掌上预约,下单的时候没有数据支撑
            getArrivalListItemResDatas.setArrivalFlag(0);//都是未签到
            getArrivalListItemResDatas.setPaidFlag(0);//预约都不支付
        }

        GetArrivalListResDatas getArrivalListResDatas = new GetArrivalListResDatas();
        getArrivalListResDatas.setOperCode(HisConvertConst.Operation.GET_ARRIVAL_ORDER_LIST_CODE);
        getArrivalListResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);

        //设置签到是否需要支付标志位
        Short payFlag=tRhipConnectParm.getPaidPriortoRegisitration();
        if(payFlag==null){
            payFlag=ReservationVar.Is.FALSE;
        }
        getArrivalListResDatas.setSignPayFlag(String.valueOf(payFlag));
        getArrivalListResDatas.setOrderList(getArrivalListItemResDatasList);
        return JSON.toJSONString(getArrivalListResDatas);
    }
}