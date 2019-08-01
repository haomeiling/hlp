package cn.bxd.sip.bxd.pay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.bxd.sip.bxd.dao.OrderMapper;
import cn.bxd.sip.bxd.dao.TransMapper;
import cn.bxd.sip.bxd.model.dto.QueryTransResData;
import cn.bxd.sip.bxd.model.dto.TransData;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.PayParm;
import cn.bxd.sip.bxd.model.entity.Trans;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.util.Signature;
import cn.bxd.sip.bxd.util.TimeUtils;
import cn.bxd.sip.bxd.util.WXPayUtils;
import cn.bxd.sip.bxd.var.ClientConst;
import cn.bxd.sip.bxd.var.PayConst;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.SIVar;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BillDownService {
    private static final String USER_TYPE = "inner";//标识是否是内部系统调用 调整时间 2019-04-09 haomeiling ，内部调用的时候，直接做数据库级别的同步

    @Autowired
    private PayProxyService payProxyService;

    @Autowired
    private TransMapper transDao;

    @Autowired
    private OrderMapper orderMapper;

    public String down(Integer hospitalId, String startTime, String endTime) {
        try {
            QueryTransResData resData = queryTransData(USER_TYPE, hospitalId, startTime, endTime);
            if (PayConst.PayCode.FAIL.equals(resData.getReturnCode())) {
                return JsonTools.obj2Json(new BaseRespond(ReservationVar.Code.FAIL, "下载失败，" + resData.getReturnMsg()));
            }
            log.info("数据长度:" + resData.getTransRecord().size());
            return saveTrans(hospitalId, resData.getTransRecord());
        } catch (Exception e) {
            log.error("", e);
        }
        return JsonTools.obj2Json(new BaseRespond(ReservationVar.Code.FAIL, "下载失败"));
    }

    public QueryTransResData queryTrans(Integer hospitalId, String startTime, String endTime) {
        QueryTransResData res = queryTransData("", hospitalId, startTime, endTime);
        List<Long> ids = new ArrayList<>();
        for (TransData temp : res.getTransRecord()) {
            ids.add(Long.valueOf(temp.getOrderNo()));
        }
        List<TransData> tempList = new ArrayList<>();
        if (ids.size() > 0) {
            List<Order> orders = orderMapper.queryOrdersByIds(ids);
            for (TransData temp : res.getTransRecord()) {
                for (Order order : orders) {
                    if (order.getOrderId().equals(Long.valueOf(temp.getOrderNo()))) {
                        temp.setHisOrderNo(order.getPeerOrderNo() == null ? "" : order.getPeerOrderNo());
                        temp.setOrderType(order.getOrderTypeId());
                        if (Arrays.asList(ReservationVar.AppID.APPID_YZDIV10, ReservationVar.AppID.APPID_ZDIV10).contains(order.getAppId())) {
                            temp.setTransScene(ReservationVar.AppID.APPID_ZDIV10.equals(order.getAppId()) ? 5 : 6);
                        }
                    }
                }
                temp.setHospitalCode(hospitalId + "");
                tempList.add(temp);
            }
        }
        res.setTransRecord(tempList);
        return res;
    }


    public QueryTransResData queryTransData(String sysType, Integer hospitalId, String startTime, String endTime) {
        //获取授权因子
        try {
            String output = queryTransData(sysType, hospitalId, startTime, endTime, false);
            if (StringUtils.isBlank(output)) {
                return new QueryTransResData(ReservationVar.Code.FAIL, "服务异常");
            }
            JSONObject obj = JSONObject.parseObject(output);
            String errorCode = obj.getString("errCode");
            if (StringUtils.isNotBlank(errorCode) && (PayConst.ErrorCode.TOKEN_INVALID.equals(errorCode)
                    || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(errorCode))) {
                output = queryTransData(sysType, hospitalId, startTime, endTime, true);

                if (StringUtils.isBlank(output)) {
                    return new QueryTransResData(ReservationVar.Code.FAIL, "服务异常");
                }
            }
            return JsonTools.gJson2Bean(output, QueryTransResData.class);
        } catch (Exception e) {
            log.error("", e);
        }
        return new QueryTransResData(ReservationVar.Code.FAIL, "服务异常");
    }

    /**
     * 下载对账单出口
     *
     * @param sysType    是否是内部编码
     * @param hospitalId 医院ID
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param reload     是否重新获取
     * @return
     * @throws Exception
     */
    private String queryTransData(String sysType, Integer hospitalId, String startTime, String endTime, boolean reload) throws Exception {
        PayParm payParm = payProxyService.getToken(hospitalId, PayConst.Trans.PROVIDER_WEI_XIN + "", ReservationVar.AppID.APPID_YZDIV10, reload);
        if (payParm == null) {
            log.error(" ----医院支付配置异常----" + hospitalId);
            return null;
        }

        Map<String, Object> req = new HashMap<>();
        req.put("startTime", startTime);
        req.put("endTime", endTime);
        req.put("token", payParm.getToken());
        //req.put("accountId", 0);
        req.put("sysType", sysType);
        //获取授权因子

        String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
        req.put("authCode", authCode);
        String sign = Signature.getSign(payParm.getProviderPayKey(), req);
        req.put("sign", sign);
        String input = JsonTools.obj2Json(req);
        log.info("------下载对账数据入参--------" + input);
        String output = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + ClientConst.ReqUrl.PAY_QUERY_TRANS_URL, input);
        log.info("------下载对账数据出参-------" + output);

        return output;
    }

    public String saveTrans(Integer hospitalId, List<TransData> transData) {
        Trans trans;
        for (TransData obj : transData) {
            trans = new Trans();
            trans.setTransId(obj.getTransId());//交易流水号
            //处理金额
            BigDecimal amount = obj.getAmount();
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                amount = amount.negate();
            }
            trans.setAmount(amount);//交易金额
            trans.setOrderId(Long.parseLong(obj.getOrderNo()));//订单号
            trans.setRequestNo(obj.getOrderNo());//订单号
            trans.setTransTypeId(obj.getTransTypeId());//交易类型
            trans.setTransactionId(obj.getTransactionId());//第三方交易流水号
            trans.setProviderId(obj.getProviderCode().equals("") ? null : Integer.parseInt(obj.getProviderCode()));//1- 微信支付 2- 支付宝支付
            trans.setStatus(StringUtils.isBlank(obj.getTransStatus()) ? 1 : obj.getTransStatus().equals(PayStatus.success) ? 2 : obj.getTransStatus().equals(PayStatus.refund) ? 2 : 1);
            trans.setCreateTime(TimeUtils.formatDate(obj.getTransTime()));
            trans.setStartedTime(TimeUtils.formatDate(obj.getTransTime()));
            trans.setEndedTime(StringUtils.isBlank(obj.getEndTime()) ? null : TimeUtils.formatDate(obj.getEndTime()));
            trans.setOriginalTransId(obj.getOriginalTransId());
            trans.setHospitalId(hospitalId);
            try {
                //先删除，再保存
                transDao.deleteByPrimaryKey(obj.getTransId());
                transDao.insert(trans);
            } catch (Exception e) {
                log.error("保存对账数据异常", e);
            }
        }
        return JsonTools.obj2Json(new BaseRespond(ReservationVar.Code.SUCCESS, "下载成功"));
    }

    class PayStatus {
        private final static String success = "SUCCESS";
        private final static String refund = "REFUND";
        private final static String fail = "FAIL";
    }


    /**
     * 账单查询,可根据时间和订单编号查询
     *
     * @param hospitalId
     * @param startTime  交易开始时间。格式：YYYYMMDD
     * @param endTime    交易结束时间。格式：YYYYMMDD
     * @param orderNo    订单编号
     * @return
     */
    public QueryTransResData queryOrderTrans(String synUserName, String synKey, String startTime, String endTime, String orderNo, String hospitalId) {

        PayParm payParm = payProxyService.getToken(Integer.parseInt(hospitalId), PayConst.Trans.PROVIDER_WEI_XIN + "", ReservationVar.AppID.APPID_YZDIV10, false);
        if (payParm == null) {
            return new QueryTransResData(ReservationVar.Code.FAIL, "配置异常");
        }
        Map<String, Object> req = new HashMap<>();
        req.put("startTime", startTime);
        req.put("endTime", endTime);
        req.put("token", payParm.getToken());
        //req.put("accountId", 0);
        req.put("synUserName", synUserName);
        req.put("synKey", synKey);
        req.put("orderNo", orderNo);
        //获取授权因子
        try {
            String authCode = WXPayUtils.getAuthCode(payParm.getProviderPayAccount(), payParm.getSessionKey());
            req.put("authCode", authCode);
            String sign = Signature.getSign(payParm.getProviderPayKey(), req);
            req.put("sign", sign);
            String input = JsonTools.obj2Json(req);
            log.info("------下载对账数据入参--------" + input);
            String output = HttpUtils.sendPostRequest(SIVar.WS.paygateUrl + ClientConst.ReqUrl.PAY_QUERY_TRANS_URL, input);
            log.info("------下载对账数据出参-------" + output);
            if (StringUtils.isBlank(output)) {
                return new QueryTransResData(ReservationVar.Code.FAIL, "服务异常");
            }
            JSONObject obj = JSONObject.parseObject(output);
            String errorCode = obj.getString("errCode");
            if (StringUtils.isNotBlank(errorCode) && (PayConst.ErrorCode.TOKEN_INVALID.equals(errorCode)
                    || PayConst.ErrorCode.TOKEN_IS_EMPTY.equals(errorCode))) {
                payProxyService.getToken(Integer.parseInt(hospitalId), PayConst.Trans.PROVIDER_WEI_XIN + "", ReservationVar.AppID.APPID_YZDIV10, true);
            }
            QueryTransResData res = JsonTools.gJson2Bean(output, QueryTransResData.class);
            return res;
        } catch (Exception e) {
            log.error("", e);
        }
        return new QueryTransResData(ReservationVar.Code.FAIL, "服务异常");
    }
}
