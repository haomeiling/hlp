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
import cn.bxd.sip.bxd.hispay.protocol.paymicro.FrontPayMicroReqData;
import cn.bxd.sip.bxd.hispay.protocol.paymicro.FrontPayMicroResData;
import cn.bxd.sip.bxd.hispay.protocol.paymicro.PayMicroResData;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.var.ClientConst;
import cn.bxd.sip.bxd.var.PayConst;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.TerminalVar;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:刷卡支付缴费
 * User: HaoMeiLing
 * Date: 2019-04-19
 * Time: 16:26
 */
@Slf4j
@Service
public class PayMicro extends BaseSynOutOperation {
    @Resource
    OrderMapper orderMapper;
    @Resource
    SeqService seqService;

    @Override
    protected String toPlatForm(Map<String, Object> inMsgMap) throws BusinessException, Exception {
        //将map转换为javabean
        FrontPayMicroReqData frontPayMicroReqData = getBody(new FrontPayMicroReqData(), inMsgMap);
        Header header = getHeader(inMsgMap);

        //创建订单
        Order order = saveOrder(frontPayMicroReqData, header);

        //封装入参
        Map<String, Object> microMap = new HashMap<>();
        microMap.put("providerCode", "");
        microMap.put("sourceCode", "");

        microMap.put("amount", frontPayMicroReqData.getAmount());
        microMap.put("requestNo", order.getOrderId());
        microMap.put("body", frontPayMicroReqData.getBody());
        microMap.put("microAuthCode", frontPayMicroReqData.getAuthCode());

        //发送请求
        String exportStr = exportService.export(header.getHospitalCode(), ClientConst.ReqUrl.PAY_MICRO_URL, order.getPayType(), microMap);

        return formatResult(frontPayMicroReqData, header, exportStr);
    }


    @Override
    protected String getSchameFileName() {
        return null;
    }


    //创建订单
    private Order saveOrder(FrontPayMicroReqData frontPayMicroReqData, Header header) throws Exception {
        Order order = new Order();
        Long orderNo = seqService.getOrderId();
        order.setOrderId(orderNo);
        order.setHospitalId(Integer.valueOf(header.getHospitalCode()));
        order.setPeerOrderNo(frontPayMicroReqData.getOrderRefNo());
        order.setCashMoney(String.valueOf(frontPayMicroReqData.getAmount()));
        order.setAmount(new BigDecimal(frontPayMicroReqData.getAmount()));
        order.setAppId(ReservationVar.AppID.APPID_PALMHV10);
        order.setOrderTypeId(Short.valueOf(frontPayMicroReqData.getOrderTypeID()));

        //设置支付类型
        order.setPayType(getPayChannel(frontPayMicroReqData.getAuthCode()));

        //设置终端号
        order.setTerminalCode(TerminalVar.TerminalCode.PALM_TERMINAL_CODE_HIS_MICRO);

        //设置支付类型
        order.setPayPattern(Short.valueOf(ReservationVar.PayPattern.SELF_FEE + ""));

        //增加姓名和患者编码保存  lisheng 2019/7/25
        String additionNotes=frontPayMicroReqData.getAdditionNotes();
        if ( additionNotes!= null && !"".equals(additionNotes)){
            String[] additionNotesArray = additionNotes.split("|");
            if(additionNotesArray.length>=2){
                order.setPatientName(additionNotesArray[0]==null ? "" : additionNotesArray[0]);
                order.setPatientNo(additionNotesArray[1]==null ? "" : additionNotesArray[1]);
            }
        }

        if (orderMapper.insertSelective(order) <= 0) {
            throw new SystemException("数据新增异常");
        }

        return order;
    }


    /**
     * 通过支付授权码判断是微信或者是支付宝
     *
     * @param authCode 支付授权码
     * @return WX/ALI/NULL
     */
    private Short getPayChannel(String authCode) throws BusinessException {

        //微信授权码
        if (authCode.matches("^1[1-5][0-9]{16}")) {
            return PayConst.Trans.PROVIDER_WEI_XIN;
        }
        //支付宝授权码
        else if (authCode.matches("^((2[5-9])|^(30))[0-9]{14,22}")) {
            return PayConst.Trans.PROVIDER_ALIPAY;
        } else {
            throw new BusinessException("非法授权码");
        }
    }


    private String formatResult(FrontPayMicroReqData frontPayMicroReqData, Header header, String exportStr) throws SystemException {
        HeaderBody headerBody = new HeaderBody();
        FrontPayMicroResData frontPayMicroResData = new FrontPayMicroResData();


        // 封装入参请求支付服务
        PayMicroResData payMicroResData = new Gson().fromJson(exportStr, PayMicroResData.class);
        header = HeaderUtils.buildHeader(header.getHospitalCode(), ResultCodeConstant.RESULTCODE_90000, OperationConst.HOSPITAL_MICRO_PAY_RES, header.getMsgID());
        // 判断支付是否成功
        String returnCode = payMicroResData.getReturnCode();
        String resultCode = payMicroResData.getResultCode();
        switch (returnCode) {
            case SUCEESS:// 成功
                switch (resultCode) {// 成功
                    case SUCEESS:
                        frontPayMicroResData.setOrderNo(payMicroResData.getRequestNo());
                        frontPayMicroResData.setTransId(String.valueOf(payMicroResData.getTransId()));
                        frontPayMicroResData.setTransactionID(payMicroResData.getTransactionId());
                        frontPayMicroResData.setOrderRefNo(frontPayMicroReqData.getOrderRefNo());
                        frontPayMicroResData.setTransState(payMicroResData.getResultCode());
                        frontPayMicroResData.setAdditionNotes(payMicroResData.getResultMsg());
                        frontPayMicroResData.setRequestDate(Integer.parseInt(frontPayMicroReqData.getRequestDate()));
                        break;

                    case FAIL:// 失败，将头部信息设置为失败
                        header.setResultCode(ResultCodeConstant.RESULTCODE_90101);
                        frontPayMicroResData.setOrderRefNo(frontPayMicroReqData.getOrderRefNo());
                        frontPayMicroResData.setOrderNo(payMicroResData.getRequestNo());
                        frontPayMicroResData.setTransId(String.valueOf(payMicroResData.getTransId()));
                        frontPayMicroResData.setTransState(FAIL);
                        frontPayMicroResData.setAdditionNotes(payMicroResData.getErrCode() + payMicroResData.getResultMsg());
                        frontPayMicroResData.setRequestDate(Integer.parseInt(frontPayMicroReqData.getRequestDate()));
                        break;
                    /**
                     * { "returnCode":"SUCCESS", "resultCode":"WATTRING",
                     * "resultMsg":"需要用户输入支付密码", "errCode":"USERPAYING"}
                     */
                    case WAITING://
                        header.setResultCode(ResultCodeConstant.RESULTCODE_90000);
                        frontPayMicroResData.setOrderNo(payMicroResData.getRequestNo());
                        frontPayMicroResData.setTransId(String.valueOf(payMicroResData.getTransId()));
                        frontPayMicroResData.setTransactionID(payMicroResData.getTransactionId());
                        frontPayMicroResData.setOrderRefNo(frontPayMicroReqData.getOrderRefNo());
                        frontPayMicroResData.setTransState(WAITING);
                        frontPayMicroResData.setAdditionNotes(payMicroResData.getResultMsg());
                        frontPayMicroResData.setRequestDate(Integer.parseInt(frontPayMicroReqData.getRequestDate()));
                        break;
                }

                break;

            case FAIL:
                header.setResultCode(ResultCodeConstant.RESULTCODE_90101);
                frontPayMicroResData.setOrderNo(payMicroResData.getRequestNo());
                frontPayMicroResData.setOrderRefNo(frontPayMicroReqData.getOrderRefNo());
                frontPayMicroResData.setTransId(String.valueOf(payMicroResData.getTransId()));
                frontPayMicroResData.setTransState(FAIL);
                String resultMsg = payMicroResData.getResultMsg();
                frontPayMicroResData.setAdditionNotes(payMicroResData.getReturnMsg() + (resultMsg == null ? "" : resultMsg));
                frontPayMicroResData.setRequestDate(Integer.parseInt(frontPayMicroReqData.getRequestDate()));
                frontPayMicroResData.setOrderNo(payMicroResData.getRequestNo());
                break;
        }


        // 将结果转化为xml格式
        headerBody.setBODY(frontPayMicroResData);
        headerBody.setHEADER(header);
        headerBody.setDSIGN("sign");
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
}
