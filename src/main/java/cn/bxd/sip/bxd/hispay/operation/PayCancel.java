package cn.bxd.sip.bxd.hispay.operation;

import cn.bxd.sip.bxd.hispay.common.HeaderUtils;
import cn.bxd.sip.bxd.hispay.common.cxdata.XStreamUtils;
import cn.bxd.sip.bxd.hispay.constant.OperationConst;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.exception.SystemException;
import cn.bxd.sip.bxd.hispay.factory.impl.BaseSynOutOperation;
import cn.bxd.sip.bxd.hispay.protocol.Header;
import cn.bxd.sip.bxd.hispay.protocol.HeaderBody;
import cn.bxd.sip.bxd.hispay.protocol.paycancel.FrontPayCancelReqData;
import cn.bxd.sip.bxd.hispay.protocol.paycancel.FrontPayCancelResData;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.pay.CancelOrderService;
import cn.bxd.sip.bxd.service.impl.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Description:订单撤销
 * User: HaoMeiLing
 * Date: 2019-04-28
 * Time: 10:44
 */
@Slf4j
@Service
public class PayCancel extends BaseSynOutOperation {
    private static final String OK = "OK";//撤销成功
    @Autowired
    OrderService orderService;
    @Resource
    private CancelOrderService cancelOrderService;

    @Override
    protected String toPlatForm(Map<String, Object> inMsgMap) throws BusinessException, Exception {
        FrontPayCancelReqData frontPayCancelReqData = getBody(new FrontPayCancelReqData(), inMsgMap);
        Header header = getHeader(inMsgMap);

        //检测订单是否存在
        Order order = checkOrder(frontPayCancelReqData);
        //此处的order.payType如果没有值，默认给微信值1，保证能获取登录信息    Lisheng  2019/7/11
        if (order.getPayType() == null || "".equals(order.getPayType())) {
            order.setPayType((short) 1);
        }

        //发送订单取消请求
        String result = cancelOrderService.cancel(order, frontPayCancelReqData.getAdditionNotes(), header.getHospitalCode(), false);

        return formatResult(frontPayCancelReqData, header, result);
    }

    @Override
    protected String getSchameFileName() {
        return null;
    }

    //检测订单
    private Order checkOrder(FrontPayCancelReqData frontPayCancelReqData) throws BusinessException {
        Order order = orderService.getOrder(Long.valueOf(frontPayCancelReqData.getOrderNo()));
        if (order == null) {
            throw new BusinessException("不存在该笔订单");
        }

        if (order.getAmount().compareTo(new BigDecimal(frontPayCancelReqData.getAmount())) != 0) {
            throw new BusinessException("订单信息不匹配,金额不正确");
        }

        return order;
    }

    private String formatResult(FrontPayCancelReqData frontPayCancelReqData, Header header, String exportStr) throws SystemException {
        // 设置需要的出参格式
        FrontPayCancelResData frontPayCancelResData = new FrontPayCancelResData();
        frontPayCancelResData.setOrderNo(frontPayCancelReqData.getOrderNo());
        if (exportStr.equals(OK)) {
            frontPayCancelResData.setRefundState(SUCEESS);
            frontPayCancelResData.setAdditionNotes("处理成功");
        } else {
            frontPayCancelResData.setRefundState(FAIL);
            frontPayCancelResData.setAdditionNotes(exportStr);
        }

        // 封装出参头部
        header = HeaderUtils.buildHeader(header.getHospitalCode(), ResultCodeConstant.RESULTCODE_90000, OperationConst.HOSPITAL_REFUND_PAY_RES, header.getMsgID());

        // 将结果转化为xml格式
        HeaderBody headerBody = new HeaderBody();
        headerBody.setBODY(frontPayCancelResData);
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
