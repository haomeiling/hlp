package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.dao.SimpleQueryDao;
import cn.bxd.sip.bxd.dao.SyncEvtMapper;

import cn.bxd.sip.bxd.model.dto.*;

import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.dto.PayScanReqData;
import cn.bxd.sip.bxd.model.dto.PayScanResData;
import cn.bxd.sip.bxd.model.dto.QueryPayStatusReqData;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.pay.*;
import cn.bxd.sip.bxd.model.respond.resultmessage.DoRegPayMgs;
import cn.bxd.sip.bxd.pay.BillDownService;
import cn.bxd.sip.bxd.pay.PayProxyService;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.ISyncEvtService;
import cn.bxd.sip.bxd.service.impl.OrderService;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.var.*;
import cn.bxd.sip.bxd.webservice.IPayWebService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.job.SynEvtServerJob;
import cn.bxd.sip.his.model.dto.reservation.CancelOrderInput;
import cn.bxd.sip.his.model.dto.reservation.CancelOrderOutput;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.his.webservice.operation.CancelOrder;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 **/
@Service
@Slf4j
@WebService(name = "Pay", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
public class PayWebService implements IPayWebService {
    //平台医院ID
    private static final String PLATFROM_HOSPITAL_ID = "0";
    private static final String WS_CLIENT_KEY = HisConvertConst.WS_CLIENT_KEY_PAY;

    @Resource
    SiService siService;
    @Resource
    HospitalSiConfigMapper tRhipHospitalSiConfigMapper;
    @Resource
    OrderMapper tRhipOrderMapper;
    @Resource
    SeqService seqService;
    @Autowired
    private SyncEvtMapper syncMapper;
    @Resource
    private ISyncEvtService syncEvtService;
    @Resource
    SimpleQueryDao simpleQueryDao;
    @Resource
    CancelOrder cancelOrder;
    @Resource
    OrderService orderService;
    @Resource
    private IOrderStatusService orderStatusService;
    @Resource
    private PayProxyService payProxyService;
    @Resource
    private SynEvtServerJob synEvtServerJob;
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private BillDownService billDownService;


    @Override
    public QueryToPayRecipeInfoListRespond queryToPayRecipeInfoList(String synUserName,
                                                                    String synKey,
                                                                    String terminalCode,
                                                                    String hospitalId,
                                                                    String patientNo,
                                                                    String medicareType,
                                                                    String medicareMess,
                                                                    String extend
    ) {
        QueryToPayRecipeInfoListRespond respond = new QueryToPayRecipeInfoListRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.NO_DATA_FAIL_CODE);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object client = connectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            log.debug("---医保类型----" + medicareType);
            log.debug("---医保信息----" + medicareMess);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_PAY_RECIPE_INFO_LIST, synUserName, synKey,
                    patientNo, medicareType, medicareMess, "", "");
            log.debug(res);

            respond = JSON.parseObject(res, QueryToPayRecipeInfoListRespond.class);
            if (respond.getHiFee() != null && respond.getHiFee().size() == 0) {
                respond.setHiFee(null);
            }

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryPaymentRecordListRespond queryPaymentRecordList(String synUserName,
                                                                String synKey,
                                                                String terminalCode,
                                                                String hospitalId,
                                                                String patientNo,
                                                                String startDate,
                                                                String endDate) {
        QueryPaymentRecordListRespond respond = new QueryPaymentRecordListRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_PAYMENT_RECORD_LIST, synUserName, synKey,
                    patientNo, startDate, endDate);

            respond = JSON.parseObject(res, QueryPaymentRecordListRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryPaymentRecordStatusRespond queryPaymentRecordStatus(String synUserName,
                                                                    String synKey,
                                                                    String terminalCode,
                                                                    String hospitalId,
                                                                    String patientNo,
                                                                    String hiFeeNos,
                                                                    String extend) {
        // 调用 1.4.3	根据处方编号获取处方信息（必须）  queryToPayRecipeByHiFeeNo 文档定义参数名称重复、估计有一个是hiFeeNo
        QueryPaymentRecordStatusRespond respond = new QueryPaymentRecordStatusRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //组装参数
            String hiFeeArray[] = StringUtils.split(hiFeeNos, ",");
            for (String hiFeeNo : hiFeeArray) {

                //利用反射动态根据类名请求
                String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_PAY_RECIPE_BY_HI_FEE_NO, synUserName, synKey,
                        terminalCode, hospitalId, patientNo, hiFeeNos, extend);

                QueryToPayRecipeInfoListRespond resBean = JSON.parseObject(String.valueOf(res), QueryToPayRecipeInfoListRespond.class);
                // 文档描述 queryToPayRecipeByHiFeeNo 返回与待缴费信息一致、但是理论上只能返回一个hiFee的信息
                List<HiFee> hiFees = resBean.getHiFee();
                if (hiFees == null && hiFees.size() > 0) {
                    continue;
                }
                PaymentRecordStatus paymentRecordStatus = new PaymentRecordStatus();
                paymentRecordStatus.setHiFeeNo(hiFeeNo);
                paymentRecordStatus.setIsPrint(String.valueOf(hiFees.get(0).getIsPrint()));
                paymentRecordStatus.setPayStatus(String.valueOf(hiFees.get(0).getStatus()));
                respond.getHiFeeList().add(paymentRecordStatus);
            }
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public BaseRespond updateRecipeStatusStatus(String synUserName,
                                                String synKey,
                                                String terminalCode,
                                                String hospitalId,
                                                String patientNo,
                                                String hiFeeNos,
                                                String extend) {
        BaseRespond respond = new BaseRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.UPDATE_RECIPE_STATUS_STATUS, synUserName, synKey,
                    terminalCode, hospitalId, patientNo, hiFeeNos, extend);

            respond = JsonTools.json2Bean(res, BaseRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public String preDoPay(String synUserName,
                           String synKey,
                           String terminalCode,
                           String hospitalId,
                           String hiFeeNos,
                           String payType,
                           String socialsecurityNO,
                           String hospitalNO,
                           String operatorNo,
                           String cycleNo,
                           String dynamic,
                           String medicareType,
                           String cardinfo
    ) {
        PreDoPayRespond respond = new PreDoPayRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return JSON.toJSONString(respond);
            }
            HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, TerminalVar.MEDICARETYPE_OUTPATIENT);

            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO, synUserName, synKey,
                    terminalCode, hospitalId, hiFeeNos, payType, socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, medicareType, cardinfo);

            // 根据社保号、获取User信息
            Map<String, String> object = (Map<String, String>) JSON.parse(res);

            //根据 cardInfo 获取信息
            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardinfo);
            // 获取  medicareInfo 调用待交费列表
            res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_PAY_RECIPE_INFO_LIST, synUserName, synKey,
                    terminalCode, hospitalId, hiFeeNos, payType, socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, medicareType, cardinfo);

            Map<String, Object> PayRecipeInfo = (Map<String, Object>) JSON.parse(res);
            JSONArray obj = (JSONArray) PayRecipeInfo.get("hiFee");
            JSONObject jsonObject = obj.getJSONObject(0);
            String medicareInfo = jsonObject.get("medicareInfo").toString();

            payInfo = siService.payBefore(hospitalSiConfig, hiFeeNos,
                    object.get("patientName"),
                    object.get("patientIdcardNo"),
                    socialsecurityNO,
                    medicareInfo,
                    null, null);
            respond.setTotalMoney(payInfo.getTotalMoney());
            respond.setCashMoney(payInfo.getCashMoney());
            respond.setOverMoney(payInfo.getOverMoney());
            respond.setPayMoney(payInfo.getPayMoney());
            return JSON.toJSONString(respond);
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return JSON.toJSONString(respond);
        }
    }

    @Override
    public MakeProductOrderRespond makeRegProductOrder(String synUserName,
                                                       String synKey,
                                                       String terminalCode,
                                                       String hospitalId,
                                                       String poType,
                                                       String objectId,
                                                       String poAllPrice,
                                                       String patientNo,
                                                       String patientName,
                                                       String cardId,
                                                       String socialsecurityNO,
                                                       String userNo,
                                                       String medicareType,
                                                       String medicareInfo,
                                                       String extend, String appId) {
        MakeProductOrderRespond respond = new MakeProductOrderRespond();

        try {
            Long orderId = seqService.getOrderId();
            Order order = new Order(orderId, Integer.valueOf(hospitalId), cardId,
                    patientName, ReservationVar.Order.ORDER_TYPE_APPOINTMENT, Short.valueOf(poType),
                    socialsecurityNO, patientNo, Short.valueOf(medicareType), String.valueOf(poAllPrice));
            tRhipOrderMapper.insertSelective(order);
            respond.setPoNo(String.valueOf(orderId));
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
        } catch (Exception e) {
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            log.error("", e);
            return respond;
        }
        return respond;

    }

    public String getUrlCode(Integer hospitalId, String payType, String appId, BigDecimal amount,
                             String orderId, boolean isReloadToken) throws Exception {
        PayParm payParm = payProxyService.getToken(hospitalId, payType, appId, isReloadToken);
        if (payParm == null) {
            return null;
        }
        PayScanReqData payScanReqData = payProxyService.signPayParam(amount, orderId, SIVar.WS.payCallBackUrl, payParm);
        String input = JsonTools.obj2Json(payScanReqData);
        log.info("------支付入参--------" + input);
        String output = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + ClientConst.ReqUrl.PAYSCAN_URL, input);
        log.info("------支付出参-------" + output);
        return orderDetail(hospitalId, payType, appId, amount, output, Long.valueOf(orderId), payParm.getProviderPayKey());
    }


    private String orderDetail(Integer hospitalId, String payType, String appId, BigDecimal amount,
                               String output, long orderId, String providerPayKey) throws Exception {
        //连接超时处理
        if (output == null || output.isEmpty()) {
            //保存订单状态记录表: 自费支付失败
            orderStatusService.insertOrderStatus(orderId, OrderStatus.PAY_SELF_FEE_FAILS, "自助机支付请求 扫码支付服务请求错误，返回为空");
            log.info("自助机支付请求 扫码支付服务请求错误，返回为空：orderId--" + orderId);
            return null;
        }

        PayScanResData res = JSON.parseObject(output, PayScanResData.class);

        //返回token无效或为空，则重新获取token，再次发送支付请求
        boolean tokenIsInvalid = PayConst.ErrorCode.TOKEN_INVALID.equals(res.getErrCode()) || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(res.getErrCode());
        if (tokenIsInvalid) {
            return getUrlCode(hospitalId, payType, appId, amount, String.valueOf(orderId), true);
        }

        //返回参数进行签名校验
        if (!payProxyService.isResSignatureValid(res, providerPayKey, res.getSign())) {
            //保存订单状态记录表: 自费支付失败
            orderStatusService.insertOrderStatus(orderId, OrderStatus.PAY_SELF_FEE_FAILS, "自助机支付请求 扫码支付服务 验签出错!");
            log.info("自助机支付请求 扫码支付服务 验签出错：orderId--" + orderId);
            return null;
        }

        if (PayConst.PayCode.FAIL.equals(res.getReturnCode()) || PayConst.PayCode.FAIL.equals(res.getResultCode())) {
            log.info("----token无效，再次请求pay服务后出错 Msg----：" + res.getReturnMsg());
            //保存订单状态记录表: 自费支付失败
            orderStatusService.insertOrderStatus(orderId, OrderStatus.PAY_SELF_FEE_FAILS, "自助机扫码支付请求服务后出错");
            return null;
        }
        if (StringUtils.isNotBlank(res.getCodeURL())) {
            orderService.updateByPrimaryKey(new Order(orderId, ReservationVar.Order.PAY_FLAG_PAYING));
        }
        return res.getCodeURL();
    }

    @Override
    public DoPayRespond doPay(String synUserName,
                              String synKey,
                              String terminalCode,
                              String hospitalId,
                              String hiFeeNos,
                              String payType,
                              String payRecord,
                              String payMoney,
                              String socialsecurityNO,
                              String hospitalNO,
                              String operatorNo,
                              String cycleNo,
                              String dynamic,
                              String bankInformation,
                              String isOverall,
                              String poNo,
                              String buyerAccount,
                              String userNo,
                              String medicareType
    ) {
/*        DoSettlementInput doSettlementInput = new DoSettlementInput();
        doSettlementInput.setOperCode(0L);
        doSettlementInput.setHosId(0L);
        doSettlementInput.setOrderId(0L);
        doSettlementInput.setRecordId(0L);
        doSettlementInput.setName("");
        doSettlementInput.setSocialNo("");
        doSettlementInput.setMedicareType("");
        doSettlementInput.setTotalMoney("");
        doSettlementInput.setPayMoney("");
        doSettlementInput.setOverMoney("");
        doSettlementInput.setCashMoney("");
        doSettlementInput.setIdNumber("");*/

        DoPayRespond respond = new DoPayRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Order order = orderService.getOrder(Long.valueOf(poNo));
            log.info("落地时订单信息:" + new Gson().toJson(order));
            if (order == null) {
                respond.setResultMsg("订单不存在");
                log.info("订单不存在:" + poNo);
                return respond;
            }
            if (order.getOrderTypeId() != Short.valueOf(ReservationVar.Order.ORDER_TYPE_OUTPATIENT)) {
                respond.setResultMsg("订单类型错误");
                log.info("订单类型错误:" + poNo);
                return respond;
            }

            if (order.getPayFlag() == null || order.getPayFlag() != Short.valueOf(ReservationVar.Order.PAY_FLAG_HAS_PAYED)) {
                respond.setResultMsg("订单未支付");
                log.info("订单未支付:" + poNo);
                return respond;
            }
            String res = synEvtServerJob.callDoPayWSNew(order, hospitalId);
            log.info("dopay出参:" + res);
            respond = new Gson().fromJson(res, DoPayRespond.class);
            if (TerminalVar.SUCCESS_CODE.equals(respond.getResultCode())) {
                tRhipOrderMapper.updateByPrimaryKey(new Order(order.getOrderId(), respond.getMedicalCode(),
                        respond.getClearingNO(), respond.getDispensaryWin(), respond.getGuidelinesInfo()));
                log.info("dopay落地成功更新返回结果:orderId" + order.getOrderId() + " " + res);
            }
            if (!TerminalVar.SUCCESS_CODE.equals(respond.getResultCode()) && StringUtils.isBlank(order.getMedicalCode())
                    && StringUtils.isBlank(order.getClearingNO()) && StringUtils.isBlank(order.getDispensaryWin())
                    && StringUtils.isBlank(order.getGuidelinesInfo())) {
                log.info("dopay落地失败增加异步落地:orderId" + order.getOrderId() + " " + res);
                syncEvtService.addNotifyPaySuccess(order.getOrderId(), order.getTransId());
                log.info("dopay落地失败新增记录:orderId" + order.getOrderId() + " " + res);
            }

          /*  //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_PATIENT_INFO_BY_SOCIALSECURITY_NO, synUserName, synKey, terminalCode, hospitalId, hiFeeNos, payType,
                    payRecord, payMoney, socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, bankInformation, isOverall, poNo, buyerAccount, userNo,
                    medicareType);

            Map<String, String> hisUserObj = (Map<String, String>) JSON.parse(res.toString());

            // 获取缴费信息 inputData
            // CardInfo  :  社保卡号||||固定|||身份证ID|姓名|NEW|
            String cardInfo = socialsecurityNO + "||||450900|||"
                    + hisUserObj.get("patientIdcardNo")
                    + "|"
                    + hisUserObj.get("patientName") + "|NEW|";
            HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, String.valueOf(medicareType));
            // 读卡信息
            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardInfo);
            // 待交费列表

            res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_PAY_RECIPE_INFO_LIST, synUserName, synKey, terminalCode, hospitalId, hiFeeNos, payType,
                    payRecord, payMoney, socialsecurityNO, hospitalNO, operatorNo, cycleNo, dynamic, bankInformation, isOverall, poNo, buyerAccount, userNo,
                    medicareType);
            
            Map<String, Object> PayRecipeInfo = (Map<String, Object>) JSON.parse(res.toString());
            JSONArray obj = (JSONArray) PayRecipeInfo.get("hiFee");
            JSONObject jsonObject = obj.getJSONObject(0);
            String medicareInfo = jsonObject.get("medicareInfo").toString();
            String[] medicareInfoSub = medicareInfo.split(">|<");
            Order orderInfo = orderService.getOrder(Long.valueOf(poNo));
            if (orderInfo == null) {
                respond.setResultMsg(JSON.toJSONString(R.resultError("查询订单错误！")));
                return respond;
            }
            if (ReservationVar.PayPattern.SELF_FEE != orderInfo.getPayPattern()) {
                // 社保支付接口调用
                payInfo = siService.payment(hospitalSiConfig,
                        0,
                        hiFeeNos,
                        poNo,
                        hisUserObj.get("patientName"),
                        hisUserObj.get("patientIdcardNo"),
                        socialsecurityNO,
                        medicareInfoSub[2],
                        Long.valueOf(poNo)
                );
            }

            //更新：订单信息
            Order order = new Order();
            order.setOrderId(Long.valueOf(poNo));
            order.setCashMoney(payMoney);
            order.setTransId(Long.valueOf(payRecord));
            order.setStatus(ReservationVar.Order.ORDER_STATUS_CONFIRMED);
            order.setPayFlag(ReservationVar.Order.PAY_FLAG_HAS_PAYED);
            order.setPayType(Short.valueOf(payType));
            order.setPayBody(orderInfo.getPayBody());
            order.setPayPattern(orderInfo.getPayPattern());
            if (ReservationVar.PayPattern.SELF_FEE == orderInfo.getPayPattern()) {
                order.setMedicarePayState(ReservationVar.MedicarePayState.NOT_PAY);
            } else {
                order.setMedicarePayState((ReservationVar.PayPattern.MIX_FEE == orderInfo.getPayPattern() ? ReservationVar.MedicarePayState.HAS_PAYED : ReservationVar.MedicarePayState.PAY_FAULT));
            }

            log.info("----payReceive发送更新订单----：" + JSON.toJSONString(order));
            int updateSIOrderRes = orderService.updateOrderWithMedicare(order);
            log.info("----payReceive发送更新订单返回----：" + updateSIOrderRes);

            //判断：更新订单返回是否错误
            if (updateSIOrderRes <= 0) {
                //保存订单状态记录表: 自费支付失败
                orderStatusService.insertOrderStatus(Long.valueOf(poNo), OrderStatus.PAY_SELF_FEE_FAILS, "更新订单返回有误：" + updateSIOrderRes);
                respond.setResultMsg(JSON.toJSONString(R.resultError("更新订单返回有误")));
                return respond;
            }
            //保存订单状态记录表: 自费支付成功
            orderStatusService.insertOrderStatus(Long.valueOf(poNo), OrderStatus.PAY_SELF_FEE_SUCCESS, "终端 自费支付成功");
            respond.setResultMsg(JSON.toJSONString(R.resultError("终端 自费支付成功")));*/
            return respond;
        } catch (Exception e) {
            log.error("落地报错信息", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public String printInfo(String jsonStr) {
        return null;// TODO 平台-终端文档输出描述不明确，无法编码
    }

    @Override
    public TradePrecreateRespond trade_precreate(String synUserName,
                                                 String synKey,
                                                 String terminalCode,
                                                 String hospitalId,
                                                 String poNo,
                                                 String payType) {
        TradePrecreateRespond respond = new TradePrecreateRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Order tRhipOrder = tRhipOrderMapper.selectOrderByOrderId(Long.valueOf(poNo));
            if (tRhipOrder == null) {
                respond.setResultMsg("订单不存在");
                return respond;
            }
            log.info("appid:" + tRhipOrder.getAppId());
            String urlCode = getUrlCode(Integer.valueOf(hospitalId), payType, tRhipOrder.getAppId(),
                    new BigDecimal(tRhipOrder.getCashMoney()), String.valueOf(tRhipOrder.getOrderId()), false);
            if (StringUtils.isNotBlank(urlCode)) {
                respond.setQrCode(urlCode);
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
            }
            return respond;
        } catch (Exception e) {
            log.error("创建订单", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryOrderStatusRespond query_orderStatus(String synUserName,
                                                     String synKey,
                                                     String terminalCode,
                                                     String hospitalId,
                                                     String poNo,
                                                     String payType) {
        log.info("--终端轮训开始--hospitalId--" + hospitalId + "--poNo----" + poNo + "-payType---" + payType);
        QueryOrderStatusRespond respond = new QueryOrderStatusRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setPayStatus(TerminalVar.PAY_STATUS_FAIL);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                log.info("---终端轮训返回--poNo--" + poNo + " " + new Gson().toJson(respond));
                return respond;
            }


            Order order = simpleQueryDao.getOrderByOrderId(Long.valueOf(poNo));
            if (order == null) {
                log.info("---终端轮训返回--poNo--" + poNo + " " + new Gson().toJson(respond));
                respond.setResultMsg("订单不存在");
                return respond;
            }

            PayParm payParm = payProxyService.getToken(Integer.valueOf(hospitalId), payType, order.getAppId(), false);
            if (payParm == null) {
                return null;
            }

            String output = queryPayStatus(hospitalId, payType, order, poNo, false);
            //返回token无效或为空，则重新获取token，再次发送支付请求
            JSONObject object = JSON.parseObject(output);
            String errCode = object.getString("errCode");
            boolean tokenIsInvalid = PayConst.ErrorCode.TOKEN_INVALID.equals(errCode) || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(errCode);
            if (tokenIsInvalid) {
                output = queryPayStatus(hospitalId, payType, order, poNo, true);
            }

            if (StringUtils.isBlank(output)) {
                respond.setResultMsg("查询失败");
                return respond;
            }

            String status = object.getString("transCode");

            if (StringUtils.isBlank(status)) {
                respond.setResultMsg("查询失败");
                return respond;
            }

            if (status.equals(PayConst.ErrorCode.HAS_PAY)) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setPayStatus(TerminalVar.PAY_STATUS_SUCCESS);
                respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
                respond.setPayRecord(order.getTransId() + "");
                log.info("--------应用appId---------" + order.getAppId());
                updateOrder(order, object.getLong("transId"));
            } else {
                respond.setResultCode(TerminalVar.FAIL_CODE);
                respond.setPayStatus(TerminalVar.PAY_STATUS_FAIL);
                respond.setResultMsg(TerminalVar.STATUS_FAIL);
            }

            log.info("---终端轮训返回--poNo--" + poNo + " " + new Gson().toJson(respond));
            return respond;
        } catch (Exception e) {
            log.error("落地报错信息", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            log.info("---终端轮训返回--poNo--" + poNo + " " + new Gson().toJson(respond));
            return respond;
        }
    }


    /**
     * 查询私有方法
     *
     * @param hospitalId 医院编码
     * @param payType    支付类型
     * @param order      订单
     * @param poNo       订单编号
     * @param reload     是否重新加载
     * @return
     * @throws Exception
     */
    private String queryPayStatus(String hospitalId, String payType, Order order, String poNo, boolean reload) throws Exception {

        PayParm payParm = payProxyService.getToken(Integer.valueOf(hospitalId), payType, order.getAppId(), reload);
        if (payParm == null) {
            return null;
        }
        QueryPayStatusReqData queryPayStatusReqData = payProxyService.queryPayStatus(poNo, payParm);
        String input = JsonTools.obj2Json(queryPayStatusReqData);
        log.info("------查询状态入参--------" + input);
        String output = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + ClientConst.ReqUrl.PAY_QUERY_URL, input);
        log.info("------查询状态出参-------" + output);

        return output;
    }


    public void updateOrder(Order orderInfo, Long transId) {
        log.info("查询接口更新订单");
        if (orderInfo.getPayFlag() != null && orderInfo.getPayFlag() == Short.valueOf(ReservationVar.Order.PAY_FLAG_HAS_PAYED)) {
            log.info("订单已经是支付状态不需要更新:" + orderInfo.getOrderId());
            return;
        }
        Short payPattern = orderInfo.getPayPattern();
        //更新：订单信息
        Order order = new Order();
        order.setOrderId(orderInfo.getOrderId());
        order.setTransId(transId);
        order.setStatus(ReservationVar.Order.ORDER_STATUS_CONFIRMED);
        order.setPayFlag(ReservationVar.Order.PAY_FLAG_HAS_PAYED);
        if (payPattern == ReservationVar.PayPattern.SELF_FEE) {
            order.setMedicarePayState(ReservationVar.MedicarePayState.NOT_PAY);
        } else {
            order.setMedicarePayState((payPattern == ReservationVar.PayPattern.MIX_FEE ? ReservationVar.MedicarePayState.HAS_PAYED : ReservationVar.MedicarePayState.NOT_PAY));
        }
        //此处需要设置appId，否则会造成异步落地 添加时间 20190425 haomeiling
        order.setAppId(orderInfo.getAppId());


        log.info("查询状态接口----payReceive发送更新订单----：" + JSON.toJSONString(order));
        int updateSIOrderRes = orderService.updateOrderWithMedicare(order);
        log.info("查询状态接口----payReceive发送更新订单返回----：" + updateSIOrderRes);

        //判断：更新订单返回是否错误
        if (updateSIOrderRes <= 0) {
            //保存订单状态记录表: 自费支付失败
            orderStatusService.insertOrderStatus(order.getOrderId(), OrderStatus.PAY_SELF_FEE_FAILS, "payReceive发送更新订单返回有误：" + updateSIOrderRes);
            return;
        }
        //保存订单状态记录表: 自费支付成功
        orderStatusService.insertOrderStatus(order.getOrderId(), OrderStatus.PAY_SELF_FEE_SUCCESS, "掌上医院 自费支付成功");
    }

    @Override
    public MakeProductOrderRespond makeProductOrder(String synUserName, String synKey, String terminalCode, String hospitalId,
                                                    String hiFeeNos, String payType, String poType, String socialsecurityNO, String poAllPrice,
                                                    String payMoney, String isOverall, String patientNo, String patientName,
                                                    String cardId, String sex, String medicareType, String cardinfo,
                                                    String medicareMess, String originalPrice, String appId
    ) {
        MakeProductOrderRespond respond = new MakeProductOrderRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            Order order = new Order();
            Long newId = seqService.getOrderId();
            order.setOrderId(newId);
            order.setHospitalId(Integer.valueOf(hospitalId));
            order.setCertIdno(cardId);
            order.setPeerOrderNo(hiFeeNos);
            order.setPatientName(patientName);
            order.setCashMoney(payMoney);
            order.setAmount(new BigDecimal(payMoney));
            order.setPayType(synEvtServerJob.payTypeConvertToDB(Short.valueOf(payType)));//将类型转换为数据库类型，haomeiling 20190411
            order.setSocialNo(socialsecurityNO);
            if (!ReservationVar.AppID.APPID_ZDIV10.equals(appId) && !ReservationVar.AppID.APPID_YZDIV10.equals(appId)) {
                respond.setResultCode(TerminalVar.FAIL_CODE);
                respond.setResultMsg("不识别的终端");
                return respond;
            }
            order.setAppId(appId);
            order.setPatientNo(patientNo);
            order.setMedicareType(Short.valueOf(medicareType));
            order.setOrderTypeId(Short.valueOf(poType));
            order.setTerminalCode(terminalCode);
            if ("0".equals(isOverall)) {
                order.setPayPattern(Short.valueOf(ReservationVar.PayPattern.SELF_FEE + ""));
            } else if (String.valueOf(ReservationVar.PayType.PAY_TYPE_SI).equals(payType) && StringUtils.isNotBlank(socialsecurityNO)) {
                order.setPayPattern(Short.valueOf(ReservationVar.PayPattern.SOSIAL_FEE + ""));
            }

            if (tRhipOrderMapper.insertSelective(order) > 0) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
                respond.setPoNo(String.valueOf(newId));
            }
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public BaseRespond yibaoFallback(String synUserName,
                                     String synKey,
                                     String terminalCode,
                                     String hospitalId,
                                     String poNo,
                                     String socialsecurityNO,
                                     String patientNo,
                                     String poAllPrice) {
        BaseRespond respond = new BaseRespond(TerminalVar.FAIL_CODE, TerminalVar.STATUS_FAIL);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            CancelOrderInput cancelOrderInput = new CancelOrderInput();
            cancelOrderInput.setCancelReason(HisConvertConst.Operation.CANCEL_ORDER);
            cancelOrderInput.setHosId(Integer.valueOf(hospitalId));
            cancelOrderInput.setOrderNo(poNo);
            cancelOrderInput.setCancelReason("终端关闭");
            String outPutStr = cancelOrder.sendToHisOperation(JSON.toJSONString(cancelOrderInput), tRhipConnectParm);
            CancelOrderOutput output = JsonTools.json2Bean(outPutStr, CancelOrderOutput.class);
            if (TerminalVar.CANCEL_ORDER_SUCCESS == output.getSuccess()) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setResultMsg(output.getMsg());
            }
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public BaseRespond tradeFallback(String synUserName,
                                     String synKey,
                                     String terminalCode,
                                     String hospitalId,
                                     String poNo,
                                     String socialsecurityNO,
                                     String patientNo,
                                     String poAllPrice) {
        BaseRespond respond = new BaseRespond(TerminalVar.FAIL_CODE, TerminalVar.STATUS_FAIL);
        respond.setResultMsg("终端不能发起退费");
        return respond;
        /*try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            CancelOrderInput cancelOrderInput = new CancelOrderInput();
            cancelOrderInput.setCancelReason(HisConvertConst.Operation.CANCEL_ORDER);
            cancelOrderInput.setHosId(Integer.valueOf(hospitalId));
            cancelOrderInput.setOrderNo(poNo);
            cancelOrderInput.setCancelReason("终端关闭");
            String outPutStr = cancelOrder.sendToHisOperation(JSON.toJSONString(cancelOrderInput), tRhipConnectParm);
            CancelOrderOutput output = JsonTools.json2Bean(outPutStr, CancelOrderOutput.class);
            if (TerminalVar.CANCEL_ORDER_SUCCESS == output.getSuccess()) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setResultMsg(output.getMsg());
            }
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE);
            respond.setResultMsg(e.getMessage());
            return respond;
        }*/
    }

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey        String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param hospitalNO                医院编号
     * @Param operatorNo                操作人员编号
     * @Param cycleNo                    周期编号
     * @Param dynamic                    动态库参数（签到交易为：客户端MAC地址|用户数目|）
     * @Param poNo                        订单号
     * @Param payMoney        String	否	个账支付金额
     * @Param extend        String	否	扩展字段Json值，如： {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/26
     * @Description 1.4.14    挂号医保支付
     */
    @Override
    public DoRegPayRespond doRegPay(String synUserName,
                                    String synKey,
                                    String terminalCode,
                                    String hospitalId,
                                    String operatorNo,
                                    String cycleNo,
                                    String dynamic,
                                    String poNo,
                                    String payMoney,
                                    String extend) {
        DoRegPayRespond respond = new DoRegPayRespond();
        respond.setResultmessage(new DoRegPayMgs());
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);

        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            // 1、医保支付
            Order tRhipOrder = tRhipOrderMapper.selectByPrimaryKey(Long.valueOf(poNo));
            // 获取缴费信息 inputData
            // CardInfo  :  社保卡号||||固定|||身份证ID|姓名|NEW|
            String cardInfo = tRhipOrder.getPatientNo() + "||||450900|||"
                    + tRhipOrder.getCertIdno()
                    + "|"
                    + tRhipOrder.getPatientName() + "|NEW|";

            HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, String.valueOf(tRhipOrder.getMedicareType()));
            // 读卡信息
            PayInfo payInfo = siService.payment(hospitalSiConfig,
                    0,
                    "", // 等终端调用再写吧、hiFeeNos,
                    poNo,
                    tRhipOrder.getPatientName(),
                    tRhipOrder.getCertIdno(),
                    tRhipOrder.getSocialNo(),
                    "", // // 等终端调用再写吧、hiFeeNos, medicareInfoSub[2],
                    Long.valueOf(poNo)
            );
            // 2、支付成功后，加入T_RHIP_SYNC_EVT_PENDING 告知HIS 支付落地
            // 3、返回支付信息到终端
            DoRegPayMgs doRegPayMgs = new DoRegPayMgs();
            doRegPayMgs.setMedicalCode("");//医院就医码
            doRegPayMgs.setClearingNO("");//医院结算流水号
            doRegPayMgs.setTransactionTime("");//交易时间
            doRegPayMgs.setDispensaryWin("");//发药窗口
            doRegPayMgs.setGuidelinesInfo("");//指引信息
            doRegPayMgs.setReserve("");//备用字段

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultCode(TerminalVar.FAIL_CODE);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }


    @Override
    public TradeCloseRespond trade_close(String synUserN,
                                         String synKey,
                                         String terminalCode,
                                         String hospitalId,
                                         String poNo,
                                         String payType
    ) {
        TradeCloseRespond respond = new TradeCloseRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        respond.setCloseStatus(TerminalVar.STATUS_FAIL);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Map<String, Object> inParam = new HashMap<>();
            inParam.put("orderId", poNo);
            inParam.put("canceledReason", "终端调关闭");
            //如果系统有异常，则会返回异常
            orderMapper.orderCancel(inParam);


            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg("终端调关单成功");
            respond.setCloseStatus(TerminalVar.STATUS_SUCCESS);

            /*CancelOrderInput cancelOrderInput = new CancelOrderInput();
            cancelOrderInput.setCancelReason(HisConvertConst.Operation.CANCEL_ORDER);
            cancelOrderInput.setHosId(Integer.valueOf(hospitalId));
            cancelOrderInput.setOrderNo(poNo);
            cancelOrderInput.setCancelReason("终端关闭");
            String outPutStr = cancelOrder.sendToHisOperation(JSON.toJSONString(cancelOrderInput), tRhipConnectParm);
            CancelOrderOutput output = JsonTools.json2Bean(outPutStr, CancelOrderOutput.class);
            if (TerminalVar.CANCEL_ORDER_SUCCESS == output.getSuccess()) {
                respond.setResultCode(TerminalVar.SUCCESS_CODE);
                respond.setResultMsg(output.getMsg());
                respond.setCloseStatus(TerminalVar.STATUS_SUCCESS);
            }else{
                respond.setResultMsg(output.getMsg());
            }*/
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public BaseRespond updateRecipeRecordStatus(String synUserName, String synKey, String terminalCode,
                                                String hospitalId, String hiFeeNos, String extend) {
        BaseRespond respond = new BaseRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.UPDATE_RECIPE_RECORD_STATUS, synUserName, synKey, hiFeeNos, extend);

            //解析返回
            respond = new Gson().fromJson(res, BaseRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("", e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public String queryOrderRecords(String synUserName, String synKey, String startTime, String endTime, String orderNo, String hospitalId) {
        QueryTransResData queryTransResData = billDownService.queryOrderTrans(synUserName, synKey, startTime, endTime, orderNo, hospitalId);
        return new Gson().toJson(queryTransResData);
    }
}
