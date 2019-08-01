package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.SiRealUser;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.IRealUserService;
import cn.bxd.sip.bxd.var.ClinicType;
import cn.bxd.sip.bxd.var.OrderStatus;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.*;
import cn.bxd.sip.his.model.dto.reservation.*;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 门诊结算详情 2029 1.4 缴费列表 1.6 住院信息
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@Slf4j
@Component
public class GeBillInfoListOperation extends AbstractOperationProcessor {

    @Autowired
    private HospitalSiConfigMapper hospitalSiConfigMapper;

    @Autowired
    private SiService siService;

    @Autowired
    private IOrderStatusService orderStatusService;

    @Autowired
    private IRealUserService realUserService;

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        //入参：掌上医院请求入参
        GetBillInfoListReqDatas getBillInfoListReqDatas = JSON.parseObject(reqMsg, GetBillInfoListReqDatas.class);
        String billNo = getBillInfoListReqDatas.getBillNo();
        String clinicNo = getBillInfoListReqDatas.getClinicNo();
        String strNo = StringUtils.isNotBlank(billNo) ? billNo : clinicNo;

        //用户名和校验码
        String sysUserName = tRhipConnectParm.getUserName();
        String sysKey = tRhipConnectParm.getCheckCode();

        log.debug("billNo:" + billNo);
        log.debug("clinicNo:" + clinicNo);

        //当clinic type 为1(门诊)billTypeId 还不分；
        //当clinic type 是2(住院)的时候，区分 billTypeId(1: 住院费用清单，2：住院押金)
        int clinicType = getBillInfoListReqDatas.getClinicType();

        //区分：1未缴费、2已缴费
        Integer billStauts = getBillInfoListReqDatas.getStatus();

        if (billStauts == null) {
            return errMsgReturn("status 不能为空!");
        }

        SimpleDateFormat hSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat rSdf = new SimpleDateFormat("yyyyMMdd");

        //0,非医保 1医保  为1时，需要向社保发送请求获取医保卡信息
        int medicareType = getBillInfoListReqDatas.getMedicareType();
        String medicareMess = "";
        if (medicareType != 0) {
            String socialNo = getBillInfoListReqDatas.getSocialNo();
            String idNumber = getBillInfoListReqDatas.getIdNumber();
            String name = getBillInfoListReqDatas.getName();

            //判断参数不为空
            if (StringUtils.isBlank(socialNo) && StringUtils.isBlank(idNumber) && StringUtils.isBlank(name)) {
                return errMsgReturn("socialNo idNumber name 不能为空!");
            }

            //保存订单状态记录表：社保移动支付读卡中
            orderStatusService.insertOrderStatus(getBillInfoListReqDatas.getOrderId(), OrderStatus.PAYING_READ_CARD, "2029 社保移动支付读卡中");

            //向社保获取医保卡号信息
            medicareMess = getMedicareMess(getBillInfoListReqDatas.getHosId(), String.valueOf(medicareType), socialNo, idNumber, name);

            //判断返回不为空
            if (StringUtils.isBlank(medicareMess) || medicareMess.equals("")) {
                //保存订单状态记录表：社保移动支付失败
                orderStatusService.insertOrderStatus(getBillInfoListReqDatas.getOrderId(), OrderStatus.PAY_SOCIAL_SECURITY_FAILS, "2029 社保移动支付失败");
                return errMsgReturn(getBillInfoListReqDatas.getName() + ":" + getBillInfoListReqDatas.getIdNumber() + ",不是本人操作！");
            }
            //保存订单状态记录表：社保移动支付数据读取中
            orderStatusService.insertOrderStatus(getBillInfoListReqDatas.getOrderId(), OrderStatus.PAYING_READ_DATA, "2029 社保移动支付数据读取中");
            medicareType = 1;
        }

        String empi = getBillInfoListReqDatas.getEmpi();

        //封装：向HIS发送请求
        String resStr = "";
        try {
            //门诊类型
            if (clinicType == ClinicType.MZ.getTypeVal()) {
                //ws客户端：1.4 缴费列表
                Object hosWsClient3 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_PAY, tRhipConnectParm);

                //未缴费
                if (billStauts == HisConvertConst.ReservationCode.BILL_STAUTS_NO_PAY_CODE) {
                    resStr = HisWSClient.invoke(hosWsClient3, HisFunNameConst.QUERY_TO_PAY_RECIPE_INFO_LIST, sysUserName, sysKey, empi, String.valueOf(medicareType), medicareMess, "", "");
                    log.debug(" 2029 待交费列表 HIS返回：" + resStr);
                }
                //已缴费
                else {
                   /* String startDate=hSdf.format(DateUtils.getFrontDay(new Date(), 365));
                    String endDate=hSdf.format(new Date());*/
                    String startDate = DateUtils.strToDateFormat(getBillInfoListReqDatas.getStartDate());
                    String endDate = DateUtils.strToDateFormat(getBillInfoListReqDatas.getEndDate());
                    resStr = HisWSClient.invoke(hosWsClient3, HisFunNameConst.QUERY_PAYMENT_RECORD_LIST, sysUserName, sysKey, empi, startDate, endDate);
                    //resStr = HisWSClient.invoke(hosWsClient3, HisFunNameConst.QUERY_TO_PAY_RECIPE_BY_HI_FEE_NO, sysUserName, sysKey, strNo, empi, String.valueOf(medicareType), medicareMess,"");
                    log.debug(" 2029 已交费列表 HIS返回：" + resStr);
                }

            }
            //住院类型
            else if (clinicType == ClinicType.ZY.getTypeVal()) {
                //ws客户端：1.6 住院信息
                Object hosWsClient5 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_IN_HOS, tRhipConnectParm);
                String startDate = hSdf.format(rSdf.parse(getBillInfoListReqDatas.getStartDate()));
                String endDate = hSdf.format(rSdf.parse(getBillInfoListReqDatas.getEndDate()));
                resStr = HisWSClient.invoke(hosWsClient5, HisFunNameConst.IN_HOS_DETAIL, sysUserName, sysKey, empi, strNo, startDate, endDate);
                log.debug(" 2029 住院费用清单 HIS返回：" + resStr);
            }
            //挂号类型
            else {
                DoRegMedicareInfoReq doRegMedicareInfoReq = JSON.parseObject(reqMsg, DoRegMedicareInfoReq.class);//入参
                doRegMedicareInfoReq.setPatientNo(getBillInfoListReqDatas.getEmpi());
                doRegMedicareInfoReq.setOrgandoctorId(getBillInfoListReqDatas.getDoctorCode());
                doRegMedicareInfoReq.setDepartmentorganId(getBillInfoListReqDatas.getDeptCode());
                doRegMedicareInfoReq.setTimestypeNo(getBillInfoListReqDatas.getTimestypeNo());
                doRegMedicareInfoReq.setSourceDate(TimeUtils.transDateInt2Str(getBillInfoListReqDatas.getOrderDay()));
                doRegMedicareInfoReq.setMedicareType("11");
                doRegMedicareInfoReq.setMedicareMess(medicareMess);

                String json = new Gson().toJson(doRegMedicareInfoReq);
                //封装：向HIS发送请求
                try {
                    //ws客户端：1.5 取号
                    Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
                    String doregin = JSON.toJSONString(doRegMedicareInfoReq);
                    log.debug("---his doRegMedicareInfo ---:" + doregin);
                    resStr = HisWSClient.invoke(hosWsClient4, HisFunNameConst.DOREG_MEDICARE_INFO, sysUserName, sysKey, json);

                    log.debug(" 2031 获取挂号医保信息 HIS返回：" + resStr);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug(" 2031 获取挂号医保信息 HIS返回有误：" + e.getMessage());
                    log.error("", e);
                }

            }

        } catch (Exception e) {
            log.info(" 2029 HIS返回有误：" + e.getMessage());
            log.error("", e);
            return errMsgReturn("连接有误");
        }


        String outStr;
        //转换数据：门诊类型
        if (clinicType == ClinicType.MZ.getTypeVal()) {
            outStr = transformToClinicStr(resStr, strNo, getBillInfoListReqDatas.getHosId(), medicareMess);
        }
        //转换数据：住院类型
        else if (clinicType == ClinicType.ZY.getTypeVal()) {
            outStr = transformToInHosDetailStr(resStr, strNo, getBillInfoListReqDatas.getHosId());
        }
        //挂号
        else {
            outStr = transformToMedicareStr(resStr);
        }

        return outStr;
    }

    /**
     * 向社保获取医保卡号信息
     */
    public String getMedicareMess(String hosId, String medicareType, String socialNo, String idNumber, String name) throws Exception {
        HospitalSiConfig hospitalSiConfig = hospitalSiConfigMapper.selectTRhipHospitalSiConfig(hosId, medicareType);
        // K13822465||||450900|||452528198009278777|陈翔|NEW|
        //区号
        SiRealUser outRealUser = realUserService.findRealUser(new SiRealUser(name, idNumber));
        if (outRealUser != null) {
            String cardInfo = socialNo + "||||" + outRealUser.getOverallArea() + "|||" + idNumber + "|" + name + "|NEW|";
            log.info("获取得到医保信息cardInfo:" + cardInfo);
            PayInfo payInfo = siService.readCardInfo(hospitalSiConfig, cardInfo);
            log.info("获取得到医保信息:" + payInfo);
            return payInfo.getOutputStr();
        } else {
            return "";
        }
    }


    /**
     * 住院清单详情
     */
    private String transformToInHosDetailStr(String resStr, String strNo, String hosId) {

        //解析：his返回成对象
        InHosDetailRes inHosDetailRes = JSON.parseObject(resStr, InHosDetailRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, inHosDetailRes.getResultCode())) {
            return errMsgReturn(inHosDetailRes.getResultMsg());
        }

        List<GetBillInfoListItemResDatas> billInfoList = new ArrayList<>();
        GetBillInfoListItemResDatas getBillInfoListItemResDatas = new GetBillInfoListItemResDatas();
        billInfoList.add(getBillInfoListItemResDatas);

        List<GetBillInfoListItemDetailResDatas> billInfoDetailList = new ArrayList<>();
        getBillInfoListItemResDatas.setData(billInfoDetailList);

        BigDecimal bigDecimal = new BigDecimal(0);

        List<InHosDetailItemRes> inHosList = inHosDetailRes.getInHosList();
        for (InHosDetailItemRes i : inHosList) {
            GetBillInfoListItemDetailResDatas getBillInfoListItemDetailResDatas = new GetBillInfoListItemDetailResDatas();
            billInfoDetailList.add(getBillInfoListItemDetailResDatas);
            String feeItemAllAmount = i.getFeeItemAllAmount();
            bigDecimal = bigDecimal.add(new BigDecimal(feeItemAllAmount));

            getBillInfoListItemResDatas.setAccDate(Integer.valueOf(StringUtils.replaceAll(i.getChargeDate(), "-", "")));
            getBillInfoListItemDetailResDatas.setAmount(Double.valueOf(i.getFeeItemAmount()));
            getBillInfoListItemDetailResDatas.setName(i.getProjectName());
            getBillInfoListItemDetailResDatas.setPrice(Double.valueOf(i.getFeeItemAmount()));
            getBillInfoListItemDetailResDatas.setUnit(i.getFeeItemUnit());
            getBillInfoListItemDetailResDatas.setTotal(Double.valueOf(feeItemAllAmount));
        }

        getBillInfoListItemResDatas.setBillAmount(bigDecimal);
        GetBillInfoListResDatas getBillInfoListResDatas = new GetBillInfoListResDatas();
        getBillInfoListResDatas.setOperCode(HisConvertConst.Operation.GET_CLINIC_INFO_REQ_CODE);
        getBillInfoListResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getBillInfoListResDatas.setHosId(hosId);
        getBillInfoListResDatas.setData(billInfoList);

        return JSON.toJSONString(getBillInfoListResDatas);
    }


    /**
     * 门诊详情
     * <p>
     * 增加入参orderId用于查询订单获取指引单信息  2019/01/30  lisheng
     */
    private String transformToClinicStr(String resStr, String strNo, String hosId, String medicareMess) {
        //解析：his返回成对象
        QueryToPayRecipeInfoListRes queryToPayRecipeInfoListRes = JSON.parseObject(resStr, QueryToPayRecipeInfoListRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryToPayRecipeInfoListRes.getResultCode())) {
            return errMsgReturn(queryToPayRecipeInfoListRes.getResultMsg());
        }

        List<GetBillInfoListItemResDatas> billInfoList = new ArrayList<>();

        List<QueryToPayRecipeInfoListItem> hiFee = queryToPayRecipeInfoListRes.getHiFee();
        for (QueryToPayRecipeInfoListItem q : hiFee) {
            String hiFeeNo = q.getHiFeeNo();
            if (strNo.equals(hiFeeNo)) {
                GetBillInfoListItemResDatas getBillInfoListItemResDatas = new GetBillInfoListItemResDatas();
                getBillInfoListItemResDatas.setClinicalNo(hiFeeNo);
                getBillInfoListItemResDatas.setDoctorName(q.getDoctorName());
                getBillInfoListItemResDatas.setDeptName(q.getOrganName());
                getBillInfoListItemResDatas.setBillAmount(new BigDecimal(q.getSettleAmount()));
                getBillInfoListItemResDatas.setBillDate(Integer.valueOf(StringUtils.replaceAll(StringUtils.substring(q.getCreateTime(), 0, 10), "-", "")));
                getBillInfoListItemResDatas.setAccDate(Integer.valueOf(StringUtils.replaceAll(StringUtils.substring(q.getVisitDate(), 0, 10), "-", "")));
                getBillInfoListItemResDatas.setMedicareInfo(q.getMedicareInfo());
                getBillInfoListItemResDatas.setMedicareType(q.getMedicareType());
                getBillInfoListItemResDatas.setDiagInfo(q.getDiagInfo());
                List<GetBillInfoListItemDetailResDatas> billInfoDetailList = new ArrayList<>();
                getBillInfoListItemResDatas.setData(billInfoDetailList);
                getBillInfoListItemResDatas.setBillNo(hiFeeNo);
                billInfoList.add(getBillInfoListItemResDatas);

                List<QueryToPayRecipeInfoListHiFeeItem> hiFeeItem = q.getHiFeeItem();
                for (QueryToPayRecipeInfoListHiFeeItem qt : hiFeeItem) {
                    GetBillInfoListItemDetailResDatas getBillInfoListItemDetailResDatas = new GetBillInfoListItemDetailResDatas();
                    getBillInfoListItemDetailResDatas.setAmount(qt.getFeeItemAllAmount());
                    getBillInfoListItemDetailResDatas.setPrice(qt.getFeeItemAmount());
                    getBillInfoListItemDetailResDatas.setTotal(qt.getFeeItemNum());
                    getBillInfoListItemDetailResDatas.setName(qt.getFeeItemName());
                    getBillInfoListItemDetailResDatas.setUnit(qt.getFeeItemUnit());
                    getBillInfoListItemDetailResDatas.setSpec(qt.getFeeItemStandard());
                    getBillInfoListItemDetailResDatas.setFeeCode(qt.getCateId());
                    billInfoDetailList.add(getBillInfoListItemDetailResDatas);
                }
            }
        }

        GetBillInfoListResDatas getBillInfoListResDatas = new GetBillInfoListResDatas();
        getBillInfoListResDatas.setOperCode(HisConvertConst.Operation.GET_CLINIC_INFO_REQ_CODE);
        getBillInfoListResDatas.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getBillInfoListResDatas.setHosId(hosId);
        getBillInfoListResDatas.setData(billInfoList);
        getBillInfoListResDatas.setMedicareMess(medicareMess);

        return JSON.toJSONString(getBillInfoListResDatas);
    }

    /**
     * 挂号费
     *
     * @return
     */
    private String transformToMedicareStr(String resStr) {
        GetBillInfoListResDatas infoRes = new GetBillInfoListResDatas();
        List<GetBillInfoListItemResDatas> list = new ArrayList<>();
        GetBillInfoListItemResDatas getBillInfoListItemResDatas = new GetBillInfoListItemResDatas();

        DoRegMedicareInfoRes doRegMedicareInfoRes = JSON.parseObject(resStr, DoRegMedicareInfoRes.class);//入参
        getBillInfoListItemResDatas.setMedicareInfo(doRegMedicareInfoRes.getMedicareInfo());
        list.add(getBillInfoListItemResDatas);
        infoRes.setMedicareMess(doRegMedicareInfoRes.getMedicareInfo());
        infoRes.setData(list);

        return JSON.toJSONString(infoRes);
    }

}