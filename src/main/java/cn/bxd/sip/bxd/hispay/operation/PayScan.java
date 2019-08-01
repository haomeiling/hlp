package cn.bxd.sip.bxd.hispay.operation;

import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.hispay.common.HeaderUtils;
import cn.bxd.sip.bxd.hispay.common.cxdata.XStreamUtils;
import cn.bxd.sip.bxd.hispay.constant.OperationConst;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.exception.SystemException;
import cn.bxd.sip.bxd.hispay.factory.impl.BaseSynOutOperation;
import cn.bxd.sip.bxd.hispay.protocol.Header;
import cn.bxd.sip.bxd.hispay.protocol.HeaderBody;
import cn.bxd.sip.bxd.hispay.protocol.payscan.FrontPayScanReqData;
import cn.bxd.sip.bxd.hispay.protocol.payscan.FrontPayScanResData;
import cn.bxd.sip.bxd.hispay.protocol.payscan.PayProvider;
import cn.bxd.sip.bxd.hispay.protocol.payscan.PayScanResData;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.var.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:扫码支付
 * User: HaoMeiLing
 * Date: 2019-04-28
 * Time: 13:42
 */
@Slf4j
@Service
public class PayScan extends BaseSynOutOperation {
    @Resource
    OrderMapper orderMapper;
    @Resource
    SeqService seqService;

    @Override
    protected String toPlatForm(Map<String, Object> inMsgMap) throws BusinessException, Exception {
        //将map转换为对象
        FrontPayScanReqData frontPayScanReqData = getBody(new FrontPayScanReqData(), inMsgMap);
        Header header = getHeader(inMsgMap);

        //保存订单
        Order order = saveOrder(frontPayScanReqData, header);

        //封装入参
        Map<String, Object> scanMap = new HashMap<>();
        scanMap.put("providerCode", "");
        scanMap.put("sourceCode", "");

        scanMap.put("amount", frontPayScanReqData.getAmount());
        scanMap.put("requestNo", order.getOrderId());
        scanMap.put("body", frontPayScanReqData.getOrderRefNo() + "扫码支付");
        scanMap.put("location", SIVar.WS.payCallBackUrl);


        List<PayProvider> providerList = new ArrayList<>();
        // 封装入参请求支付服务
        header = HeaderUtils.buildHeader(header.getHospitalCode(), ResultCodeConstant.RESULTCODE_90000, OperationConst.HOSPITAL_MICRO_PAY_RES, header.getMsgID());
        //获取微信短码
        String wxExportStr = exportService.export(header.getHospitalCode(), ClientConst.ReqUrl.PAYSCAN_URL, PayConst.Trans.PROVIDER_WEI_XIN, scanMap);
        PayProvider wxpayProvider = setPayProvider(PayConst.Trans.PROVIDER_WEI_XIN, wxExportStr);
        if (wxpayProvider != null) {
            providerList.add(wxpayProvider);
        }

        //获取支付宝短码
        String alipayExportStr = exportService.export(header.getHospitalCode(), ClientConst.ReqUrl.PAYSCAN_URL, PayConst.Trans.PROVIDER_ALIPAY, scanMap);
        PayProvider alipayProvider = setPayProvider(PayConst.Trans.PROVIDER_ALIPAY, alipayExportStr);
        if (alipayProvider != null) {
            providerList.add(alipayProvider);
        }

        return formatResult(frontPayScanReqData, header, order, providerList);
    }

    private String formatResult(FrontPayScanReqData frontPayScanReqData, Header header, Order order, List<PayProvider> providerList) throws SystemException {
        HeaderBody headerBody = new HeaderBody();
        FrontPayScanResData frontPayScanResData = new FrontPayScanResData();

        frontPayScanResData.setOrderNo(String.valueOf(order.getOrderId()));
        frontPayScanResData.setOrderRefNo(frontPayScanReqData.getOrderRefNo());
        frontPayScanResData.setRequestDate(frontPayScanReqData.getRequestDate());
        frontPayScanResData.setProviderCount(String.valueOf(providerList.size()));
        frontPayScanResData.setProviderList(providerList);

        // 设置出参格式
        headerBody.setBODY(frontPayScanResData);
        headerBody.setHEADER(header);

        // 返回结果
        String return2FrontString;
        try {
            return2FrontString = XStreamUtils.object2XML(headerBody);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw new SystemException("对象转换xml文件异常");
        }

        return return2FrontString;
    }


    private PayProvider setPayProvider(short providerId, String exportStr) throws SystemException {
        // 封装入参请求支付服务
        PayScanResData payScanResData = new Gson().fromJson(exportStr, PayScanResData.class);
        // 判断支付是否成功
        String returnCode = payScanResData.getReturnCode();
        String resultCode = payScanResData.getResultCode();
        switch (returnCode) {
            case SUCEESS:// 成功
                switch (resultCode) {// 成功
                    case SUCEESS:
                        PayProvider payProvider = new PayProvider();
                        payProvider.setPaymentStr("<![CDATA[" + payScanResData.getCodeURL() + "]]>");
                        setPayProviderParameter(providerId, payProvider);
                        return payProvider;
                    case FAIL:// 失败，将头部信息设置为失败
                        return null;
                }
                break;
            case FAIL:
                return null;
        }

        return null;
    }

    private void setPayProviderParameter(short providerId, PayProvider payProvider) {
        switch (providerId) {
            case PayConst.Trans.PROVIDER_WEI_XIN:
                //payProvider.setProviderID("WECHATPAY");
                payProvider.setProviderID("1");
                payProvider.setProviderName("微信支付");
                break;

            case PayConst.Trans.PROVIDER_ALIPAY:
                //payProvider.setProviderID("ALIPAY");
                payProvider.setProviderID("2");
                payProvider.setProviderName("支付宝支付");
                break;
        }
    }

    //创建订单
    private Order saveOrder(FrontPayScanReqData frontPayScanReqData, Header header) throws Exception {
        Order order = new Order();
        Long orderNo = seqService.getOrderId();
        order.setOrderId(orderNo);
        order.setHospitalId(Integer.valueOf(header.getHospitalCode()));
        order.setPeerOrderNo(frontPayScanReqData.getOrderRefNo());
        order.setCashMoney(String.valueOf(frontPayScanReqData.getAmount()));
        order.setAmount(new BigDecimal(frontPayScanReqData.getAmount()));
        order.setAppId(ReservationVar.AppID.APPID_PALMHV10);
        order.setOrderTypeId(Short.valueOf(frontPayScanReqData.getOrderTypeID()));
        //设置终端号
        order.setTerminalCode(TerminalVar.TerminalCode.PALM_TERMINAL_CODE_A);

        //增加姓名和患者编码保存  lisheng 2019/7/25
        String additionNotes=frontPayScanReqData.getAdditionNotes();
        if ( additionNotes!= null && !"".equals(additionNotes)){
            String[] additionNotesArray = additionNotes.split("|");
            if(additionNotesArray.length>=2){
                order.setPatientName(additionNotesArray[0]==null ? "" : additionNotesArray[0]);
                order.setPatientNo(additionNotesArray[1]==null ? "" : additionNotesArray[1]);
            }
        }

        //设置支付类型
        order.setPayPattern(Short.valueOf(ReservationVar.PayPattern.SELF_FEE + ""));

        if (orderMapper.insertSelective(order) <= 0) {
            throw new SystemException("数据新增异常");
        }
        return order;
    }

    @Override
    protected String getSchameFileName() {
        return null;
    }

    public static void main(String[] args) {
        List<PayProvider> providerList = new ArrayList<>();
        PayProvider payProvider = new PayProvider();
        payProvider.setProviderID("1");
        payProvider.setProviderName("微信支付");
        payProvider.setPaymentStr("<![CDATA[weixin://wxpay/bizpayurl?pr=99UmogI]]]]>><![CDATA[");
        providerList.add(payProvider);
        PayProvider payProvider2 = new PayProvider();
        payProvider2.setProviderID("2");
        payProvider2.setProviderName("支付宝支付");
        payProvider2.setPaymentStr("<![CDATA[weixin://wxpay/bizpayurl?pr=99UmogI]]]]>><![CDATA[");
        providerList.add(payProvider2);

        //封装入参
        Map<String, Object> scanMap = new HashMap<>();
        scanMap.put("providerCode", "");
        scanMap.put("sourceCode", "");

        scanMap.put("amount", 0.01);
        scanMap.put("requestNo", 1111);
        scanMap.put("body", "扫码支付");
        scanMap.put("location", SIVar.WS.payCallBackUrl);

        HeaderBody headerBody = new HeaderBody();
        FrontPayScanResData frontPayScanResData = new FrontPayScanResData();

        frontPayScanResData.setOrderNo("1111");
        frontPayScanResData.setOrderRefNo("1111");
        frontPayScanResData.setRequestDate("1111");
        frontPayScanResData.setProviderCount(String.valueOf(providerList.size()));
        frontPayScanResData.setProviderList(providerList);

        // 设置出参格式
        headerBody.setBODY(frontPayScanResData);
        headerBody.setHEADER(new Header());

        // 返回结果
        String return2FrontString = null;
        try {
            return2FrontString = XStreamUtils.object2XML(headerBody);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            try {
                throw new SystemException("对象转换xml文件异常");
            } catch (SystemException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println(return2FrontString);
    }
}
