package cn.bxd.sip.si.service.impl;

import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecords;
import cn.bxd.sip.bxd.service.IOrderStatusService;
import cn.bxd.sip.bxd.service.ISeqService;
import cn.bxd.sip.bxd.service.ISiMedicareRecordsService;
import cn.bxd.sip.bxd.util.DOMTool;
import cn.bxd.sip.bxd.util.HttpTookit;
import cn.bxd.sip.bxd.var.OrderStatus;
import cn.bxd.sip.bxd.var.SIVar;
import cn.bxd.sip.si.service.SiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.mapper.Mapper.Null;

import java.util.Date;

@Service
@Slf4j
public class SiServiceImpl implements SiService {

    @Value("${si.url}")
    private String url;
    @Autowired
    ISiMedicareRecordsService medicareRecordsService;
    @Autowired
    IOrderStatusService orderStatusService;
    @Autowired
    ISeqService seqService;

    @Override
    public PayInfo readCardInfo(HospitalSiConfig hospitalSiConfig, String cardInfo) {
        log.info("读社保卡信息开始：");
        boolean sign = true;// 流程控制标志
        String appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
        String detailMessage = "";
        PayInfo payInfo = new PayInfo();
        try {
            // 1、判断社保卡信息串是否为空
            if (sign && StringUtils.isBlank(cardInfo)) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "社保卡信息串，不能为空！";
                log.info(detailMessage);
                sign = false;
            }
            // 2、判断社保接口配置是否存在
            if (sign && hospitalSiConfig == null) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "医院接口配置信息不存在！";
                log.info(detailMessage);
                sign = false;
            } else {
                // 查询交易流水号
                String businessNo = getSequence(hospitalSiConfig.getHospitalNo(), "BUSINESS_NO");
                hospitalSiConfig.setBusinessNo(businessNo);
                log.info("医院社保接口配置查询--" + " 医院id：" + hospitalSiConfig.getHospitalId() + " 医疗类型：" + hospitalSiConfig.getMedicareType());
            }
            // 3、赋值对象
            if (sign) {
                payInfo.setHospitalId(hospitalSiConfig.getHospitalId());// 医院ID
                payInfo.setHospitalno(hospitalSiConfig.getHospitalNo());// 医院编号
                payInfo.setInputStr(cardInfo);// 预结算交易入参串
                payInfo.setMedicareType(hospitalSiConfig.getMedicareType());// 医疗类型
                payInfo.setBusinessCode(SIVar.BusinessCode.MEDICAL_OF_HIS_READ_CARD);// 业务编号
                payInfo.setBusinessNo(hospitalSiConfig.getBusinessNo());// 交易流水号
            }
            // 4、2100交易
            if (sign) {
                log.info("开始：读社保卡2210交易：");
                hospitalSiConfig.setInputData("");
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_READ_CARD);// 业务编号
                // 发起社保：2210交易
                fun_2100(payInfo, cardInfo, hospitalSiConfig);
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    // 交易，返回失败
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = payInfo.getDetailMessage();
                    log.info("2100交易：失败" + detailMessage);
                    sign = false;
                }
            }
            payInfo.setAppCode(appCode);
            payInfo.setDetailMessage(detailMessage);
        } catch (Exception e) {
            payInfo.setAppCode(SIVar.BusinessCode.RESULT_CODE_FALI);
            payInfo.setDetailMessage("程序异常：" + detailMessage);
            e.printStackTrace();
        }
        return payInfo;
    }

    @Override
    // @Transactional
    public PayInfo payBefore(HospitalSiConfig hospitalSiConfig, String feeIds, String name, String idNumber, String socialNo, String hospitalInfo, Long orderId, String medicareMess) {
        log.info("医保预结算接口开始：" + " 身份证号：" + idNumber + " 姓名：" + name + " 诊疗编号:" + feeIds);
        boolean sign = true;// 流程控制标志
        String appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
        String detailMessage = "";
        PayInfo payInfo = new PayInfo();
        try {
            // 1、判断社保接口配置是否存在
            if (sign && hospitalSiConfig == null) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "医院接口配置信息不存在！";
                log.info(detailMessage);
                sign = false;
            } else {
                log.info("医院社保接口配置查询--" + " 医院id：" + hospitalSiConfig.getHospitalId() + " 医疗类型：" + hospitalSiConfig.getMedicareType());
            }
            // 2、解析his信息串medicareInfo：分隔符">|<"
            String str2210 = "";// 门诊/住院登记，2210入参串
            String str2310 = "";// 处方明细上报 2310入参串
            String str2420 = "";// 费用预结算 2420入参串
            if (sign && StringUtils.isBlank(hospitalInfo)) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "医院串hospitalInfo，为空！";
                log.info(detailMessage);
                sign = false;
            }
            if (sign && StringUtils.isNoneBlank(hospitalInfo)) {
                String[] hospitalInfoList = hospitalInfo.split(">\\|<");// 解析医院串
                if (hospitalInfoList != null && hospitalInfoList.length == 3) {
                    str2210 = hospitalInfoList[0];
                    str2310 = hospitalInfoList[1];
                    str2420 = hospitalInfoList[2];
                    log.info("医院串2210：" + str2210);
                    log.info("医院串2310：" + str2310);
                    log.info("医院串2420：" + str2420);
                } else {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "医院提供交易串不准确！";
                    log.info(detailMessage);
                    sign = false;
                }
            }

            // 3、通过医院ID hospitalId 、 诊疗编号feeIds 、poNo，查询就诊流水号是否已使用。
            // 每次都向社保发起预结算交易
            payInfo.setHospitalId(hospitalSiConfig.getHospitalId());
            payInfo.setFeeIds(feeIds);//诊疗编号(诊疗记录标志)
            payInfo.setPoNo(String.valueOf(orderId));//订单号


            log.info("预结算信息查询--" + " HospitalId：" + payInfo.getHospitalId() + " feeIds：" + payInfo.getFeeIds() + " poNo：" + payInfo.getPoNo());
            SiMedicareRecords tSiMedicareRecords = medicareRecordsService.selectMedicareRecords(payInfo);
            if (sign && tSiMedicareRecords != null && StringUtils.equals(payInfo.getSiFeeIds(), tSiMedicareRecords.getSiFeeIds())) {
                appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
                detailMessage = "就诊流水号  :" + payInfo.getSiFeeIds() + " 已使用！";
                log.info(detailMessage);
                sign = false;
            } else {
                // 查询交易流水号
                String businessNo = getSequence(hospitalSiConfig.getHospitalNo(), "BUSINESS_NO");
                hospitalSiConfig.setBusinessNo(businessNo);
            }
            // 4、解析登记入参串，并赋值对象
            if (sign && StringUtils.isNoneBlank(str2210)) {
                String[] checkInInfo = str2210.split("\\|");// 门诊/住院登记，入参串
                payInfo.setHospitalId(hospitalSiConfig.getHospitalId());// 医院ID
                payInfo.setHospitalno(hospitalSiConfig.getHospitalNo());// 医院编号
                payInfo.setName(name);// 姓名
                payInfo.setIdNumber(idNumber);// 身份证号
                payInfo.setSocialNo(socialNo);// 社保卡号
                payInfo.setUserNo(checkInInfo[13]);// 个人编号
                payInfo.setArea(checkInInfo.length >= 15 ? checkInInfo[14] : ""); // 统筹区号  二级代码，异地就医的需要传异地的统筹区号,如果是本地的不需要传
                payInfo.setHospitalInfo(hospitalInfo);// 医院串
                payInfo.setInputStr(str2420);// 预结算交易入参串
                payInfo.setMedicareType(hospitalSiConfig.getMedicareType());// 医疗类型
                payInfo.setBusinessCode(SIVar.BusinessCode.MEDICAL_OF_HIS_PAYMENT);// 业务编号
                payInfo.setFeeIds(feeIds);// 诊疗编号(诊疗记录标志)
                payInfo.setBusinessNo(hospitalSiConfig.getBusinessNo());// 交易流水号
                payInfo.setSiFeeIds(checkInInfo[0]);// 社保_就诊流水号
                payInfo.setOperatorCode(checkInInfo[12]);// 经办人（操作员）
            }
            // 5、封装卡信息串："socialNo||||行政区划代码|||idNumber|name|NEW|"
            String cardInfo = "";
            if (sign) {
                cardInfo = socialNo + "||||" + payInfo.getArea() + "|||" + idNumber + "|" + name + "|" + hospitalSiConfig.getCentralCode() + "|";
                payInfo.setCardInfo(cardInfo); // 社保卡信息（如读卡交易入参）
                log.info("卡信息串：" + cardInfo);
            }
            // 6、门诊/住院登记 2210交易
            if (sign) {
                log.info("开始：门诊/住院登记 2210交易：");
                this.InsertOrderStatus(orderId, OrderStatus.PAYING_CHECK, "社保移动支付门诊/住院登记中 2210交易");// 写订单状态日志
                hospitalSiConfig.setInputData(str2210);// 2210交易串
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CHECK_IN);// 业务编号
                // 发起社保：2210交易
                fun_2210(payInfo, cardInfo, hospitalSiConfig);
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    // 登记交易，返回失败
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = payInfo.getDetailMessage();
                    log.info("2210交易：失败" + detailMessage);
                    sign = false;
                }
            }
            // 7、处方明细上报 2310
            if (sign) {
                log.info("开始：处方明细上报 2310交易");
                this.InsertOrderStatus(orderId, OrderStatus.PAYING_DETAIL_UPLOADING, "社保移动支付明细上传中 2310交易");//
                // 写订单状态日志
                hospitalSiConfig.setInputData(str2310);// 2310交易串
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_DETAIL_UPLOAD);// 业务编号
                // 发起社保：2310交易
                fun_2310(payInfo, cardInfo, hospitalSiConfig);
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = payInfo.getDetailMessage();
                    log.info("2310交易：失败" + detailMessage);
                    this.InsertOrderStatus(orderId, OrderStatus.PAYING_CHECK_REVOKING, "社保移动支付门诊/住院登记撤销中,处方明细上报2310交易失败，则发起：登记撤销 2240交易");// 写订单状态日志
                    // 处方明细上报2310交易失败，则发起：登记撤销 2240交易
                    hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CANCEL_IN);
                    fun_2240(payInfo, cardInfo, hospitalSiConfig);
                    sign = false;
                }
            }
            // 8、费用预结算 2420
            if (sign) {
                log.info("开始：费用预结算 2420交易");
                this.InsertOrderStatus(orderId, OrderStatus.PAYING_BUDGET, "社保移动支付预结算中, 2420交易");// 写订单状态日志
                hospitalSiConfig.setInputData(str2420);// 2420交易串
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_PAYBEFORE);// 业务编号
                // 发起社保：2420交易
                fun_2420(payInfo, cardInfo, hospitalSiConfig);
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = payInfo.getDetailMessage();
                    log.info("2420交易：失败" + detailMessage);
                    sign = false;
                    // 费用预结算2420交易失败，则先发起：处方明细撤销2320交易；再发起：登记撤销2240交易
                    // 处方明细撤销 2320
                    this.InsertOrderStatus(orderId, OrderStatus.PAYING_DETAIL_UPLOAD_REVOKING, "社保移动支付明细上传撤销中,费用预结算2420交易失败，则发起：处方明细撤销2320交易");// 写订单状态日志
                    hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CANCEL_UPLOAD);
                    fun_2320(payInfo, cardInfo, hospitalSiConfig);
                    // 登记撤销 2240
                    this.InsertOrderStatus(orderId, OrderStatus.PAYING_CHECK_REVOKING, "社保移动支付门诊/住院登记撤销中, 费用预结算2420交易失败，则发起：登记撤销 2240交易");// 写订单状态日志
                    hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CANCEL_IN);
                    fun_2240(payInfo, cardInfo, hospitalSiConfig);
                }
            }
            // 9、保存数据表：t_si_medicare_records
            // 掌上医院、社保预约平台：保存t_si_medicare_records医保支付记录表；自助机：不保存表。
            if (sign) {
                String[] split = str2420.split("\\|");
                payInfo.setSiFeeIds(split[0]);// 社保_就诊流水号
                payInfo.setSiPoNo(split[1]); // 社保_单据号

                String medicareInfo = medicareMess + "^" + payInfo.getMedicareInfo() + "^" + payInfo.getMedicareType();
                log.info("医保返回数据：" + medicareInfo);
                payInfo.setMedicareInfo(medicareInfo);

                int insertNum = medicareRecordsService.saveMedicareRecords(payInfo);
                // 保存成功：返回预结算信息和recordId社保支付记录ID
                if (insertNum <= 0) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "预结算数据保存，失败！";
                    log.info(detailMessage);
                    this.InsertOrderStatus(orderId, OrderStatus.PAY_BUDGET_FAILS, "社保移动支付预结算失败。预结算数据保存失败！");// 写订单状态日志
                    sign = false;
                    // 保存数据失败，则先发起：处方明细撤销2320交易；再发起：登记撤销2240交易
                    // 处方明细撤销 2320
                    this.InsertOrderStatus(orderId, OrderStatus.PAYING_DETAIL_UPLOAD_REVOKING, "社保移动支付明细上传撤销中,预结算数据保存失败，则发起：处方明细撤销2320交易");// 写订单状态日志
                    hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CANCEL_UPLOAD);
                    fun_2320(payInfo, cardInfo, hospitalSiConfig);
                    // 登记撤销 2240
                    this.InsertOrderStatus(orderId, OrderStatus.PAYING_CHECK_REVOKING, "社保移动支付门诊/住院登记撤销中,预结算数据保存失败，则发起：登记撤销 2240交易");// 写订单状态日志
                    hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CANCEL_IN);
                    fun_2240(payInfo, cardInfo, hospitalSiConfig);
                } else {
                    log.info("保存预结算数据，成功！ recordId：" + payInfo.getRecordId());
                    this.InsertOrderStatus(orderId, OrderStatus.PAY_BUDGET_SUCCESS, "社保移动支付预结算成功");// 写订单状态日志
                }
            }
            if (!sign) {
                this.InsertOrderStatus(orderId, OrderStatus.PAY_BUDGET_FAILS, detailMessage);// 写订单状态日志
            }
            payInfo.setAppCode(appCode);
            payInfo.setDetailMessage(detailMessage);
        } catch (Exception e) {
            this.InsertOrderStatus(orderId, OrderStatus.PAY_BUDGET_FAILS, detailMessage);// 写订单状态日志
            e.printStackTrace();
        }
        return payInfo;
    }

    @Override
    // @Transactional
    public PayInfo payment(HospitalSiConfig hospitalSiConfig, int recordId, String feeIds, String poNo, String name, String idNumber, String socialNo, String inputData, Long orderId) {
        log.info("医保结算接口开始：" + " 身份证号：" + idNumber + " 姓名：" + name + "  结算记录recordId：" + recordId + " 诊疗编号：" + feeIds + " 订单号：" + poNo);
        log.info("结算交易串inputData：" + inputData);
        boolean sign = true;// 流程控制标志
        String appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
        String detailMessage = "";
        PayInfo payInfo = new PayInfo();
        try {
            // 1、判断社保接口配置是否存在
            if (sign && hospitalSiConfig == null) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "医院接口配置信息不存在！";
                log.info(detailMessage);
                sign = false;
            } else {
                log.info("医院社保接口配置查询--" + " 医院id：" + hospitalSiConfig.getHospitalId() + " 医疗类型：" + hospitalSiConfig.getMedicareType());
            }
            // 2、订单号校验
            if (sign && StringUtils.isBlank(poNo)) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "诊疗编号 " + feeIds + " 订单号为空！";
                log.info(detailMessage);
                sign = false;
            }
            // 5、解析结算入参串，并赋值对象
            if (sign && StringUtils.isNoneBlank(inputData)) {
                String[] checkInInfo = inputData.split("\\|");// 结算交易串
                payInfo.setHospitalId(hospitalSiConfig.getHospitalId());// 医院ID
                payInfo.setHospitalno(hospitalSiConfig.getHospitalNo());// 医院编号
                payInfo.setName(name);// 姓名
                payInfo.setIdNumber(idNumber);// 身份证号
                payInfo.setSocialNo(socialNo);// 社保卡号
                //payInfo.setArea(checkInInfo[12]); // 统筹区号
                payInfo.setArea(checkInInfo.length >= 13 ? checkInInfo[12] : ""); // 统筹区号  二级代码，异地就医的需要传异地的统筹区号,如果是本地的不需要传
                payInfo.setMedicareType(hospitalSiConfig.getMedicareType());// 医疗类型
                payInfo.setRecordId(recordId);//
                payInfo.setFeeIds(feeIds);// 诊疗编号(诊疗记录标志)
                payInfo.setBusinessNo(hospitalSiConfig.getBusinessNo());// 交易流水号
                payInfo.setSiFeeIds(checkInInfo[0]);// 社保_就诊流水号
                payInfo.setSiPoNo(checkInInfo[1]);// 社保_单据号
                payInfo.setOperatorCode(checkInInfo[10]);// 经办人（操作员）
                payInfo.setInputStr(inputData);// 结算交易入参串
                payInfo.setPoNo(poNo);// 单据号
                payInfo.setPayDate(checkInInfo[3]); // 结算日期
            }

            // 3、通过医院ID hospitalId 、 诊疗编号feeIds ，查询是否已存在结算记录。
            SiMedicareRecords tSiMedicareRecords = null;
            if (sign) {
                log.info("结算信息查询--" + " HospitalId：" + payInfo.getHospitalId() + " feeIds：" + feeIds + " poNo：" + poNo + " siFeeIds：" + payInfo.getSiFeeIds());
                tSiMedicareRecords = medicareRecordsService.selectMedicareRecords(payInfo);
                if (tSiMedicareRecords == null) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "诊疗编号 " + feeIds + " 预结算信息不存在，无法进行结算！";
                    log.info(detailMessage);
                    sign = false;
                }
            }
            if (sign && tSiMedicareRecords != null && StringUtils.equals("1", tSiMedicareRecords.getPostate().toString())) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "诊疗编号 " + feeIds + " 已结算，不允许重新结算！";
                log.info(detailMessage);
                sign = false;
            }
            if (sign && tSiMedicareRecords != null && StringUtils.equals("0", tSiMedicareRecords.getPostate().toString())) {
                // 查询交易流水号
                String businessNo = getSequence(hospitalSiConfig.getHospitalNo(), "BUSINESS_NO");
                hospitalSiConfig.setBusinessNo(businessNo);
            }
            // 4、校验单据号是否已使用
//			if(sign){
//				log.info( "单据号查询--" + " HospitalId：" + payInfo.getHospitalId( ) + " siPoNo：" + payInfo.getSiPoNo( ) );
//				String selectSiPoNo = medicareRecordsService.selectSiPoNo( payInfo );
//				if(StringUtils.isNoneBlank( selectSiPoNo )){
//					appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
//					detailMessage = "社保_就诊流水号:" + payInfo.getSiFeeIds( ) + " 结算单据号 " + payInfo.getSiPoNo( ) + " 已经使用，无法进行结算！";
//					log.info( detailMessage );
//					sign = false;
//				}
//			}


            // 6、封装卡信息串："socialNo||||行政区划代码|||idNumber|name|NEW|"
            String cardInfo = "";
            if (sign) {
                cardInfo = socialNo + "||||" + payInfo.getArea() + "|||" + idNumber + "|" + name + "|" + hospitalSiConfig.getCentralCode() + "|";
                payInfo.setCardInfo(cardInfo); // 社保卡信息（如读卡交易入参）
                log.info("卡信息串：" + cardInfo);
                if (sign && !StringUtils.equals(cardInfo, payInfo.getCardInfo())) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "诊疗编号 " + feeIds + " 人员卡信息与预结算时不一致！";
                    log.info("预结算时卡信息：" + payInfo.getCardInfo());
                    log.info("结算时卡信息：" + cardInfo);
                    log.info(detailMessage);
                    sign = false;
                }
            }
            // 7、费用结算 2410
            if (sign) {
                log.info("开始：费用结算 2410交易");
                this.InsertOrderStatus(orderId, OrderStatus.PAYING_SETTLEING, "社保移动支付结算中，费用结算 2410交易");// 写订单状态日志
                hospitalSiConfig.setInputData(inputData);// 2410交易串
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_PAYMENT);// 业务编号
                // 发起社保：2410交易
                payInfo.setMedicareInfo(null);
                fun_2410(payInfo, cardInfo, hospitalSiConfig);
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = payInfo.getDetailMessage();
                    log.info("2410交易：失败" + detailMessage);
                    this.InsertOrderStatus(orderId, OrderStatus.PAY_SETTLE_FAILS, "社保移动支付结算失败，2410交易失败");// 写订单状态日志
                    sign = false;
                }
            }
            // 8、更新数据表：t_si_medicare_records
            if (sign) {
                try {
                    payInfo.setPoState("1");// 结算成功
                    int updateNum = medicareRecordsService.updateMedicareRecords(payInfo);
                    // 更新成功：返回结算信息和订单号
                    if (updateNum <= 0) {
                        appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                        detailMessage = "社保结算成功；结算数据更新，失败！";
                        log.info(detailMessage);
                        this.InsertOrderStatus(orderId, OrderStatus.PAY_SETTLE_FAILS, "社保移动支付结算失败，2410交易成功，结算数据更新失败！");// 写订单状态日志
                        sign = false;
                    } else {
                        log.info("结算数据更新，成功！" + " 订单号PoNo：" + payInfo.getPoNo());
                        this.InsertOrderStatus(orderId, OrderStatus.PAY_SETTLE_SUCCESS, "社保移动支付结算成功。");// 写订单状态日志
                    }
                } catch (Exception e) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "社保结算成功；结算数据更新，程序异常！";
                    sign = false;
                    log.info(detailMessage);
                    this.InsertOrderStatus(orderId, OrderStatus.PAY_SETTLE_FAILS, "社保移动支付结算失败，2410交易成功，结算数据更新程序异常！");// 写订单状态日志
                    e.printStackTrace();
                }
            }
            if (!sign) {
                this.InsertOrderStatus(orderId, OrderStatus.PAY_SETTLE_FAILS, detailMessage);// 写订单状态日志
            }
            // 9、返回结算信息：
            //添加时间  2018-12-06 add by haomeiling
            payInfo.setUserNo(tSiMedicareRecords.getPatientno());
            payInfo.setMedicareInfo(tSiMedicareRecords.getMedicareInfo());

            payInfo.setAppCode(appCode);
            payInfo.setDetailMessage(detailMessage);
        } catch (Exception e) {
            this.InsertOrderStatus(orderId, OrderStatus.PAY_SETTLE_FAILS, detailMessage);// 写订单状态日志
            e.printStackTrace();
        }
        return payInfo;
    }

    @Override
    // @Transactional
    public PayInfo cancelPayment(HospitalSiConfig hospitalSiConfig, int recordId, String feeIds, String poNo, String name, String idNumber, String socialNo, Long orderId) {
        log.info("医保结算撤消接口开始：" + " 身份证号：" + idNumber + " 姓名：" + name + "  结算记录recordId：" + recordId + " 诊疗编号：" + feeIds + " 订单号：" + poNo);
        boolean sign = true;// 流程控制标志
        String appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
        String detailMessage = "";
        String inputData = "";
        PayInfo payInfo = new PayInfo();
        try {
            // 1、判断社保接口配置是否存在
            if (sign && hospitalSiConfig == null) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "医院接口配置信息不存在！";
                log.info(detailMessage);
                sign = false;
            } else {
                log.info("医院社保接口配置查询--" + " 医院id：" + hospitalSiConfig.getHospitalId() + " 医疗类型：" + hospitalSiConfig.getMedicareType());
            }
            // 2、通过医院ID hospitalId 、 诊疗编号feeIds ，查询是否存在结算记录。
            payInfo.setHospitalId(hospitalSiConfig.getHospitalId());
            payInfo.setFeeIds(feeIds);
            payInfo.setPoNo(poNo);
            log.info("结算信息查询--" + " HospitalId：" + payInfo.getHospitalId() + " feeIds：" + feeIds + "poNo:" + poNo);
            payInfo = medicareRecordsService.selectMedicareRecordsCancelPayment(payInfo);
            if (payInfo == null) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "诊疗编号 " + feeIds + " 结算信息不存在，无法进行结算撤消！";
                log.info(detailMessage);
                sign = false;
            }
            if (sign && payInfo != null && ("0".equals(payInfo.getPoState()) || "2".equals(payInfo.getPoState()))) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage = "诊疗编号 " + feeIds + " 未结算，不允许结算撤消！";
                log.info(detailMessage);
                sign = false;
            }
            if (sign && payInfo != null && payInfo.getPoState() != null && "1".equals(String.valueOf(payInfo.getPoState()))) {
                // 查询交易流水号
                String businessNo = getSequence(hospitalSiConfig.getHospitalNo(), "BUSINESS_NO");
                hospitalSiConfig.setBusinessNo(businessNo);
                //payInfo.setInputStr(tSiMedicareRecords.get);
                inputData = payInfo.getInputStr();// 预结算串
            }
            // 4、解析结算入参串，并赋值对象
            if (sign) {
                String[] checkInInfo = inputData.split("\\|");// 门诊/住院登记，入参串
                payInfo.setHospitalId(hospitalSiConfig.getHospitalId());// 医院ID
                payInfo.setHospitalno(hospitalSiConfig.getHospitalNo());// 医院编号
                payInfo.setName(name);// 姓名
                payInfo.setIdNumber(idNumber);// 身份证号
                payInfo.setSocialNo(socialNo);// 社保卡号
                payInfo.setArea(checkInInfo[12]); // 统筹区号
                payInfo.setMedicareType(hospitalSiConfig.getMedicareType());// 医疗类型
                payInfo.setRecordId(recordId);//
                payInfo.setFeeIds(feeIds);// 诊疗编号(诊疗记录标志)
                payInfo.setBusinessNo(hospitalSiConfig.getBusinessNo());// 交易流水号
                payInfo.setSiFeeIds(checkInInfo[0]);// 社保_就诊流水号
                payInfo.setSiPoNo(checkInInfo[1]);// 社保_单据号
                payInfo.setOperatorCode(checkInInfo[10]);// 经办人（操作员）
                payInfo.setInputStr(inputData);// 结算交易入参串
                payInfo.setPoNo(poNo);// 单据号
                payInfo.setPayDate(checkInInfo[3]);// 结算日期
            }
            // 3、封装卡信息串："socialNo||||行政区划代码|||idNumber|name|NEW|"
            String cardInfo = "";
            if (sign) {
                cardInfo = socialNo + "||||" + payInfo.getArea() + "|||" + idNumber + "|" + name + "|" + hospitalSiConfig.getCentralCode() + "|";
                payInfo.setCardInfo(cardInfo); // 社保卡信息（如读卡交易入参）
                log.info("卡信息串：" + cardInfo);
                if (sign && !StringUtils.equals(cardInfo, payInfo.getCardInfo())) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "诊疗编号 " + feeIds + " 人员卡信息与结算时不一致！";
                    log.info(detailMessage);
                    sign = false;
                }
            }
            // 5、 费用结算撤销 2430
            if (sign) {
                log.info("开始：费用结算撤消 2430交易");
                this.InsertOrderStatus(orderId, OrderStatus.REFUND_SOCIAL_SECURITY_HANDLING, "社保移动支付退款处理中，费用结算撤消 2430交易");// 写订单状态日志
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_CANCEL_PAYMENT);
                payInfo.setSavePrescriptionsign("1");// 是否保存处方标志（0不保存，1保存）
                fun_2430(payInfo, cardInfo, hospitalSiConfig);
                appCode = payInfo.getAppCode();
                detailMessage = payInfo.getMedicareInfo();
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = payInfo.getDetailMessage();
                    log.info("2430交易：失败" + detailMessage);
                    this.InsertOrderStatus(orderId, OrderStatus.REFUND_SOCIAL_SECURITY_ERROR, "社保移动支付退费失败，2430交易失败！");// 写订单状态日志
                    sign = false;
                }
            }
            // 6、更新数据表：t_si_medicare_records
            if (sign) {
                payInfo.setPoState("2");// 撤消结算
                int updateNum = medicareRecordsService.updateMedicareRecords(payInfo);
                // 更新成功：返回结算信息和订单号
                if (updateNum <= 0) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage = "结算数据更新，失败！";
                    this.InsertOrderStatus(orderId, OrderStatus.REFUND_SOCIAL_SECURITY_ERROR, "社保移动支付退费失败，2430交易成功，数据更新失败！");// 写订单状态日志
                    log.info(detailMessage);
                    sign = false;
                } else {
                    this.InsertOrderStatus(orderId, OrderStatus.REFUND_SOCIAL_SECURITY_SUCCESS, "社保移动支付退款成功");// 写订单状态日志
                    log.info("结算数据更新，成功！" + " 订单号PoNo：" + payInfo.getPoNo());
                }
            }
            if (!sign) {
                this.InsertOrderStatus(orderId, OrderStatus.REFUND_SOCIAL_SECURITY_ERROR, detailMessage);// 写订单状态日志
            }
            // 7、返回结算信息：
            payInfo.setAppCode(appCode);
            payInfo.setDetailMessage(detailMessage);
        } catch (Exception e) {
            this.InsertOrderStatus(orderId, OrderStatus.REFUND_SOCIAL_SECURITY_ERROR, detailMessage);// 写订单状态日志
            e.printStackTrace();
        }
        return payInfo;
    }

    @Override
    // @Transactional
    public PayInfo selectPayment(HospitalSiConfig hospitalSiConfig, String feeIds, String poNo) {
        log.info("结算信息查询(发票补打)接口开始：" + " 诊疗编号：" + feeIds + " 订单号：" + poNo);
        boolean sign = true;// 流程控制标志
        String appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
        String detailMessage = " 诊疗编号：" + feeIds + "  ";
        PayInfo payInfo = new PayInfo();
        try {
            // 1、判断社保接口配置是否存在
            if (sign && hospitalSiConfig == null) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage += "医院接口配置信息不存在！";
                log.info(detailMessage);
                sign = false;
            } else {
                log.info("医院社保接口配置查询--" + " 医院id：" + hospitalSiConfig.getHospitalId() + " 医疗类型：" + hospitalSiConfig.getMedicareType());
            }
            // 2、订单号校验
            if (sign && StringUtils.isBlank(poNo)) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage += " 订单号为空！";
                log.info(detailMessage);
                sign = false;
            }
            // 3、通过医院ID hospitalId 、 诊疗编号feeIds ，查询是否已存在结算记录。
            SiMedicareRecords tSiMedicareRecords = null;
            if (sign) {
                payInfo.setHospitalId(hospitalSiConfig.getHospitalId());
                payInfo.setFeeIds(feeIds);
                payInfo.setPoNo(poNo);
                log.info("结算信息查询--" + " HospitalId：" + payInfo.getHospitalId() + " feeIds：" + feeIds + " poNo：" + poNo);
                tSiMedicareRecords = medicareRecordsService.selectMedicareRecords(payInfo);
                if (tSiMedicareRecords == null) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage += " 结算信息不存在！";
                    log.info(detailMessage);
                    sign = false;
                }
            }
            if (sign && StringUtils.isNoneBlank(tSiMedicareRecords.getPono()) && !StringUtils.equals(poNo, tSiMedicareRecords.getPono())) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage += " 订单号：" + poNo + "不准确！";
                log.info(detailMessage);
                sign = false;
            }
            if (sign && payInfo != null && "1".equals(payInfo.getPoState())) {
                appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
                detailMessage += " 医院已结算！";
                log.info(detailMessage);
            }
            if (sign && payInfo != null && ("0".equals(payInfo.getPoState()) || "2".equals(payInfo.getPoState()))) {
                appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                detailMessage += " 医院未结算！";
                log.info(detailMessage);
            }
            // 4、查询交易流水号
            if (sign) {
                String businessNo = getSequence(hospitalSiConfig.getHospitalNo(), "BUSINESS_NO");
                hospitalSiConfig.setBusinessNo(businessNo);
            }
            // 5、赋值对象
            // 6、结算信息查询(发票补打)(1101)
            if (sign) {
                log.info("开始：结算信息查询 1101交易");
                hospitalSiConfig.setMsgType(SIVar.BusinessCode.MEDICAL_OF_HIS_SELECTPAYMENT);// 业务编号
                // 发起社保：1101交易
                payInfo.setMedicareInfo(null);
                fun_1101(payInfo, payInfo.getCardInfo(), hospitalSiConfig);
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_FALI)) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_FALI;
                    detailMessage += " 社保：" + payInfo.getDetailMessage();
                    log.info("1101交易：失败" + detailMessage);
                }
                if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_SUCCESS)) {
                    appCode = SIVar.BusinessCode.RESULT_CODE_SUCCESS;
                    detailMessage += " 社保已结算！";
                    log.info(detailMessage);
                }
            }
            // 7、返回结算信息：
            payInfo.setAppCode(appCode);
            payInfo.setDetailMessage(detailMessage);
        } catch (Exception e) {
            payInfo.setAppCode(SIVar.BusinessCode.RESULT_CODE_FALI);
            payInfo.setDetailMessage("结算信息查询，程序异常！" + detailMessage);
            log.info("结算信息查询，程序异常！" + detailMessage);
            e.printStackTrace();
        }
        return payInfo;
    }

    private void fun_si(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        String dynamic = hospitalSiConfig.getDynamic();// 网卡地址
        String inputData = getHospitalSiConfig(hospitalSiConfig);// 交易串
        String outputXml = sendSi(dynamic, cardInfo, inputData);// 发送医保交易
        resolveOutputXmlHeader(payInfo, outputXml);// 解析医保返回XML头部信息
    }

    // 读卡交易 2100
    private void fun_2100(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        String dynamic = hospitalSiConfig.getDynamic();// 网卡地址
        String inputData = getHospitalSiConfig(hospitalSiConfig);// 交易串
        String outputXml = sendSi(dynamic, cardInfo, inputData);// 发送医保交易
        resolveOutputXmlHeader(payInfo, outputXml);// 解析医保返回XML头部信息
        if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_SUCCESS)) {
            try {
                Parser parser = new Parser(outputXml);
                NodeFilter filter = new HasAttributeFilter("paraName", "outputData");
                NodeList nodes = parser.extractAllNodesThatMatch(filter);
                if (nodes != null) {
                    Node node = (Node) nodes.elementAt(0);
                    String outputStr = ((TagNode) node).getAttribute("paraValue");
                    String[] outList = StringUtils.splitPreserveAllTokens(outputStr, "^");
                    payInfo.setOutputStr(outList[2]);// 读卡返回信息
                }
            } catch (Exception e) {
                payInfo.setAppCode(SIVar.BusinessCode.RESULT_CODE_FALI);
                payInfo.setDetailMessage("程序处理异常！");
                e.printStackTrace();
            }
        }
    }

    // 门诊/住院登记 2210
    private void fun_2210(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        fun_si(payInfo, cardInfo, hospitalSiConfig);
    }

    // 登记撤销 2240
    private void fun_2240(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        hospitalSiConfig.setInputData(payInfo.getSiFeeIds() + "|" + payInfo.getOperatorCode() + "|" + payInfo.getArea() + "|");
        fun_si(payInfo, cardInfo, hospitalSiConfig);
    }

    // 处方明细上报 2310
    private void fun_2310(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        fun_si(payInfo, cardInfo, hospitalSiConfig);
    }

    // 处方明细撤销 2320
    private void fun_2320(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        hospitalSiConfig.setInputData(payInfo.getSiFeeIds() + "|" + payInfo.getBusinessNo() + "|" + payInfo.getOperatorCode() + "|" + payInfo.getArea() + "|");
        fun_si(payInfo, cardInfo, hospitalSiConfig);
    }

    // 费用预结算 2420
    private void fun_2420(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        String dynamic = hospitalSiConfig.getDynamic();// 网卡地址
        String inputData = getHospitalSiConfig(hospitalSiConfig);// 交易串
        String outputXml = sendSi(dynamic, cardInfo, inputData);// 发送医保交易
        resolveOutputXmlHeader(payInfo, outputXml);// 解析医保返回XML头部信息
        if (StringUtils.equals(payInfo.getAppCode(), SIVar.BusinessCode.RESULT_CODE_SUCCESS)) {
            try {
                Parser parser = new Parser(outputXml);
                NodeFilter filter = new HasAttributeFilter("paraName", "outputData");
                NodeList nodes = parser.extractAllNodesThatMatch(filter);
                if (nodes != null) {
                    Node node = (Node) nodes.elementAt(0);
                    String outputData = ((TagNode) node).getAttribute("paraValue");
                    String[] outList = StringUtils.splitPreserveAllTokens(outputData, "^");
                    String[] outMessage = StringUtils.splitPreserveAllTokens(outList[2], "|");
                    payInfo.setMedicareInfo(outList[2]);// 医保返回数据
                    payInfo.setTotalMoney(outMessage[0]);// 医疗费总额1
                    payInfo.setPayMoney(outMessage[2]);// 本次帐户支付3
                    payInfo.setOverMoney(outMessage[3]);// 统筹支出4
                    payInfo.setCashMoney(outMessage[6]);// 现金支付金额7
                    payInfo.setPoState("0");// 交易状态（0未成功 ,1成功）

                    if (outMessage[3] != null && Double.parseDouble(outMessage[3]) > 0.0) {
                        payInfo.setIsOverall("1");// 是否进行统筹（0否，1是）
                    } else {
                        payInfo.setIsOverall("0");// 是否进行统筹（0否，1是）
                    }
                }
            } catch (Exception e) {
                payInfo.setAppCode(SIVar.BusinessCode.RESULT_CODE_FALI);
                payInfo.setDetailMessage("程序处理异常！");
                e.printStackTrace();
            }
        }
    }

    // 费用结算 2410
    private void fun_2410(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        fun_2420(payInfo, cardInfo, hospitalSiConfig);
    }

    // 费用结算撤销 2430
    private void fun_2430(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        hospitalSiConfig.setInputData(payInfo.getSiFeeIds() + "|" + payInfo.getSiPoNo() + "|" + payInfo.getPayDate() + "|" + payInfo.getOperatorCode() + "|" + payInfo.getSavePrescriptionsign()
                + "|" + payInfo.getArea() + "|");
        fun_si(payInfo, cardInfo, hospitalSiConfig);
    }

    // 结算信息查询(发票补打)(1101)
    private void fun_1101(PayInfo payInfo, String cardInfo, HospitalSiConfig hospitalSiConfig) {
        hospitalSiConfig.setInputData(payInfo.getSiFeeIds() + "|" + payInfo.getSiPoNo() + "|");
        fun_2420(payInfo, cardInfo, hospitalSiConfig);
    }

    /**
     * 发送医保接口
     *
     * @param dynamic   网卡地址
     * @param cardInfo  卡信息串
     * @param inputData 入参交易串 （业务编号^医疗机构编号^操作员编号^业务周期号^医院交易流水号^中心编码^入参^联机标志^）
     * @return 医保返回信息（XML格式）
     */
    private String sendSi(String dynamic, String cardInfo, String inputData) {
        String dllParam = dynamic + "||";
        String dllparm = "|||||||||||";
        log.info("医保接口url：" + url);
        log.info("医保接口inputData：" + inputData);
        // log.info("医保接口dllParam：" + dllParam);
        log.info("医保接口cardInfo：" + cardInfo);
        String outputData = HttpTookit.httpPost(url, inputData, dllParam, dllparm, cardInfo);
        log.info("医保返回outputData：" + outputData);
        return outputData;
    }

    /**
     * 解析医保返回XML头部信息
     *
     * @param payInfo   医保结算信息
     * @param outputXml 医保返回信息（XML格式）
     */
    private void resolveOutputXmlHeader(PayInfo payInfo, String outputXml) {
        try {
            if (StringUtils.isNotBlank(outputXml)) {
                Document dom = DOMTool.loadDocumentFromStr(outputXml);
                Element header1 = DOMTool.getHeaderOrBody(dom, "header");
                String appCode = DOMTool.getElement(header1, "appCode").getText();
                String detailMessage = DOMTool.getElement(header1, "errorMessage").attributeValue("detailMessage");
                payInfo.setAppCode(appCode);
                payInfo.setDetailMessage(detailMessage);
            } else {
                payInfo.setAppCode(SIVar.BusinessCode.RESULT_CODE_FALI);
                payInfo.setDetailMessage(" 社保返回报文为空！");
            }
        } catch (Exception e) {
            payInfo.setAppCode(SIVar.BusinessCode.RESULT_CODE_FALI);
            payInfo.setDetailMessage(" 社保返回报文，解析异常！");
        }
    }

    /**
     * 查询交易流水号
     *
     * @param hospitalNo      医院编号
     * @param as_sequenceName 序列名
     * @return 医院编号 + yyyyMMddHHmmss + 序列
     */
    private String getSequence(String hospitalNo, String as_sequenceName) {
		/*HashMap<String,Object> param = new HashMap<String,Object>( );
		param.put( "as_sequenceName", as_sequenceName );
		param.put( "as_Creator", "" );
		medicareRecordsService.getBusinessNoSequence( param );
		Object ai_Sequence = param.get( "ai_Sequence" );*/
        long ai_Sequence = seqService.getBusinessNo();
        String businessNo = hospitalNo + "-" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + "-" + ai_Sequence;
        return businessNo;
    }

    /**
     * 获取医保接口交易串
     *
     * @param hospitalSiConfig
     * @return 入参格式: 业务编号^医疗机构编号^操作员编号^业务周期号^医院交易流水号^中心编码^入参^联机标志^
     */
    private String getHospitalSiConfig(HospitalSiConfig hospitalSiConfig) {
        // 查询交易流水号
        String businessNo = getSequence(hospitalSiConfig.getHospitalNo(), "BUSINESS_NO");
        hospitalSiConfig.setBusinessNo(businessNo);
        String sign = "^";
        StringBuffer strBuf = new StringBuffer("");
        strBuf.append(hospitalSiConfig.getMsgType());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getHospitalNo());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getOperatorNo());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getCycleNo());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getBusinessNo());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getCentralCode());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getInputData());
        strBuf.append(sign);
        strBuf.append(hospitalSiConfig.getOnlineSign());
        strBuf.append(sign);
        return strBuf.toString();
    }

    public void InsertOrderStatus(Long orderId, OrderStatus orderStatus, String mark) {
        if (orderId != null && orderId > 0) {
            orderStatusService.insertOrderStatus(orderId, orderStatus, mark);
        }
    }

}
