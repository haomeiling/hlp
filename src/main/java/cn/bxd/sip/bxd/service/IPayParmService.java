package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.entity.PayParm;

/**
 * @author 郝美玲
 * @Date 2015/11/5
 * 获取平台各配置信息服务接口
 */
public interface IPayParmService {
    /**
     * 获取微信互联配置
     *
     * @param hospitalId
     * @return
     */
    PayParm getWechatConnectPayParm(Integer hospitalId);

    /**
     * 获取阿里云互联配置
     *
     * @param hospitalId
     * @return
     */
    PayParm getAlipayConnectPayParm(Integer hospitalId);

}
