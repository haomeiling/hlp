package cn.bxd.sip.his.job;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.dao.*;
import cn.bxd.sip.bxd.model.dto.HSyncEvn;
import cn.bxd.sip.bxd.model.entity.*;
import cn.bxd.sip.bxd.model.respond.pay.DoPayRespond;
import cn.bxd.sip.bxd.service.IExceptionService;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.ISyncEvtService;
import cn.bxd.sip.bxd.var.*;
import cn.bxd.sip.bxd.var.OrderStatus;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.*;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.his.webservice.hisws.invoke2.DoPayExtend;
import cn.bxd.sip.his.webservice.hisws.invoke2.DoPayMedicareData;
import cn.bxd.sip.his.webservice.hisws.invoke2.DoPayPaymentData;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.ws.soap.SOAPFaultException;
import java.lang.Exception;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:tangliang
 * @date:2018/7/11
 * @description:
 */
@Slf4j
@Component
public class SynEvtServerJob {
    @Autowired
    private IExceptionService exceSer;
    @Autowired
    private SyncEvtPendingMapper syncPendingMapper;
    @Autowired
    private SyncEvtMapper syncMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ISyncEvtService syncEvtService;
    @Autowired
    IOrderStatusService orderStatusService;
    @Autowired
    private SiMedicareRecordsMapper siMedicareRecordsMapper;


    // 支付类型：  4 = 医保
    private static final String TERMINAL_CODE = "3122";
    private static final String REUSTL_CODE_4_HIS = "00";//00代表成功
    private static final String REUSTL_CODE_01 = "01";//01代表失败
    private static final String SEPARATOR = ",";

    private static final short HAS_MEDICARE_PAY = 1;
    private static final short NO_MEDICARE_PAY = 0;


    @Scheduled(cron = "0/3 * * * * ?")
    public void SynEntService() {
        //查询待通知列表的ID组合
        String idStr = syncEvtService.getEvtPendingIdStr();

        if (StringUtils.isBlank(idStr)) {
            return;
        }

        log.info("----id组合-----" + idStr);

        //通过ID组合一次性从数据库查询数据，并逐次推送至HIS
        String ids[] = idStr.split(",");
        int idArray[] = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            idArray[i] = Integer.valueOf(ids[i]);
        }

        List<HSyncEvn> list = syncMapper.getByListId(idArray);
        for (HSyncEvn item : list) {
            //从内存中获取配置
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(String.valueOf(item.getHospitalId()));
            if (connectParm == null) {
                log.info("-----医院----" + item.getHospitalId() + "-----客户端实例化未完成");
                continue;
            }
            try {
                callWsUrl3Time(item);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    //调用接口
    private boolean callWsUrl3Time(HSyncEvn model) {
        boolean sus = false;
        try {
            short syncTypeId = model.getSyncTypeId();
            switch (syncTypeId) {
                case SyncType.CANCEL_ORDER_NOTIFY://订单取消通知
                    for (int i = 0; i < 3; i++) {
                        DoRegCancelRes res = callDoRegCancelWS(model);
                        sus = REUSTL_CODE_4_HIS.equals(res.getResultCode());
                        if (sus) {//如果成功，跳出循环
                            Long orderId = model.getOrderId();
                            if (orderId != null && orderId > 0) {
                                orderStatusService.insertOrderStatus(model.getOrderId(), OrderStatus.CANCEL_SUCCESS, "订单取消通知");// 写订单状态日志
                            }
                            break;
                        }
                    }
                    break;
                case SyncType.BACK_NO_NOTIFY://还号通知(处理方式与订单取消通知相同）
                    for (int i = 0; i < 3; i++) {
                        DoRegCancelRes res = callDoRegCancelWS(model);
                        sus = REUSTL_CODE_4_HIS.equals(res.getResultCode());
                        if (sus) {//如果成功，跳出循环
                            Long orderId = model.getOrderId();
                            if (orderId != null && orderId > 0) {
                                orderStatusService.insertOrderStatus(model.getOrderId(), OrderStatus.RETURN_SUCCESS, "订单还号成功");// 写订单状态日志
                            }
                            break;
                        }
                    }
                    break;
                case SyncType.PAY_SUCCESS_NOTIFY://支付成功通知
                    for (int i = 0; i < 1; i++) {
                        String resultCode = null;
                        Short orderTypeId = model.getOrder().getOrderTypeId();
                        switch (orderTypeId) {
                            case ReservationVar.Order.ORDER_TYPE_OUTPATIENT:
                                String result4Registration = callDoPayWS(model);
                                resultCode = JSON.parseObject(result4Registration).getString("resultCode");
                                break;
                            case ReservationVar.Order.ORDER_TYPE_ADVANCE:
                                String result4Inpatient = inpatientPayment(model);
                                resultCode = JSON.parseObject(result4Inpatient).getString("resultCode");
                                break;
                            //增加挂号订单处理 haomeiling 20190219
                            case ReservationVar.Order.ORDER_TYPE_REGISTRATION:
                                DoRegRes doRegRes = doRegAndTakeNo(model);
                                resultCode = doRegRes.getResultCode();
                                break;
                        }

                        if (resultCode == null || resultCode.equals("")) continue;
                        //处理结果
                        sus = TerminalVar.SUCCESS_CODE.equals(resultCode);
                        if (sus) {//如果成功，跳出循环
                            orderStatusService.insertOrderStatus(model.getOrderId(), OrderStatus.PAY_SUCCESS, "订单支付完成");// 订单支付完成
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
            log.info("SIP TO HIS-----result--" + sus);
            if (!sus) {//连续错误3次  新增异常
                exceSer.insertException(model);
            } else {
                //改状态跟时间
                SyncEvt evt = syncMapper.selectByPrimaryKey(model.getSyncSeqId());
                evt.setStatusCode(SyncType.SyncStatusCode.DONE);
                //evt.setFirstProcTime(new Date());
                log.info("更新SyncEvt状态，order_id--" + evt.getOrderId());
                int synRes = syncMapper.updateStatusByPrimaryKey(evt);
                log.info("更新SyncEvt状态结果--" + synRes);
            }
            int delResult = syncPendingMapper.deleteByPrimaryKey(model.getSyncSeqId());//删除pending表
            log.info("删除pending表：" + delResult);
        } catch (SOAPFaultException e) {
            //SOAP 调用异常，是否修正Pending表 扫描标记
            //yncPendingMapper.updateFlagingByPrimaryKey(model.getSyncSeqId());
            log.info("异常[{}]", e.getMessage());
        }
        return sus;
    }

    /**
     * 调用处方支付接口
     *
     * @param model 模型
     * @return DoPayRes
     * @date 20180917
     */
    public String callDoPayWS(HSyncEvn model) {
        Order order = model.getOrder();
        String hiFeeNos = order.getPeerOrderNo();
        Short payTypeObject = order.getPayType();
        short payType = payTypeConvertToHIS(payTypeObject == null ? 0 : order.getPayType());
        //Long payRecord = payType == ReservationVar.PayType.PAY_TYPE_SI ? order.getMedicareRecordId() : order.getTransId();
        //落地时，将订单号落地HIS，HIS取消的时候， 以该字段做凭证  修改时间2019-02-14 郝美玲
        Long payRecord = order.getOrderId();
        String payMoney = order.getCashMoney() == null ? "0" : order.getCashMoney();
        String medicareMoney = order.getMedicareMoney() == null ? "0" : order.getMedicareMoney();
        String socialSecurityNO = order.getSocialNo() == null ? "" : order.getSocialNo();
        String overRecord = "";//医保统筹流水
        String overMoney = order.getOverAllMoney() == null ? "0" : order.getOverAllMoney();
        String medicareReturn = "";
        String bankReturn = "";//银行返回信息 可空
        String terminalCode = order.getTerminalCode() == null || order.getTerminalCode().equals("") ? TERMINAL_CODE : order.getTerminalCode();//终端编号
        //String terminalCode = TERMINAL_CODE;//终端编号
        String userNo = order.getPatientNo();

        Short orderPayPattern = order.getPayPattern();
        log.info(" hiFeeNos = " + hiFeeNos);
        log.info(" payType = " + payType);
        log.info(" payRecord = " + payRecord);
        log.info(" payMoney = " + payMoney);
        log.info(" medicareMoney = " + medicareMoney);
        log.info(" socialsecurityNO = " + socialSecurityNO);
        log.info(" overRecord = " + overRecord);
        log.info(" overMoney = " + overMoney);
        log.info(" medicareReturn = " + medicareReturn);
        log.info(" bankReturn = " + bankReturn);
        log.info(" terminalCode = " + terminalCode);


        //从内存中获取配置
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        log.info(String.valueOf(model.getHospitalId()));
        ConnectParm connectParm = hosConnectParamMaps.get(String.valueOf(model.getHospitalId()));
        String resStr = null;
        Object hisPayWSClient = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_PAY);
        String paySoap = connectParm.getPaySoap();
        switch (paySoap) {
            case HisConvertConst.Soap.PAY_SOAP:
                break;
            case HisConvertConst.Soap.SERVICE_SOAP:
                //数据封装
                DoPayPaymentData doPayPaymentData = new DoPayPaymentData();
                doPayPaymentData.setHiFeeNo(hiFeeNos);
                doPayPaymentData.setTerminalCode(terminalCode);
                doPayPaymentData.setPayType(String.valueOf(payType));
                doPayPaymentData.setTotalMoney(String.valueOf(order.getAmount()));
                doPayPaymentData.setPayRecord(String.valueOf(payRecord));
                doPayPaymentData.setBankReturn(bankReturn);
                //设置扩展字段
                DoPayExtend doPayExtend = new DoPayExtend();
                doPayExtend.setPoNo(String.valueOf(order.getOrderId()));
                doPayPaymentData.setExtend(doPayExtend);

                //设置社保支付数据(社保支付或者混合支付需要再次封装)
                if (orderPayPattern == ReservationVar.PayPattern.SOSIAL_FEE || orderPayPattern == ReservationVar.PayPattern.MIX_FEE) {
                    SiMedicareRecordsWithBLOBs tSiMedicareRecordsWithBLOBs = siMedicareRecordsMapper.selectByPrimaryKey(order.getMedicareRecordId().intValue());
                    DoPayMedicareData doPayMedicareData = new DoPayMedicareData();
                    doPayMedicareData.setCashMoney(payMoney);
                    doPayMedicareData.setMedicareMoney(order.getMedicareMoney());
                    doPayMedicareData.setSocialsecurityNO(order.getSocialNo());
                    doPayMedicareData.setMedicareRecord(tSiMedicareRecordsWithBLOBs.getSiFeeIds());
                    doPayMedicareData.setOverMoney(order.getOverAllMoney());
                    doPayMedicareData.setMedicareReturn(tSiMedicareRecordsWithBLOBs.getMedicareinfo());
                    doPayMedicareData.setUserNo(tSiMedicareRecordsWithBLOBs.getPatientno());
                    doPayMedicareData.setMedicareType(String.valueOf(tSiMedicareRecordsWithBLOBs.getMedicaretype()));
                    doPayPaymentData.setMedicareData(doPayMedicareData);
                    //设置是否支付
                    doPayPaymentData.setHasMPay(HAS_MEDICARE_PAY);
                } else {
                    doPayPaymentData.setHasMPay(NO_MEDICARE_PAY);
                }

                String paymentData = JSON.toJSONString(doPayPaymentData);
                log.info("---调用处方支付doPay---paymentdata---" + paymentData);
                resStr = HisWSClient.invoke(hisPayWSClient, HisFunNameConst.DO_PAY, connectParm.getUserName(), connectParm.getCheckCode(), paymentData);
                break;
            case HisConvertConst.Soap.HIS_INTERFACE_QKB_SOAP:

                //设置社保支付数据(社保支付或者混合支付需要再次封装)
                if (orderPayPattern == ReservationVar.PayPattern.SOSIAL_FEE || orderPayPattern == ReservationVar.PayPattern.MIX_FEE) {
                    SiMedicareRecordsWithBLOBs tSiMedicareRecordsWithBLOBs = siMedicareRecordsMapper.selectByPrimaryKey(order.getMedicareRecordId().intValue());
                    medicareReturn = tSiMedicareRecordsWithBLOBs.getMedicareinfo();
                    userNo = tSiMedicareRecordsWithBLOBs.getPatientno();
                }


                //线下没有userNo传空字符  haomeiling 20190711
                if (userNo == null || userNo.equals("null")) {
                    userNo = "";
                }
                /**
                 * 0：非医保，
                 * 1：市医保，
                 * 2：区医保
                 */
                int medicareType = payType == ReservationVar.PayType.PAY_TYPE_SI ? 1 : 0;//兼容非医保的情况
                log.info(" medicareReturn = " + medicareReturn);
                log.info(" userNo = " + userNo);
                log.info(" medicareType = " + medicareType);

                resStr = HisWSClient.invoke(hisPayWSClient, HisFunNameConst.DO_PAY, connectParm.getUserName(), connectParm.getCheckCode(),
                        hiFeeNos, String.valueOf(payType), String.valueOf(payRecord), payMoney, medicareMoney, socialSecurityNO,
                        overRecord, overMoney, medicareReturn, bankReturn, terminalCode, userNo, String.valueOf(medicareType));
                break;

        }
        log.info("---调用处方支付接口结果---" + resStr);
        DoPayRespond respond = new Gson().fromJson(resStr, DoPayRespond.class);
        if (TerminalVar.SUCCESS_CODE.equals(respond.getResultCode())) {
            orderMapper.updateByPrimaryKey(new Order(order.getOrderId(), respond.getMedicalCode(),
                    respond.getClearingNO(), respond.getDispensaryWin(), respond.getGuidelinesInfo()));
            log.info("---异步调用处方支付接口结果之后更新trants表---orderId:" + order.getOrderId());
        }
        return resStr;
    }


    //注意，此方法是给终端使用，调整的时候，需要注意
    public String callDoPayWSNew(Order order, String hospitalId) {
        //Order order = model.getOrder();
        String hiFeeNos = order.getPeerOrderNo();
        Short payTypeObject = order.getPayType();
        short payType = payTypeConvertToHIS(payTypeObject == null ? 0 : payTypeObject);
        //Long payRecord = payType == ReservationVar.PayType.PAY_TYPE_SI ? order.getMedicareRecordId() : order.getTransId();
        //落地时，将订单号落地HIS，HIS取消的时候， 以该字段做凭证  修改时间2019-02-14 郝美玲
        Long payRecord = order.getOrderId();
        String payMoney = order.getCashMoney() == null ? "0" : order.getCashMoney();
        String medicareMoney = order.getMedicareMoney() == null ? "0" : order.getMedicareMoney();
        String socialSecurityNO = order.getSocialNo() == null ? "" : order.getSocialNo();
        String overRecord = "";//医保统筹流水
        String overMoney = order.getOverAllMoney() == null ? "0" : order.getOverAllMoney();
        String medicareReturn = "";
        String bankReturn = "";//银行返回信息 可空
        //String terminalCode = order.getTerminalCode();//终端编号
        String terminalCode = order.getTerminalCode();//终端编号 该终端号是上林县人民医院的终端号，针对不同的医院，此处要做调整 20180315 haomeiling
        String userNo = order.getPatientNo();
        Short medicareType = payType == ReservationVar.PayType.PAY_TYPE_SI ? order.getMedicareType() : 0;
        log.info(" hiFeeNos = " + hiFeeNos);
        log.info(" payType = " + payType);
        log.info(" payRecord = " + payRecord);
        log.info(" payMoney = " + payMoney);
        log.info(" medicareMoney = " + medicareMoney);
        log.info(" socialsecurityNO = " + socialSecurityNO);
        log.info(" overRecord = " + overRecord);
        log.info(" overMoney = " + overMoney);
        log.info(" medicareReturn = " + medicareReturn);
        log.info(" bankReturn = " + bankReturn);
        log.info(" terminalCode = " + terminalCode);
        log.info(" userNo = " + userNo);
        log.info(" medicareType = " + medicareType);
        //从内存中获取配置
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        log.info(String.valueOf(hospitalId));
        ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
        String resStr = null;
        Object hisPayWSClient = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_PAY);
        String paySoap = connectParm.getPaySoap();
        switch (paySoap) {
            case HisConvertConst.Soap.PAY_SOAP:
                break;
            case HisConvertConst.Soap.SERVICE_SOAP:
                //数据封装
                DoPayPaymentData doPayPaymentData = new DoPayPaymentData();
                doPayPaymentData.setHiFeeNo(hiFeeNos);
                doPayPaymentData.setTerminalCode(terminalCode);
                doPayPaymentData.setPayType(String.valueOf(payType));
                doPayPaymentData.setTotalMoney(String.valueOf(order.getAmount()));
                doPayPaymentData.setPayRecord(String.valueOf(payRecord));
                doPayPaymentData.setBankReturn(bankReturn);
                //设置扩展字段
                DoPayExtend doPayExtend = new DoPayExtend();
                doPayExtend.setPoNo(String.valueOf(order.getOrderId()));
                doPayPaymentData.setExtend(doPayExtend);

                Short orderPayPattern = order.getPayPattern();
                //设置社保支付数据(社保支付或者混合支付需要再次封装)
                if (orderPayPattern == ReservationVar.PayPattern.SOSIAL_FEE || orderPayPattern == ReservationVar.PayPattern.MIX_FEE) {
                    SiMedicareRecordsWithBLOBs tSiMedicareRecordsWithBLOBs = siMedicareRecordsMapper.selectByPrimaryKey(order.getMedicareRecordId().intValue());
                    DoPayMedicareData doPayMedicareData = new DoPayMedicareData();
                    doPayMedicareData.setCashMoney(payMoney);
                    doPayMedicareData.setMedicareMoney(order.getMedicareMoney());
                    doPayMedicareData.setSocialsecurityNO(order.getSocialNo());
                    doPayMedicareData.setMedicareRecord(tSiMedicareRecordsWithBLOBs.getSiFeeIds());
                    doPayMedicareData.setOverMoney(order.getOverAllMoney());
                    doPayMedicareData.setMedicareReturn(tSiMedicareRecordsWithBLOBs.getMedicareinfo());
                    doPayMedicareData.setUserNo(tSiMedicareRecordsWithBLOBs.getPatientno());
                    doPayMedicareData.setMedicareType(String.valueOf(tSiMedicareRecordsWithBLOBs.getMedicaretype()));
                    doPayPaymentData.setMedicareData(doPayMedicareData);
                    //设置是否支付
                    doPayPaymentData.setHasMPay(HAS_MEDICARE_PAY);
                } else {
                    doPayPaymentData.setHasMPay(NO_MEDICARE_PAY);
                }

                String paymentData = JSON.toJSONString(doPayPaymentData);
                log.info("---调用处方支付doPay---paymentdata---" + paymentData);
                resStr = HisWSClient.invoke(hisPayWSClient, HisFunNameConst.DO_PAY, connectParm.getUserName(), connectParm.getCheckCode(), paymentData);
                break;
            case HisConvertConst.Soap.HIS_INTERFACE_QKB_SOAP:
                resStr = HisWSClient.invoke(hisPayWSClient, HisFunNameConst.DO_PAY, connectParm.getUserName(), connectParm.getCheckCode(),
                        hiFeeNos, String.valueOf(payType), String.valueOf(payRecord), payMoney, medicareMoney, socialSecurityNO,
                        overRecord, overMoney, medicareReturn, bankReturn, terminalCode, userNo, String.valueOf(medicareType));
                break;

        }
        log.info("---调用处方支付接口结果---" + resStr);
        return resStr;
    }

    /**
     * 调用住院补缴费
     * haomeiling
     * 20181221
     */
    private String inpatientPayment(HSyncEvn model) {
        Order order = model.getOrder();
        String inHosNo = order.getPeerOrderNo();
        Short payTypeObject = order.getPayType();
        short payType = payTypeConvertToHIS(payTypeObject == null ? 0 : payTypeObject);
        //Long payRecord = payType == ReservationVar.PayType.PAY_TYPE_SI ? order.getMedicareRecordId() : order.getTransId();
        //落地时，将订单号落地HIS，HIS取消的时候， 以该字段做凭证  修改时间2019-02-14 郝美玲
        Long payRecord = order.getOrderId();
        String payMoney = order.getCashMoney() == null ? "0" : order.getCashMoney();
        String terminalCode = order.getTerminalCode();//终端编号
        log.info(" inHosNo = " + inHosNo);
        log.info(" payType = " + payType);
        log.info(" payRecord = " + payRecord);
        log.info(" payMoney = " + payMoney);
        log.info(" terminalCode = " + terminalCode);
        //从内存中获取配置
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        ConnectParm connectParm = hosConnectParamMaps.get(String.valueOf(model.getHospitalId()));
        String resStr = null;
        Object hisPayWSClient = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_PAY);
        String paySoap = connectParm.getPaySoap();
        switch (paySoap) {
            case HisConvertConst.Soap.PAY_SOAP:
                break;
            case HisConvertConst.Soap.SERVICE_SOAP:
                break;
            case HisConvertConst.Soap.HIS_INTERFACE_QKB_SOAP:
                resStr = HisWSClient.invoke(hisPayWSClient, HisFunNameConst.INPATIENT_PAYMENT,
                        connectParm.getUserName(), connectParm.getCheckCode(), inHosNo, "", String.valueOf(payType), String.valueOf(payRecord), payMoney, "", terminalCode);
                break;

        }
        log.info("---调用住院预交接口结果---" + resStr);
        return resStr;
    }

    /**
     * 调用取消接口
     *
     * @param model 模型
     * @return DoRegCancelRes
     * @date 20180917
     */
    private DoRegCancelRes callDoRegCancelWS(HSyncEvn model) {
        Long orderId = model.getOrderId();
        DoRegCancelRes res = new DoRegCancelRes();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        ConnectParm connObj = model.getConnectParm();
        if (order != null) {
            String sourceMark = order.getPeerOrderNo();
            String patientNo = order.getPatientNo();
            String sourceDate = TimeUtils.transDateInt2Str(order.getOrderDay());
            //获取科室编码
            //2019/1/22 修改输入code与按id查询不对应问题
           /* HospitalDeptKey hospitalDeptKey = new HospitalDeptKey();
            hospitalDeptKey.setHospitalId(order.getHospitalId());
            hospitalDeptKey.setDeptNo(Integer.parseInt(order.getDeptCode()));
            HospitalDeptService hospitalDept = hospitalDeptMapper.selectByPrimaryKey(hospitalDeptKey);*/

            //从内存中获取配置
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(String.valueOf(model.getHospitalId()));
            Object hisRegWSClient = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            //发送订单取消请求
            //2019/1/22 修改输入code与按id查询不对应问题
            String outPut = HisWSClient.invoke(hisRegWSClient, HisFunNameConst.DO_REG_CANCEL, connObj.getUserName(), connObj.getCheckCode(),
                    sourceMark, patientNo, sourceDate, order.getDeptCode());
            //此处如果接口没有实现，会返回空或者null，做兼容处理
            if (outPut == null || outPut.equals("")) {
                res.setResultCode(REUSTL_CODE_01);
                log.info("未请求到HIS，或者HIS还未实现该接口，需确认");
            } else {
                res = JSON.parseObject(outPut, DoRegCancelRes.class);
            }

        }
        return res;
    }

    /**
     * 订单信息通知
     *
     * @param hSyncEvn 处理字符串
     * @return 预约挂号信息
     */
    private DoRegRes doRegAndTakeNo(HSyncEvn hSyncEvn) {
        Order order = hSyncEvn.getOrder();
        //封装：his入参
        DoRegReq doRegReq = new DoRegReq();
        doRegReq.setOrgandoctorId(order.getDoctorCode());
        doRegReq.setDepartmentorganId(order.getDeptCode());

        doRegReq.setPatientNo(order.getPatientNo());
        doRegReq.setSourceDate(TimeUtils.transDateInt2Str(order.getOrderDay()));
        //时间 string
        doRegReq.setTimestypeNo(order.getOrderPeriod());
        //时间段显示名称
        doRegReq.setTimestypeNoName("");
        doRegReq.setSourceTimeType(String.valueOf(order.getPeriodNo()));
        //是否支付 0 否 1是
        doRegReq.setIsDopay(DoPay.YES.getPayFlag());
        doRegReq.setTerminalCode("");
        //现场付款3
        doRegReq.setPayType(String.valueOf(order.getPayType()));

        //2019/1/21
        doRegReq.setPatientName(order.getPatientName());//患者姓名
        doRegReq.setGenderCode(order.getGenderCode());//患者性别  0未知的性别,1男性,2女性,9未说明的性别
        doRegReq.setSourceCode(order.getSourceCode());//预约来源  1微信,2支付宝,13云pos,14自助机

        //贵港市中医医院，如果没有身份证，身份证的字段传patientNo haomeiling 20190719
        Integer hospitalId = order.getHospitalId();
        String cardNo=order.getCertIdno();
        switch (hospitalId) {
            case HospitalCode.GGSZYYY:
                if(cardNo==null||cardNo.equals("")) {
                    doRegReq.setCardNo(order.getPatientNo());
                }else {
                    doRegReq.setCardNo(cardNo);
                }
            default://其他医院传空
                if(cardNo==null||cardNo.equals("")) {
                    doRegReq.setCardNo("");
                }else {
                    doRegReq.setCardNo(cardNo);
                }
        }

        //设置payNo和支付金额
        doRegReq.setPayRecord(String.valueOf(order.getOrderId()));
        doRegReq.setPayAmount(String.valueOf(order.getAmount()));

        //设置联系电话好和联系电话 Lisheng  2019/6/29
        doRegReq.setPatientTelephone(hSyncEvn.getContactPhone());//联系电话 lisheng
        doRegReq.setVisitCardNo(hSyncEvn.getClinicCardNo()); //就诊卡号 lisheng

        //支付模式
        Short orderPayPattern = order.getPayPattern();
        //设置社保支付数据(社保支付或者混合支付需要再次封装)
        String payMoney = order.getCashMoney() == null ? "0" : order.getCashMoney();
        if (orderPayPattern == ReservationVar.PayPattern.SOSIAL_FEE || orderPayPattern == ReservationVar.PayPattern.MIX_FEE) {
            SiMedicareRecordsWithBLOBs tSiMedicareRecordsWithBLOBs = siMedicareRecordsMapper.selectByPrimaryKey(order.getMedicareRecordId().intValue());
            doRegReq.setCashMoney(payMoney);
            doRegReq.setMedicareMoney(order.getMedicareMoney());
            doRegReq.setSocialsecurityNO(order.getSocialNo());
            doRegReq.setMedicareRecord(tSiMedicareRecordsWithBLOBs.getSiFeeIds());
            doRegReq.setOverMoney(order.getOverAllMoney());
            doRegReq.setMedicareReturn(tSiMedicareRecordsWithBLOBs.getMedicareinfo());
            doRegReq.setUserNo(tSiMedicareRecordsWithBLOBs.getPatientno());
            doRegReq.setMedicareType(String.valueOf(tSiMedicareRecordsWithBLOBs.getMedicaretype()));
        }
        String doRegReqStr = JSON.toJSONString(doRegReq);
        //获取医院互联配置
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        ConnectParm connectParm = hosConnectParamMaps.get(String.valueOf(order.getHospitalId()));
        Object hisRegWSClient = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);

        //用户名 校验码
        String sysUserName = connectParm.getUserName();
        String sysKey = connectParm.getCheckCode();

        //发送预约以及取号请求
        String outPut = HisWSClient.invoke(hisRegWSClient, HisFunNameConst.DO_REG_AND_TAKETHE_NO, sysUserName, sysKey, doRegReqStr);

        DoRegRes res = JSON.parseObject(outPut, DoRegRes.class);

        if (TerminalVar.SUCCESS_CODE.equals(res.getResultCode())) {
          /*  sourceMark		号源编号
            departmentNum		就诊科室
            takeNo	String		取号号码
            medicalCode	String否	医院就医码*/

            /**
             * (Long orderId, String queueNo,String peerOrderNo, String displayQueueNo, String queueAddr, Short sentFlag,Date validatedTime
             *  {"sourceMark":"00","medicalCode":"201902211555001228","departmentNum":"040443","takeNo":"00","resultCode":"00","resultMsg":"信息返回成功！",}
             *  号源编号 00
             */
            orderMapper.updateByPrimaryKey(new Order(order.getOrderId(), res.getTakeNo(),
                    res.getSourceMark(), res.getMedicalCode(), res.getDepartmentNum(), SentFlag.YES.getSentFlag(), new Date()));
            log.info("---异步调用当日挂号接口结果之后更新order表---orderId:" + order.getOrderId());
        }

        return res;
    }

    //数据库： 1微信，2支付宝 , 3现场支付, 4医保账户，5银联，6建行，7中行,8招行,9、交通银行
    //接口：1银联，2支付宝 3，现场支付 4、医保，5、微信，6、建行，7、中行 ，8 招行，9、交通银行
    //差别在于微信和银联互换了值

    //数据库： 1微信，5银联
    //接口：    1银联，5、微信

    //将数据库类型转换为HIS终端类型
    public short payTypeConvertToHIS(Short payType) {
        if (payType == null) {
            return 0;
        }
        short res;
        switch (payType) {
            case ReservationVar.PayType.PAY_TYPE_WXPAY:
                res = ReservationVar.HPayType.PAY_TYPE_WXPAY;
                break;
            case ReservationVar.PayType.PAY_TYPE_BACK:
                res = ReservationVar.HPayType.PAY_TYPE_BACK;
                break;
            default:
                res = payType;
                break;
        }
        return res;
    }

    //将终端数据转换为数据库类型
    public short payTypeConvertToDB(Short payType) {
        if (payType == null) {
            return 0;
        }
        short res;
        switch (payType) {
            case ReservationVar.HPayType.PAY_TYPE_WXPAY:
                res = ReservationVar.PayType.PAY_TYPE_WXPAY;
                break;
            case ReservationVar.HPayType.PAY_TYPE_BACK:
                res = ReservationVar.PayType.PAY_TYPE_BACK;
                break;
            default:
                res = payType;
                break;
        }
        return res;
    }
}
