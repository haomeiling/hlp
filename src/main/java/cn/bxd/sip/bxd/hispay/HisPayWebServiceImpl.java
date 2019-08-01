package cn.bxd.sip.bxd.hispay;

import cn.bxd.sip.bxd.hispay.common.MsgUtils;
import cn.bxd.sip.bxd.hispay.common.SystemResponseMsg;
import cn.bxd.sip.bxd.hispay.constant.OperationConst;
import cn.bxd.sip.bxd.hispay.constant.ResultCodeConstant;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.exception.SysErrException;
import cn.bxd.sip.bxd.hispay.exception.SystemException;
import cn.bxd.sip.bxd.hispay.factory.HisPayOperationFactory;
import cn.bxd.sip.bxd.hispay.factory.IOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:线下支付实现类，兼容旧系统，包括类命名，也是以旧的方式来命名
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 14:54
 */
@Slf4j
@Service
public class HisPayWebServiceImpl implements HisPayWebService {
    @Autowired
    private HisPayOperationFactory hisPayFactory;

    @Override
    public String recvMsg(String inMsgStr) {
        // 调试模式下记录入参信息
        log.info("\nhis调用Recv input Msg:\n" + inMsgStr);

        int msgType;// 默认为不存在的消息类型
        String outMsgStr;

        try {
            //先检测医院编码，不存在的情况下，直接跑出异常
            MsgUtils.getHospitalCode(inMsgStr);

            // 从消息文本中获取MsgType,以便根据消息类型进行不同的消息处理接口
            msgType = MsgUtils.getMsgType(inMsgStr);
            // 根据消息类型选择不同的业务处理逻辑
            IOperation operation = hisPayFactory.creatOperation(msgType);
            if (operation != null) {
                // 调用具体的消息处理函数入口
                outMsgStr = operation.operateRequest(inMsgStr);
            } else {
                throw new SysErrException("无效的操作代码", ResultCodeConstant.RESULTCODE_90001);
            }

        } catch (SystemException e) {// 该错误，系统消息未得到任何处理
            if (log.isErrorEnabled()) {
                log.error("\nXML等相关验证失败：" + e.toString());
            }
            // 构造消息语法格式错误的应答消息
            outMsgStr = SystemResponseMsg.msgSimpleRes(OperationConst.OPR_CODE_OF_COMMON_SYS_RES, e.getMessage(),
                    e.getResultCode());
        } catch (SysErrException e) {
            if (log.isErrorEnabled()) {
                log.error("\n消息类型、数字签名等验证失败：" + e.toString());
            }
            // 构造系统错误的应答消息
            outMsgStr = SystemResponseMsg.msgSimpleRes(OperationConst.OPR_CODE_OF_SYS_ERROR_RES, e.getMessage(),
                    e.getResultCode());
        } catch (BusinessException e) {
            if (log.isErrorEnabled()) {
                log.error("\n业务处理失败：" + e.toString());
            }
            // 构造业务处理失败的应答消息
            outMsgStr = SystemResponseMsg.msgSimpleRes(OperationConst.OPR_CODE_OF_COMMON_BUSIN_RES, e.getMessage(),
                    e.getResultCode());
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
                log.error("\n异常入参：" + inMsgStr);
            }
            // 构造系统异常的应答消息
            outMsgStr = SystemResponseMsg.msgSimpleRes(OperationConst.OPR_CODE_OF_SYS_ERROR_RES, "系统异常，请联系技术人员解决",
                    ResultCodeConstant.RESULTCODE_90007);
        }

        log.info("\nhis调用Recv output Msg:\n" + outMsgStr);
        return outMsgStr;
    }
}
