package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.common.cxdata.XStreamUtils;
import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.model.dto.FamilyInfo;
import cn.bxd.sip.bxd.model.dto.FamilyInfoReqData;
import cn.bxd.sip.bxd.model.dto.FamilyInfoResData;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.RealUser;
import cn.bxd.sip.bxd.model.entity.SiRealUser;
import cn.bxd.sip.bxd.model.policy.*;
import cn.bxd.sip.bxd.service.IFamilyMembersService;
import cn.bxd.sip.bxd.service.IRealUserService;
import cn.bxd.sip.bxd.service.ISysService;
import cn.bxd.sip.bxd.service.ISyncPolicyService;
import cn.bxd.sip.bxd.util.IpUtil;
import cn.bxd.sip.bxd.var.SIVar;
import cn.bxd.sip.bxd.webservice.Header;
import cn.bxd.sip.bxd.webservice.HeaderBody;
import cn.bxd.sip.bxd.webservice.ISiWebService;
import cn.bxd.sip.bxd.webservice.client.WSClient;
import cn.bxd.sip.si.service.SiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

// name暴露的服务名称, targetNamespace:命名空间,设置为接口的包名倒写(默认是本类包名倒写).
// endpointInterface接口地址
@Service
@Slf4j
@WebService(name = "Si", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
public class SiWebService implements ISiWebService {

    // 消息
    private static final String HEADER = "<HEADER>";
    private static final String HEADER_ = "</HEADER>";
    // BODY
    private static final String BODY = "<BODY>";
    private static final String BODY_ = "</BODY>";
    @Resource
    ISysService sysService;
    @Resource
    IFamilyMembersService familyMembersService;
    @Resource
    IRealUserService realUserService;
    @Resource
    SiService siService;
    @Resource
    HospitalSiConfigMapper tRhipHospitalSiConfigMapper;
    @Resource
    private WebServiceContext wsContext;
	@Resource
    ISyncPolicyService syncPolicyService;
    @Value("${limit.control}")
    private String control;

    @Override
    public String RecvMsg(String inMsgStr) {
        //log.info("\n 接收到入参===\n" + inMsgStr);
        // 输出参数
        String outMsgStr = "";
        // 获取header字符串
        String headerStr = getHeader(inMsgStr);
        // 将header状态解析为javabean,获取操作码
        Header header = XStreamUtils.parseFromXml(Header.class, headerStr);
        // 消息类型
        String msgType = header.getMsgType();
        // 定义输出对象，在业务处理过程中，对输出对象进行封装
        HeaderBody headerBody = new HeaderBody();
        Class itemClass = null;// 针对列表转化的时候用到
        String bodyStr = getBody(inMsgStr);
        String clientIP = getClientInfo();
        //log.info("\n客户端访问IP：" + clientIP);
        //log.info("control：" + control);
        /** 访问权限过虑 **/
        if ("true".equals(control)) {
            boolean sign = sysService.checkPermission(clientIP, header);
            if (!sign && !StringUtils.equals(msgType.substring(0, 3), "110")) {
                headerBody.setHEADER(header);
                outMsgStr = XStreamUtils.object2XML(itemClass, headerBody);
                // 保存交易日志
                sysService.saveBusinessLog(clientIP, header, inMsgStr, outMsgStr);
                return outMsgStr;
            }
        }
        //log.info("msgType：" + msgType);
        switch (msgType) {
            /** 【查询业务】 110 **/
            /* 参保人信息查询(110101) */
            case SIVar.MsgType.OPERATION_OF_GET_USER_INFO:// 110101;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 参保人险种查询(110102) */
            case SIVar.MsgType.OPERATION_OF_GET_INSURANCE_TYPE:// 110102;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 社保缴费查询(职工)(110103) */
            case SIVar.MsgType.OPERATION_OF_GET_PAYMENT_LIST:// 110103;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 医保账户费用查询(110104) */
            case SIVar.MsgType.OPERATION_OF_GET_ACCOUNT_PAYMENT:// 110104;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 就诊费用查询(110105) */
            case SIVar.MsgType.OPERATION_OF_GET_MEDICAL_LIST:// 110105;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 就诊费用明细查询(110106) */
            case SIVar.MsgType.OPERATION_OF_GET_MEDICAL_ITEM_LIST:// 110106;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 查询医保卡信息(110107) */
            case SIVar.MsgType.OPERATION_OF_GET_CARD_INFO:// 110107;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 账户注入(110108) */
            case SIVar.MsgType.OPERATION_OF_GET_INCOME:// 110108;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 居民医保缴费查询(110109) */
            case SIVar.MsgType.OPERATION_OF_GET_PAYMENT_4_RESIDENT:// 110109;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 社保政策查询(110110) */
            case SIVar.MsgType.OPERATION_OF_GET_POLICY_LIST:// 110110;
                //outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
				PolicyListReqData policyListReqData = XStreamUtils.parseFromXml(PolicyListReqData.class, bodyStr);
				List<Policy> policyList = syncPolicyService.findPolicyList(policyListReqData);
				PolicyListResData policyListResData = new PolicyListResData();
				policyListResData.setListItems(policyList);
				// 封装body
				headerBody.setBODY(policyListResData);
				itemClass = Policy.class;
				// 封装header
				header.setResultCode(SIVar.ResultCode.RESULT_CODE_90000);
				header.setResultMsg("success");
				headerBody.setHEADER(header);
                outMsgStr = XStreamUtils.object2XML(itemClass, headerBody);
				break;
            /* 政策详细查询 (110111) */
            case SIVar.MsgType.OPERATION_OF_GET_POLICY_DETAIL:// 110111;
                //outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
            	PolicyDetailReqData policyDetailReqData = XStreamUtils.parseFromXml(PolicyDetailReqData.class, bodyStr);
				PolicyDetailResData policyDetailResData = syncPolicyService.findPolicyDetail(policyDetailReqData);
				// 封装body
				headerBody.setBODY(policyDetailResData);
				// 封装header
				header.setResultCode(SIVar.ResultCode.RESULT_CODE_90000);
				header.setResultMsg("success");
				headerBody.setHEADER(header);
				outMsgStr = XStreamUtils.object2XML(itemClass, headerBody);
                break;
            /* 授权账户信息查询 (110112) */
            case SIVar.MsgType.OPERATION_OF_GET_USER_LIST:// 110112;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 个人编码查询(110113) */
            case SIVar.MsgType.OPERATION_OF_GET_USER_CODE:// 110113;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 二级代码查询（110114） */
            case SIVar.MsgType.OPERATION_OF_GET_LEVEL_CODE:// 110114;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 读卡信息接口（人脸识别）(110115) */
            case SIVar.MsgType.OPERATION_OF_GET_CARD_FACE:// 110115;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /** 【医疗业务】 310 **/
            /* 医保个账授权添加（310101） */
            case SIVar.MsgType.MEDICAL_OF_ACCOUNT_ACCREDIT_ADD:// 310101;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                Header headerOut = XStreamUtils.parseFromXml(Header.class, this.getHeader(outMsgStr));
                String resultCode = headerOut.getResultCode();
                if (StringUtils.equals(resultCode, SIVar.ResultCode.RESULT_CODE_90000)) {
                    // 社保添加成功，则保存家庭成员表t_si_family_members
                    // FamilyMembers familyMembers =
                    // XStreamUtils.parseFromXml(FamilyMembers.class, bodyStr);
                    // familyMembers.setUser_id( "20185029203856898479" );//测试
                    // familyMembersService.saveFamilyMembers(familyMembers);
                }
                break;
            /* 医保个账授权取消（310102） */
            case SIVar.MsgType.MEDICAL_OF_ACCOUNT_ACCREDIT_CANCEL:// 310102;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                Header headerOut2 = XStreamUtils.parseFromXml(Header.class, this.getHeader(outMsgStr));
                String resultCode2 = headerOut2.getResultCode();
                if (StringUtils.equals(resultCode2, SIVar.ResultCode.RESULT_CODE_90000)) {
                    // 社保取消成功，则更新家庭成员表t_si_family_members
                    // FamilyMembers familyMembers =
                    // XStreamUtils.parseFromXml(FamilyMembers.class, bodyStr);
                    // String user_id =
                    // familyMembersService.findUserId(familyMembers.getAuthorizerIDNumber());
                    // familyMembers.setUser_id(user_id);
                    // familyMembersService.deleteFamilyMembers(familyMembers);
                }
                break;
            /* 医保个账授权查询（310103） */
            case SIVar.MsgType.MEDICAL_OF_GET_ACCOUNT_ACCREDIT_LIST:// 310103;
                FamilyInfoReqData familyInfoReqData = XStreamUtils.parseFromXml(FamilyInfoReqData.class, bodyStr);
                String user_id = familyMembersService.findUserId(familyInfoReqData.getIDNumber());
                familyInfoReqData.setUser_id(user_id);
                List<FamilyInfo> FamilyInfoList = familyMembersService.findFamilyInfo(familyInfoReqData);
                FamilyInfoResData familyInfoResData = new FamilyInfoResData();
                familyInfoResData.setListItems(FamilyInfoList);
                headerBody.setBODY(familyInfoResData);
                itemClass = FamilyInfo.class;
                // 封装header
                header.setResultCode(SIVar.ResultCode.RESULT_CODE_90000);
                header.setResultMsg("success");
                headerBody.setHEADER(header);
                headerBody.setDSIGN("No_Signature");
                outMsgStr = XStreamUtils.object2XML(itemClass, headerBody);
                break;
            /* his接口服务，统一交易入口（310999） */
            case SIVar.MsgType.SYNTHETICAL_OF_HIS_SERVICE:// 310999;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 医保测试入口（310000） */
            case SIVar.MsgType.SYNTHETICAL_OF_HIS_TEST:// 310000;
                //outMsgStr = WSClient.getInstance().sendWS( inMsgStr );
                String feeIds = "166"; // 诊疗编号
                int recordId = 0;
                Long orderId = (long) 1000166;
                String poNo = "100016";// 支付订单号
                String name = "陈翔";
                String idNumber = "452528198009278777";
                String hospitalId = "7962";
                String medicareType = "11";
                String socialNo = "K13822465";
                String cardInfo = "K13822465||||450900|||452528198009278777|陈翔|NEW|";
                String str1 = "M300166|11|20180724131617|J06.903|上呼吸道感染||综合内科|3|601088|陈翔|0|0|测试员|4509231358171|450923|";
                String str2 = "M300166|1|1|CF11022041|20180702084920|HSZYP1080|10129284|氯化钠注射液|5|2||100ml:0.9g×1||bid|尤茂海||续静滴(门诊)|瓶|28|1|0|测试员||450923|0|";
                String str3 = "M300166|200016|11|20180724131617|20180724131617|1|460.05|感冒|1||测试员|10.0|450923";
                String medicareInfo = str1 + ">|<" + str2 + ">|<" + str3;
                System.out.println(medicareInfo);

                HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hospitalId, medicareType);
                PayInfo payInfo = null;
                payInfo = siService.readCardInfo(hospitalSiConfig, cardInfo);
                System.out.println("成功标志：" + payInfo.getAppCode());
                System.out.println("提示信息:" + payInfo.getDetailMessage());
                System.out.println("交易出参串：" + payInfo.getOutputStr());


//				payInfo = siService.payBefore( hospitalSiConfig, feeIds, name, idNumber, socialNo, medicareInfo, orderId );
//				System.out.println( payInfo.getDetailMessage( ) );
//				recordId = payInfo.getRecordId( );
//				payInfo = siService.payment( hospitalSiConfig, recordId, feeIds, poNo, name, idNumber, socialNo, str3, orderId );
//				System.out.println( payInfo.getDetailMessage( ) );
//				payInfo = siService.cancelPayment( hospitalSiConfig, recordId, feeIds, poNo, name, idNumber, socialNo, orderId );
//				System.out.println( payInfo.getDetailMessage( ) );
//				payInfo = siService.selectPayment( hospitalSiConfig, feeIds, poNo );
//				System.out.println( payInfo.getDetailMessage( ) );


                break;

            /** 【缴费业务】 610 **/
            /* 查询可缴费信息(610101) */
            case SIVar.MsgType.PAYMENT_OF_GET_PAYMENT_INFO:// 610101;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 查询可缴费明细 (610102) */
            case SIVar.MsgType.PAYMENT_OF_GET_PAYMENT_DETAIL:// 610102;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 缴纳保险费(610103) */
            case SIVar.MsgType.PAYMENT_OF_PAYMENT:// 610103;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 缴费抹账(610104) */
            case SIVar.MsgType.PAYMENT_OF_PAYMENT_CANCEL:// 610104;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 到账通知（610105） */
            case SIVar.MsgType.PAYMENT_OF_PAY_NOTICE:// 610105;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 缴费对账（610106） */
            case SIVar.MsgType.PAYMENT_OF_PAYMENT_CHECK:// 610106;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 查询缴费订单（610107） */
            case SIVar.MsgType.PAYMENT_OF_GET_PAYMENT_ORDER:// 610107;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 查询未对账信息（610108） */
            case SIVar.MsgType.PAYMENT_OF_GET_PAYMENT_NOCHECK_LIST:// 610108;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 查询已对账信息（610109） */
            case SIVar.MsgType.PAYMENT_OF_GET_PAYMENT_CHECK_LIST:// 610109;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /** 【综合业务】 710 **/
            /* 社保卡临时挂失（冻结）（710101） */
            case SIVar.MsgType.SYNTHETICAL_OF_CARD_REPORT_LOSS:// 710101;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 制卡进度查询（710102） */
            case SIVar.MsgType.SYNTHETICAL_OF_CARD_SCHEDULE:// 710102;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 电子社保卡查询（710103） */
            case SIVar.MsgType.SYNTHETICAL_OF_CARD_ELECTRONICS:// 710103;
                outMsgStr = WSClient.getInstance().sendWS(inMsgStr);
                break;
            /* 查询认证信息（710104） */
            case SIVar.MsgType.SYNTHETICAL_OF_REAL:// 710104;
                RealUser realUser = XStreamUtils.parseFromXml(RealUser.class, bodyStr);
                SiRealUser siRealUser = realUserService.findRealUser(new SiRealUser(realUser.getName(), realUser.getIDNumber()));
                if(siRealUser != null) {
                	realUser = new RealUser(siRealUser.getUserNo(), siRealUser.getPatientName(), siRealUser.getCardId(), 
                			siRealUser.getVisitcardNum(), siRealUser.getMobileNo(), siRealUser.getRealTime(), 
                			siRealUser.getCardinfo(), siRealUser.getOverallArea(), siRealUser.getUserId());
                	headerBody.setBODY(realUser);
                }else {
                	headerBody.setBODY(null);
                }
                headerBody.setBODY(realUser);
                // 封装header
                header.setResultCode(SIVar.ResultCode.RESULT_CODE_90000);
                header.setResultMsg("success");
                headerBody.setHEADER(header);
                headerBody.setDSIGN("No_Signature");
                outMsgStr = XStreamUtils.object2XML(itemClass, headerBody);
                break;
            default:
                header.setResultCode(SIVar.ResultCode.RESULT_CODE_90004);
                header.setResultMsg("INVALID_MSG_TYPE");
                headerBody.setHEADER(header);
                outMsgStr = XStreamUtils.object2XML(itemClass, headerBody);
                break;
        }
        // 统一设置签名
        headerBody.setDSIGN("No_Signature");
        //log.info("\n输出参数===\n" + outMsgStr);
        // 保存交易日志
        sysService.saveBusinessLog(clientIP, header, inMsgStr, outMsgStr);
        return outMsgStr;
    }

    /**
     * 获取header内容串
     *
     * @param inMsgStr 消息字符串
     * @return HEADER内容
     */
    public String getHeader(String inMsgStr) {
        // 通过字符串处理的方式，先找到操作码
        int headerIndex = inMsgStr.indexOf(HEADER);
        int header_Index = inMsgStr.indexOf(HEADER_);
        // 这两个字符串之间的数据就是操作码,<MsgType>z这个的长度是9
        String header = inMsgStr.substring(headerIndex, header_Index + 9);
        return header;
    }

    /**
     * 获取body内容串
     *
     * @param inMsgStr 消息字符串
     * @return BODY内容，包括<BODY>和<BODY/>两个节点 body的节点长度是7
     */
    public String getBody(String inMsgStr) {
        // 通过字符串的方式，获取到body内容串
        int bodyIndex = inMsgStr.indexOf(BODY);
        int body_Index = inMsgStr.indexOf(BODY_);
        String body = inMsgStr.substring(bodyIndex, body_Index + 7);
        return body;
    }

    /**
     * 获取客户端IP
     *
     * @return
     */
    private String getClientInfo() {
        String clientIP = null;
        try {
            MessageContext mc = wsContext.getMessageContext();
            HttpServletRequest request = (HttpServletRequest) (mc.get(MessageContext.SERVLET_REQUEST));
            clientIP = IpUtil.getIpAddr(request);
        } catch (Exception e) {
            log.error("",e);
        }
        return clientIP;
    }
}
