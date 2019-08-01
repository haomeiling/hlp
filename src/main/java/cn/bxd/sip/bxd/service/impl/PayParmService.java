package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.dao.PayParmMapper;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.model.entity.PayParmKey;
import cn.bxd.sip.bxd.service.IPayParmService;
import cn.bxd.sip.bxd.var.AppVar;
import cn.bxd.sip.bxd.var.ReservationVar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author hml
 * @Date 2015/11/5
 */
@Service
public class PayParmService implements IPayParmService {

    @Autowired
    private PayParmMapper payParmMapper;

    @Override
    public PayParm getWechatConnectPayParm(Integer hospitalId) {
        String appId = hospitalId == ReservationVar.HospitalId.HM ?
                ReservationVar.AppID.APPID_BPHIV10 :
                ReservationVar.AppID.APPID_PALMHV10;

        PayParmKey payParmKey = new PayParmKey();
        payParmKey.setHospitalId(hospitalId);
        payParmKey.setProviderId(ReservationVar.Trans.PROVIDER_WEI_XIN);
        payParmKey.setAppId(appId);

        PayParmMapper payParameterDao = AppVar.ac.getBean(PayParmMapper.class);
        PayParm payParm = payParameterDao.selectByPrimaryKey(payParmKey);

        return payParm;
    }

    @Override
    public PayParm getAlipayConnectPayParm(Integer hospitalId) {
        String appId = hospitalId == ReservationVar.HospitalId.HM ?
                ReservationVar.AppID.APPID_BPHIV10 :
                ReservationVar.AppID.APPID_PALMHV10;

        PayParmKey payParmKey = new PayParmKey();
        payParmKey.setHospitalId(hospitalId);
        payParmKey.setProviderId(ReservationVar.Trans.PROVIDER_ALIPAY);
        payParmKey.setAppId(appId);

        PayParm payParm = payParmMapper.selectByPrimaryKey(payParmKey);

        return payParm;
    }
}
