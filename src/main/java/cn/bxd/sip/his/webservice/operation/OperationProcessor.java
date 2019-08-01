package cn.bxd.sip.his.webservice.operation;


import cn.bxd.sip.bxd.model.entity.ConnectParm;

/**
 * Description: 取数据接口
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
public interface OperationProcessor {

    String getDataFromHis(String reqMsg, ConnectParm tRhipConnectParm) throws Exception;

}