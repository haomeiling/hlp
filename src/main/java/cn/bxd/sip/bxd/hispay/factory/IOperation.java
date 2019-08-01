package cn.bxd.sip.bxd.hispay.factory;

import cn.bxd.sip.bxd.hispay.exception.BusinessException;

/**
 * Description:操作接口
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:46
 */
public interface IOperation {
    /**
     * 外部对内请求
     *
     * @param inMsgStr
     * @return
     * @throws BusinessException
     * @throws Exception
     */
    String operateRequest(String inMsgStr) throws BusinessException, Exception;
}
