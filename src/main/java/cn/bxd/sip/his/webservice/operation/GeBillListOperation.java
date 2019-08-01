package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.*;
import cn.bxd.sip.his.model.dto.reservation.GetBillListItemResDatas;
import cn.bxd.sip.his.model.dto.reservation.GetBillListReqDatas;
import cn.bxd.sip.his.model.dto.reservation.GetBillListResDatas;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description: 门诊结算详情列表 2028 1.4 缴费列表 1.6 住院信息
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@Slf4j
@Component
public class GeBillListOperation extends AbstractOperationProcessor {


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {

        //入参：掌上医院请求入参
        GetBillListReqDatas getBillListReqDatas = JSON.parseObject(reqMsg, GetBillListReqDatas.class);
        //当clinic type 为1(门诊)billTypeId 还不分；
        //当clinic type 是2(住院)的时候，区分 billTypeId(1: 住院费用清单，2：住院押金)
        int clinicType = getBillListReqDatas.getClinicType();

        //区分：1未缴费、2已缴费
        int billStauts = getBillListReqDatas.getStatus();

        //1: 住院费用清单，2：住院押金
        int billTypeId = getBillListReqDatas.getBillTypeId();

        //empi获取
        String empi = getBillListReqDatas.getEmpi();


        //用户名和校验码
        String sysUserName = tRhipConnectParm.getUserName();
        String sysKey = tRhipConnectParm.getCheckCode();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //封装：向HIS发送请求
        String resStr;
        try {
            //门诊类型
            if (clinicType == HisConvertConst.ReservationCode.CLINIC_TYPE_CODE) {
                //ws客户端：1.4 缴费列表
                Object hosWsClient3 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_PAY, tRhipConnectParm);
                //未缴费
                if (billStauts == HisConvertConst.ReservationCode.BILL_STAUTS_NO_PAY_CODE) {
                    resStr = HisWSClient.invoke(hosWsClient3, HisFunNameConst.QUERY_TO_PAY_RECIPE_INFO_LIST, sysUserName, sysKey,
                            empi, "0", "", "", "");
                    log.debug(" 2028 待交费列表 HIS返回：" + resStr);
                }
                //已缴费
                else {
                    resStr = HisWSClient.invoke(hosWsClient3, HisFunNameConst.QUERY_PAYMENT_RECORD_LIST, sysUserName, sysKey,
                            empi, sdf.format(DateUtils.getFrontDay(new Date(), 365)), sdf.format(new Date()));

                    log.debug(" 2028 已交费列表 HIS返回：" + resStr);
                }
            }
            //住院类型
            else {
                //ws客户端：1.6 住院信息
                Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_IN_HOS, tRhipConnectParm);
                //住院费用清单
                if (billTypeId == HisConvertConst.ReservationCode.BILL_TYPE_ID_IN_HOS_CODE) {
                    resStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.IN_HOS_DETAIL, sysUserName, sysKey,
                            getBillListReqDatas.getEmpi(), getBillListReqDatas.getClinicNo(), sdf.format(DateUtils.getFrontDay(new Date(), 365)), sdf.format(new Date()));
                    log.debug(" 2028 住院费用清单 HIS返回：" + resStr);
                }
                //住院押金
                else {
                    resStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.PAY_DETAIL, sysUserName, sysKey,
                            getBillListReqDatas.getEmpi(), getBillListReqDatas.getClinicNo(), sdf.format(DateUtils.getFrontDay(new Date(), 365)), sdf.format(new Date()));

                    log.debug(" 2028 住院押金 HIS返回：" + resStr);
                }
            }
        } catch (Exception e) {
            log.info(" 2028 HIS返回有误：" + e.getMessage());
            log.error("", e);
            return errMsgReturn("连接有误");
        }

        String outStr;
        //转换数据：门诊类型
        if (clinicType == HisConvertConst.ReservationCode.CLINIC_TYPE_CODE) {
            outStr = transformToClinicStr(resStr, billStauts, getBillListReqDatas.getHosId());
        }
        //转换数据：住院类型
        else {
            //住院费用清单
            if (billTypeId == HisConvertConst.ReservationCode.BILL_TYPE_ID_IN_HOS_CODE) {
                outStr = transformToInHosDetailStr(resStr, billStauts, getBillListReqDatas.getHosId());
            }
            //住院押金
            else {
                outStr = transformToDepositStr(resStr, billStauts, getBillListReqDatas.getHosId());
            }
        }

        return outStr;
    }


    /**
     * 住院押金
     */
    private String transformToDepositStr(String resStr, int billStauts, String hosId) {

        //解析：his返回成对象
        PayDetailRes payDetailRes = JSON.parseObject(resStr, PayDetailRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, payDetailRes.getResultCode())) {
            return errMsgReturn(payDetailRes.getResultMsg());
        }

        List<GetBillListItemResDatas> listItemResDatas = new ArrayList<>();
        List<PayDetailItemRes> payList = payDetailRes.getPayList();

        for (PayDetailItemRes p : payList) {
            GetBillListItemResDatas getBillListItemResDatas = new GetBillListItemResDatas();
            getBillListItemResDatas.setBillNo(p.getInHosSerialNo());
            getBillListItemResDatas.setClinicalNo(p.getInHosSerialNo());
            getBillListItemResDatas.setAmount(new BigDecimal(p.getPayMoney()));
            getBillListItemResDatas.setDepositAmount(Double.valueOf(p.getPayMoney()));
            getBillListItemResDatas.setBillDate(Long.valueOf(StringUtils.replaceAll(StringUtils.substring(p.getPayDate(), 0, 10), "-", "").trim()));
            getBillListItemResDatas.setAccDate(Long.valueOf(StringUtils.replaceAll(StringUtils.substring(p.getPayDate(), 0, 10), "-", "").trim()));
            getBillListItemResDatas.setBillStatus(billStauts);
            listItemResDatas.add(getBillListItemResDatas);
        }

        GetBillListResDatas getBillListResDatas = new GetBillListResDatas();
        getBillListResDatas.setOperCode(HisConvertConst.Operation.GET_CLINIC_INFO_LIST_REQ_CODE);
        getBillListResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getBillListResDatas.setHosId(hosId);
        getBillListResDatas.setData(listItemResDatas);
        return JSON.toJSONString(getBillListResDatas);
    }

    /**
     * 住院清单详情
     */
    private String transformToInHosDetailStr(String resStr, int billStauts, String hosId) {

        //解析：his返回成对象
        InHosDetailRes inHosDetailRes = JSON.parseObject(resStr, InHosDetailRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, inHosDetailRes.getResultCode())) {
            return errMsgReturn(inHosDetailRes.getResultMsg());
        }
        List<GetBillListItemResDatas> listItemResDatas = new ArrayList<>();
        List<InHosDetailItemRes> inHosList = inHosDetailRes.getInHosList();
        //BigDecimal bigDecimal = new BigDecimal(0);
        Map<Long, BigDecimal> map = new HashMap();
        int size = inHosList.size();
        for (int i = 0; i < size; i++) {
            Long chargeDate = Long.valueOf(StringUtils.replaceAll(inHosList.get(i).getChargeDate(), "-", ""));
            if (map.get(chargeDate) == null) {
                map.put(chargeDate, new BigDecimal(inHosList.get(i).getFeeItemAllAmount()));
                GetBillListItemResDatas getBillListItemResDatas = new GetBillListItemResDatas();
                getBillListItemResDatas.setBillNo(inHosList.get(i).getProjectCode());
                getBillListItemResDatas.setClinicalNo(inHosList.get(i).getProjectCode());
                getBillListItemResDatas.setAccDate(Long.valueOf(StringUtils.replaceAll(inHosList.get(i).getChargeDate(), "-", "")));
                getBillListItemResDatas.setBillDate(Long.valueOf(StringUtils.replaceAll(inHosList.get(i).getChargeDate(), "-", "")));
                /*bigDecimal = bigDecimal.add(new BigDecimal(inHosList.get(i).getFeeItemAllAmount()));
                getBillListItemResDatas.setAmount(bigDecimal);*/
                getBillListItemResDatas.setBillStatus(billStauts);
                listItemResDatas.add(getBillListItemResDatas);
            } else {
                BigDecimal bigDecimal1 = map.get(chargeDate).add(new BigDecimal(inHosList.get(i).getFeeItemAllAmount()));
                map.put(chargeDate, bigDecimal1);
            }

            //由于汇总单页面显示不全，注释并修改  lisheng 2019/5/9
            /*bigDecimal = bigDecimal.add(new BigDecimal(inHosList.get(i).getFeeItemAllAmount()));
            if (i == size - 1) {
                GetBillListItemResDatas getBillListItemResDatas = new GetBillListItemResDatas();
                getBillListItemResDatas.setBillNo(inHosList.get(i).getProjectCode());
                getBillListItemResDatas.setClinicalNo(inHosList.get(i).getProjectCode());
                getBillListItemResDatas.setAccDate(Long.valueOf(StringUtils.replaceAll(inHosList.get(i).getChargeDate(), "-", "")));
                getBillListItemResDatas.setBillDate(Long.valueOf(StringUtils.replaceAll(inHosList.get(i).getChargeDate(), "-", "")));
                bigDecimal = bigDecimal.add(new BigDecimal(inHosList.get(i).getFeeItemAllAmount()));
                getBillListItemResDatas.setAmount(bigDecimal);
                getBillListItemResDatas.setBillStatus(billStauts);
                listItemResDatas.add(getBillListItemResDatas);
            }*/
        }
        //封装金额
        for (GetBillListItemResDatas itemDatas : listItemResDatas) {
            itemDatas.setAmount(map.get(itemDatas.getAccDate()));
        }
        GetBillListResDatas getBillListResDatas = new GetBillListResDatas();
        getBillListResDatas.setOperCode(HisConvertConst.Operation.GET_CLINIC_INFO_LIST_REQ_CODE);
        getBillListResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getBillListResDatas.setHosId(hosId);
        getBillListResDatas.setData(listItemResDatas);
        return JSON.toJSONString(getBillListResDatas, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 门诊数据转换
     */
    private String transformToClinicStr(String resStr, int billStauts, String hosId) {

        //解析：his返回成对象
        QueryToPayRecipeInfoListRes queryToPayRecipeInfoListRes = JSON.parseObject(resStr, QueryToPayRecipeInfoListRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryToPayRecipeInfoListRes.getResultCode())) {
            return errMsgReturn(queryToPayRecipeInfoListRes.getResultMsg());
        }

        List<GetBillListItemResDatas> listItemResDatas = new ArrayList<>();

        List<QueryToPayRecipeInfoListItem> hiFee = queryToPayRecipeInfoListRes.getHiFee();

        for (QueryToPayRecipeInfoListItem q : hiFee) {
            //缴费状态：1表示已缴费，0表示未缴费
            short status = (short) (q.getStatus() + 1);
            if (billStauts == status) {
                GetBillListItemResDatas getBillListItemResDatas = new GetBillListItemResDatas();
                getBillListItemResDatas.setBillNo(q.getHiFeeNo());
                getBillListItemResDatas.setClinicalNo(q.getHiFeeNo());
                getBillListItemResDatas.setDoctorName(q.getDoctorName());
                getBillListItemResDatas.setDeptName(q.getOrganName());
                getBillListItemResDatas.setAmount(new BigDecimal(q.getSettleAmount()));
                getBillListItemResDatas.setBillDate(Long.valueOf(StringUtils.replaceAll(StringUtils.substring(q.getCreateTime(), 0, 10), "-", "")));
                getBillListItemResDatas.setAccDate(Long.valueOf(StringUtils.replaceAll(StringUtils.substring(q.getVisitDate(), 0, 10), "-", "")));
                getBillListItemResDatas.setBillStatus(billStauts);
                getBillListItemResDatas.setMedicareInfo(q.getMedicareInfo());
                getBillListItemResDatas.setMedicareType(q.getMedicareType());
                getBillListItemResDatas.setDiagInfo(q.getDiagInfo());
                listItemResDatas.add(getBillListItemResDatas);
            }
        }

        GetBillListResDatas getBillListResDatas = new GetBillListResDatas();
        getBillListResDatas.setOperCode(HisConvertConst.Operation.GET_CLINIC_INFO_LIST_REQ_CODE);
        getBillListResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getBillListResDatas.setHosId(hosId);
        getBillListResDatas.setData(listItemResDatas);
        return JSON.toJSONString(getBillListResDatas);
    }


}