package cn.bxd.sip.bxd.hispay.factory;

import cn.bxd.sip.bxd.hispay.constant.OperationConst;
import cn.bxd.sip.bxd.hispay.operation.PayCancel;
import cn.bxd.sip.bxd.hispay.operation.PayMicro;
import cn.bxd.sip.bxd.hispay.operation.PayQuery;
import cn.bxd.sip.bxd.hispay.operation.PayScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:45
 */
@Service
public class HisPayOperationFactory {
    @Autowired
    private ApplicationContext ac;

    public IOperation creatOperation(int operCode) {
        IOperation operation;
        switch (operCode) {

            // 医院调用扫码支付请求，将微信、支付宝的二维码返回给医院。（同步） Leeves 2017-12-25
            case OperationConst.HOSPITAL_SCAN_PAY_REQ:
                operation = (PayScan) ac.getBean("payScan");
                break;
            // 医院调用刷卡支付请求。（同步） Leeves 2017-12-25
            case OperationConst.HOSPITAL_MICRO_PAY_REQ:
                operation = (PayMicro) ac.getBean("payMicro");
                break;
            // 医院调用支付结果请求。（同步） Leeves 2018-1-23
            case OperationConst.HOSPITAL_PAY_QUERY_REQ:
                operation = (PayQuery) ac.getBean("payQuery");
                break;
            // 医院调用撤销支付请求。（同步） Leeves 2018-3-9
            case OperationConst.HOSPITAL_REFUND_PAY_REQ:
                operation = (PayCancel) ac.getBean("payCancel");
                break;
            // 扫码支付结果请求。（同步） Leeves 2018-1-23
           /* case OperationConst.HOSPITAL_H5_PAY_REQ:
                operation = (H5PayToPlatform) ac.getBean("h5PayToPlatform");
                break;*/
            default:
                operation = null;
                break;
        }
        return operation;
    }
}
