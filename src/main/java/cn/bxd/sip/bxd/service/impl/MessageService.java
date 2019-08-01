package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.dao.ConnectParmMapper;
import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.model.dto.QueryOrderInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.ExtUser;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.service.IExtUserService;
import cn.bxd.sip.bxd.service.IMessageService;
import cn.bxd.sip.bxd.service.IPayParmService;
import cn.bxd.sip.bxd.util.SendMsgUtils;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.model.dto.his.CancelNotifyObject;
import cn.bxd.sip.his.model.dto.his.MsgBaseData;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 微信消息模板
 *
 * @author HML
 * @Date 2016/3/15
 */
@Service
public class MessageService implements IMessageService {
    private static final Logger logger = Logger.getLogger(MessageService.class.getName());

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ConnectParmMapper connMapper;

    @Autowired
    private IExtUserService extUserService;

    @Autowired
    private IPayParmService parmService;

    /**
     * 挂号取消通知
     *
     * @param orderId 订单编号
     */
    public void sendCancelNotify(Integer hospitalId,Integer userId,Long orderId) {
        QueryOrderInfo order = orderMapper.selectOrderByOrderId(orderId);

        boolean isAppointment = order.getOrderTypeId() == ReservationVar.Order.ORDER_TYPE_APPOINTMENT;
        if (!isAppointment) {
            return;
        }

        // 判断是否是预约，有可能是预约转挂号的单子，这个时候如果被取消，则不发通知
        //添加时间20171027
        //通过队列id来判断。
        String queueId = order.getQueueId();
        if (queueId == null || queueId.equals("")) {
            return;
        }

        //预约医生
        String doctorName = order.getDoctorName();
        if (doctorName == null || doctorName.equals("")) {
            doctorName = "普通门诊";
        } else {
            doctorName = "专家门诊  " + doctorName;
        }
        //预约时间
        int orderDay = order.getOrderDay();
        String orderDayStr = TimeUtils.transDateInt2Str(orderDay);

        CancelNotifyObject cancelNotifyObject = new CancelNotifyObject();
        MsgBaseData msgBaseData = getBaseSendMsgInfo(hospitalId, userId, order.getAppId(), order.getContactPhone());
        msgBaseData.setOperCode(ReservationVar.SmgOperateCode.RESERVATION_CANCEL_NOTIFICATION);
        cancelNotifyObject.setMsgBaseData(msgBaseData);

        cancelNotifyObject.setHospitalName(order.getHospitalName());
        cancelNotifyObject.setOrderPeriod(order.getOrderPeriod());
        cancelNotifyObject.setOrderDayStr(orderDayStr);
        cancelNotifyObject.setDeptName(order.getDeptName());
        cancelNotifyObject.setPatientName(order.getPatientName());
        cancelNotifyObject.setDoctorName(doctorName);

        String json = new Gson().toJson(cancelNotifyObject);
//        String json = new Gson().toJson(msgBaseData);
        logger.info("挂号取消通知==入参===" + json);
        String res = SendMsgUtils.sendMsg(json);
        logger.info("挂号取消通知==出参===" + res);

    }

    /*
     * 获取公共的对象
     *
     * */
    public MsgBaseData getBaseSendMsgInfo(Integer hosId, Integer userId, String appid, String contactPhone) {


        //获取用于发送短信的参数
        ConnectParm connectParm = connMapper.selectByPrimaryKey(hosId);
        boolean isPrivateHospital = connectParm != null && connectParm.getPrivateSmsGateway() != null
                && connectParm.getPrivateSmsGateway() == ReservationVar.Is.TRUE;
        //0 不用医院自身短信，1 使用医院自身短信，传参数给 消息中心
        Integer iPH = isPrivateHospital ? 1 : 0;


        ExtUser extUserWC = extUserService.getExtUser(userId, hosId, ReservationVar.ExtUserType.WECHAT);
        ExtUser extUserAli = extUserService.getExtUser(userId, hosId, ReservationVar.ExtUserType.ALI);

        MsgBaseData msgBaseData = new MsgBaseData();
        //微信账户
        if (extUserWC != null) {
            String openIdWc = extUserWC.getOpenId();
            PayParm payParm = parmService.getWechatConnectPayParm(hosId);
            msgBaseData.setOpenIdWC(openIdWc);//微信外部账户ID
            msgBaseData.setAppid(payParm.getProviderAppid());//微信应用ID
            msgBaseData.setSecret(payParm.getProviderAppsecret());//微信秘钥
        } else {
            msgBaseData.setOpenIdWC("");//微信外部账户ID
            msgBaseData.setAppid("");//微信应用ID
            msgBaseData.setSecret("");//微信秘钥
        }
        //获取微信凭证
        boolean isHm = appid.equals(ReservationVar.AppID.APPID_BPHIV10);
        //用于短消息中心获取模板
        Integer hosId4App = isHm ? ReservationVar.HospitalId.HM : hosId;//医院编码
        msgBaseData.setHosId4App(hosId4App);//平台或者医院的id
        msgBaseData.setIsPrivateHospital(iPH);//是否使用医院短信系统
        msgBaseData.setReceiverUId(userId);//接收用户ID
        msgBaseData.setMsgType(ReservationVar.MsgType.ONE);//一对一发送
        msgBaseData.setHospitalId(hosId);//医院ID
        msgBaseData.setContactPhone(contactPhone);//手机联系方式

        //阿里支付宝账户
        if (extUserAli != null) {

            msgBaseData.setOpenIdAli(extUserAli.getOpenId());
            msgBaseData.setMsgTypeAli(ReservationVar.AliMsgType.ALI_TEXT_MSG);

        } else {
            msgBaseData.setOpenIdAli("");
            msgBaseData.setMsgTypeAli("");
        }

        return msgBaseData;
    }


    public static class Utils {

    }


}
