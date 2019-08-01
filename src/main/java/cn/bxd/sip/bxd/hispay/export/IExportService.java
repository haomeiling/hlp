package cn.bxd.sip.bxd.hispay.export;

import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.exception.SysErrException;

import java.sql.SQLException;
import java.util.Map;

/**
 * Description: 出口service
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 16:00
 */

public interface IExportService {
    /**
     * @param hospitalCode 医院编码
     * @param methodName 方法名称
     * @param map 入参
     * @return
     * @throws BusinessException
     * @throws SysErrException
     * @throws SQLException
     */
    String export(String hospitalCode, String methodName, Short payType,Map<String, Object> map) throws BusinessException, SysErrException, SQLException;
}
