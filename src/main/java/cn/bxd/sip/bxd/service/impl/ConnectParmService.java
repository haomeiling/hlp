package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.ConnectParmMapper;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.service.IConnectParm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.bxd.service.impl.oracle
 *
 * @author Leeves
 * @version 1.0.0  2018-08-22
 */

@Service
public class ConnectParmService implements IConnectParm {

    @Autowired
    private ConnectParmMapper tRhipConnectParmMapper;

    @Override
    public List<ConnectParm> getAllHosConnectParamList() {
        return tRhipConnectParmMapper.selectAllConnectParm();
    }

    @Override
    public ConnectParm getHosConnectParamByHosId(int hosId) {
        return tRhipConnectParmMapper.selectByPrimaryKey(hosId);
    }

}