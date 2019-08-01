package cn.bxd.sip.his.webservice;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.BaseErrResDates;
import cn.bxd.sip.his.model.dto.reservation.BaseReqDates;
import cn.bxd.sip.his.webservice.operation.*;
import cn.bxd.sip.his.webservice.reservationws.AppointmentService;
import cn.bxd.sip.his.webservice.reservationws.AppointmentServiceResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Map;

/**
 * Description:  appointment web service 入口 出口
 * Package: com.bxd.sip.reservation.entry
 *
 * @author Leeves
 * @version 1.0.0  2018-07-11
 */
@Slf4j
@Endpoint
public class AppointmentEntry {

    @Autowired
    private OperationProcessorHolder operationProcessorHolder;

    //平台医院ID
    private static final String PLATFROM_HOSPITAL_ID = "0";

    /**
     * 命名空间
     */
    private static final String NAMESPACE_URI = "http://webservice.sip.bxd.cn/his";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "appointmentService")
    @ResponsePayload
    public AppointmentServiceResponse getRes(@RequestPayload AppointmentService request) {
        String reqMsg = request.getArg0();
        log.info("----HisConvert----入参:" + reqMsg);
        AppointmentServiceResponse response = new AppointmentServiceResponse();
        //判断：是否是json格式
        BaseReqDates baseReqDates;
        try {
            baseReqDates = JSON.parseObject(reqMsg, BaseReqDates.class);
        } catch (Exception e) {
            log.error("",e);
            response.setReturn(JSON.toJSONString(new BaseErrResDates("不是json格式")));
            return response;
        }

        //判断：操作码码为空错误
        String operCode = baseReqDates.getOperCode();
        if (operCode == null) {
            response.setReturn(JSON.toJSONString(new BaseErrResDates("数据错误")));
            return response;
        }

        //根据医院id，获取到连接配置
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        ConnectParm tRhipConnectParm = hosConnectParamMaps.get(baseReqDates.getHosId());
        if (tRhipConnectParm == null && !baseReqDates.getHosId().equals(PLATFROM_HOSPITAL_ID)) {
            response.setReturn(JSON.toJSONString(new BaseErrResDates("没有此医院信息")));
            return response;
        }

        Class operationClass;
        switch (operCode) {
            //查询最早排班日期 2020
            case HisConvertConst.Operation.GET_EARLY_ARRANGE_REQ_CODE:
                operationClass = GetEarlyArrangeOperation.class;
                break;
            //查询最晚排班日期 1
            case HisConvertConst.Operation.GET_LAST_ARRANGE_REQ_CODE:
                operationClass = GetLastArrangeOperation.class;
                break;
            //查询号源时段 2003
            case HisConvertConst.Operation.GET_QUEUE_REG_REQ_CODE:
                operationClass = GetQueueRegOperation.class;
                break;
            //查询号类信息 1000
            case HisConvertConst.Operation.GET_CLINIC_TYEP_INFO_REQ_CODE:
                operationClass = QueryToRegDoctorTimesOperation.class;
                break;
            //查询医生队列号源 3011
            case HisConvertConst.Operation.GET_QUEUE_DOCTOR_INFO_REQ_CODE:
                operationClass = DoctorResInfoOperation.class;
                break;
            //查询队列列表 2021
            case HisConvertConst.Operation.GET_QUEUE_LIST_REQ_CODE:
                operationClass = GetQueueListOperation.class;
                break;
            //患者主索引查询接口 2015
            case HisConvertConst.Operation.GET_PATIENT_INDEX_REQ_CODE:
                operationClass = PatientIndexQueryOperation.class;
                break;
            //取号接口 2004
            case HisConvertConst.Operation.GET_LOCK_REG_REQ_CODE:
                operationClass = LockRegOperation.class;
                break;
            //预结算 5000
            case HisConvertConst.Operation.PRE_SETTLEMENT:
                operationClass = PreSettlement.class;
                break;
            //结算 5001
            case HisConvertConst.Operation.SETTLE_ACCOUNTS:
                operationClass = DoSettlement.class;
                break;
            //查询待签到订单列表 2024
            case HisConvertConst.Operation.GET_ARRIVAL_ORDER_LIST_CODE:
                operationClass = GetArrivalListOperation.class;
                break;
            //住院记录查询 2027
            case HisConvertConst.Operation.GET_PATIENT_IN_HOS_INFO_CODE:
                operationClass = GetPatientInHosOperation.class;
                break;
            //门诊结算详情列表 2028
            case HisConvertConst.Operation.GET_CLINIC_INFO_LIST_REQ_CODE:
                operationClass = GeBillListOperation.class;
                break;
            //门诊结算详情 2029
            case HisConvertConst.Operation.GET_CLINIC_INFO_REQ_CODE:
                operationClass = GeBillInfoListOperation.class;
                break;
            //社保信息 4000
            case HisConvertConst.Operation.GET_SI_INFO_REQ_CODE:
                operationClass = GetSIInfoOperation.class;
                break;
            //查询用户是否存在 4005
            case HisConvertConst.Operation.JUDGE_USER_IS_EXIST:
                operationClass = JudgeUserOperation.class;
                break;
            //查询第三方用户是否存在 4002
            case HisConvertConst.Operation.JUDGE_THIRD_USER_IS_EXIST:
                operationClass = JudgeThirdUserOperation.class;
                break;
            //登录社保系统 4003
            case HisConvertConst.Operation.LOGIN:
                operationClass = LoginSIOperation.class;
                break;
            //社保退款 4006
            case HisConvertConst.Operation.REFUND_SOCIAL:
                operationClass = RefundSocialOperation.class;
                break;  
                //社保退款 4007
            case HisConvertConst.Operation.GET_PAYMENT:
                operationClass = QueryPaymentOperation.class;
                break;                          
            //社保政策 6000
            case HisConvertConst.Operation.GET_SOCIAL_POLICY:
                operationClass = GetSocialPolicy.class;
                break;
            //取消 2012
            case HisConvertConst.Operation.CANCEL_ORDER:
                operationClass = CancelOrder.class;
                break;
            //检验检查记录查询 3014
            case HisConvertConst.Operation.GET_REPORT_LIST:
                operationClass = GetClinicRecordOperation.class;
                break;

            //检验检查详细查询 3015  lisheng 2019/4/23
            case HisConvertConst.Operation.GET_REPORT_INFO:
                operationClass = GetClinicRecordDetailOperation.class;
                break;

            //诊疗记录查询 3017
            case HisConvertConst.Operation.GET_CLINIC_RECORD:
                operationClass = GetHospitalizationRecordsOperation.class;
                break;
            //排队信息 4001
            case HisConvertConst.Operation.GET_QUEUE_WAIT_INFO:
                operationClass = GetQueueWaitInfoOperation.class;
                break;
            //取号 2022
            case HisConvertConst.Operation.DO_TAKE_THE_NO:
                operationClass = DoTaketheNoOperation.class;
                break;
            //查询挂号费 2030
            case HisConvertConst.Operation.DOREG_FEE_INTERFACE:
                operationClass = DoregFeeInterfaceOperation.class;
                break;
            //获取挂号医保信息 2031
            case HisConvertConst.Operation.DO_REG_MEDICARE_INFO:
                operationClass = DoRegMedicareInfoOperation.class;
                break;
            default:
                operationClass = NoDefinitionOperation.class;
                break;
        }


        String operationOutStr;
        try {
            operationOutStr = operationProcessorHolder.findOperationProcessor(operationClass).getDataFromHis(reqMsg, tRhipConnectParm);
        } catch (Exception e) {
            log.error("",e);
            operationOutStr = JSON.toJSONString(new BaseErrResDates("发生异常！" + e.getMessage()));
        }

        log.info("----HisConvert----出参:" + operationOutStr);
        response.setReturn(operationOutStr);
        return response;
    }

}