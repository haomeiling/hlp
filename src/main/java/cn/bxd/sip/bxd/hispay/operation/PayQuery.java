package cn.bxd.sip.bxd.hispay.operation;

import cn.bxd.sip.bxd.dao.SimpleQueryDao;
import cn.bxd.sip.bxd.hispay.common.HeaderUtils;
import cn.bxd.sip.bxd.hispay.common.cxdata.XStreamUtils;
import cn.bxd.sip.bxd.hispay.constant.OperationConst;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.exception.SystemException;
import cn.bxd.sip.bxd.hispay.factory.impl.BaseSynOutOperation;
import cn.bxd.sip.bxd.hispay.protocol.Header;
import cn.bxd.sip.bxd.hispay.protocol.HeaderBody;
import cn.bxd.sip.bxd.hispay.protocol.payquery.FrontPayQueryReqData;
import cn.bxd.sip.bxd.hispay.protocol.payquery.FrontPayQueryResData;
import cn.bxd.sip.bxd.hispay.protocol.payquery.PayQueryResData;
import cn.bxd.sip.bxd.hispay.protocol.payquery.PayQueryTopResData;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.var.ClientConst;
import cn.bxd.sip.bxd.var.ReservationVar;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 16:17
 */

@Slf4j
@Service
public class PayQuery extends BaseSynOutOperation {
    @Autowired
    SimpleQueryDao simpleQueryDao;


    @Override
    protected String toPlatForm(Map<String, Object> inMsgMap) throws BusinessException, Exception {
        //将map转换为javabean
        FrontPayQueryReqData frontPayQueryReqData = getBody(new FrontPayQueryReqData(), inMsgMap);
        Header header = getHeader(inMsgMap);

        //检测订单是否存在
        Order order = checkOrder(frontPayQueryReqData);

        //请求体
        Map<String, Object> microMap = new HashMap<>();
        microMap.put("orderNo", frontPayQueryReqData.getOrderNo());

        //发送请求
        //此处的payType没有值，默认给微信值1，保证能获取登录信息
        String exportStr = exportService.export(header.getHospitalCode(), ClientConst.ReqUrl.PAY_QUERY_TRANS_URL, ReservationVar.PayType.PAY_TYPE_WXPAY, microMap);

        //返回结果
        return formatResult(frontPayQueryReqData, header, exportStr);
    }

    @Override
    protected String getSchameFileName() {
        return null;
    }

    //检测订单
    private Order checkOrder(FrontPayQueryReqData frontPayQueryReqData) throws BusinessException {
        Order order = simpleQueryDao.getOrderByOrderId(Long.valueOf(frontPayQueryReqData.getOrderNo()));
        if (order == null) {
            throw new BusinessException("不存在该笔订单", ResultCodeConstant.RESULTCODE_90003);
        }

        /*if(order.getPayType()==null){
            throw new BusinessException("未知的支付类型",ResultCodeConstant.RESULTCODE_90003);
        }*/
        return order;
    }

    //封装出参
    private String formatResult(FrontPayQueryReqData frontPayQueryReqData, Header header, String exportStr) throws SystemException {
        //此处需要封装map
        HeaderBody headerBody = new HeaderBody();
        FrontPayQueryResData frontPayQueryResData = new FrontPayQueryResData();

        //支付平台返回数据
        PayQueryTopResData payQueryTopResData = new Gson().fromJson(exportStr, PayQueryTopResData.class);
        header = HeaderUtils.buildHeader(header.getHospitalCode(), ResultCodeConstant.RESULTCODE_90000, OperationConst.HOSPITAL_PAY_QUERY_RES, header.getMsgID());
        // 判断支付是否成功
        String returnCode = payQueryTopResData.getReturnCode();
        switch (returnCode) {
            case SUCEESS:// 成功
                frontPayQueryResData.setOrderRefNo(frontPayQueryReqData.getOrderRefNo());
                frontPayQueryResData.setOrderTypeID(frontPayQueryReqData.getOrderTypeID());
                frontPayQueryResData.setOrderNo(String.valueOf(frontPayQueryReqData.getOrderNo()));

                List<PayQueryResData> payQueryResDatas = payQueryTopResData.getTransRecord();
                PayQueryResData payQueryResData;
                if (payQueryResDatas == null || payQueryResDatas.size() == 0) {
                    frontPayQueryResData.setAdditionNotes("无有效交易记录");
                    frontPayQueryResData.setTransState(FAIL);
                } else {
                    payQueryResData = payQueryResDatas.get(0);
                    frontPayQueryResData.setAdditionNotes("查询成功");
                    String transTimeString = payQueryResData.getTransTime();
                    frontPayQueryResData.setPayTime(transTimeString == null ? "0" : transTimeString);
                    frontPayQueryResData.setTransId(String.valueOf(payQueryResData.getTransId()));
                    frontPayQueryResData.setTransactionID(payQueryResData.getTransactionId());
                    frontPayQueryResData.setTransState(payQueryResData.getTransStatus());
                }
                break;
            case FAIL:
                frontPayQueryResData.setOrderRefNo(frontPayQueryReqData.getOrderRefNo());
                frontPayQueryResData.setOrderTypeID(frontPayQueryReqData.getOrderTypeID());
                frontPayQueryResData.setOrderNo(String.valueOf(frontPayQueryReqData.getOrderNo()));
                frontPayQueryResData.setTransState(FAIL);
                frontPayQueryResData.setAdditionNotes("查询出错");
                break;
        }

        // 将结果转化为xml格式
        headerBody.setBODY(frontPayQueryResData);
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
