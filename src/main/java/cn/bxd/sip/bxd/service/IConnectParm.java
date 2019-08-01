package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.entity.ConnectParm;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.bxd.service
 *
 * @author Leeves
 * @version 1.0.0  2018-08-22
 */
public interface IConnectParm {

    /**
     * 查询所有的医院配置信息
     */
    List<ConnectParm> getAllHosConnectParamList();


    ConnectParm getHosConnectParamByHosId(int hosId);

}
