package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.dao.SimpleQueryDao;
import cn.bxd.sip.bxd.model.SmsSend;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.reg.*;
import cn.bxd.sip.bxd.util.HttpUtils;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.util.TimeUtils;
import cn.bxd.sip.bxd.var.HospitalCode;
import cn.bxd.sip.bxd.var.OrderType;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.bxd.var.TerminalVar;
import cn.bxd.sip.bxd.webservice.IRegWebService;
import cn.bxd.sip.bxd.webservice.common.WSTools;
import cn.bxd.sip.bxd.webservice.impl.base.OrderAssistService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.his.webservice.hisws.invoke.HISInterfaceQkbSoap;
import cn.bxd.sip.his.webservice.hisws.invoke2.ServiceSoap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : cRyann
 *
 * @create 2018-08-31
 **/
@Service
@Slf4j
@WebService(name = "Reg", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
public class RegWebService extends OrderAssistService implements IRegWebService {
    //平台医院ID
    private static final String PLATFROM_HOSPITAL_ID = "0";
    @Resource
    SimpleQueryDao simpleQueryDao;
    @Resource
    WebServiceContext context;

    @Override
    public DoRegAnddoTaketheNoRespond doRegAnddoTaketheNo(String synUserName,
                                                          String synKey,
                                                          String terminalCode,
                                                          String hospitalId,
                                                          String doRegIn) {
        DoRegAnddoTaketheNoRespond respond = new DoRegAnddoTaketheNoRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.DO_REG_AND_TAKETHE_NO, synUserName, synKey, doRegIn);
            //String res = " {\"sourceMark\": \"620825\",\"medicalCode\": \"620825\",\"departmentNum\": \"0317\",\"takeNo\": \"9\",\"resultCode\": \"00\",\"resultMsg\": \"挂号成功！\"}";
            respond = JsonTools.json2Bean(res, DoRegAnddoTaketheNoRespond.class);
            //检测号是否可用，若不可用，直接返回
            if (!respond.getResultCode().equals(TerminalVar.SUCCESS_CODE)) {
                return respond;
            }

            //获取userNo
            //保存订单
            log.debug("----保存订单开始----");
            Long orderId = insertOrder(ReservationVar.Order.ORDER_TYPE_REGISTRATION, ReservationVar.Order.ORDER_STATUS_CONFIRMED,
                    hospitalId, respond.getSourceMark(), respond.getTakeNo(), terminalCode, doRegIn);
            log.debug("----保存订单结束----");

            //设置单号
            respond.setOrderNo(String.valueOf(orderId));

            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("-----当日挂号平台系统错误------" + e.getMessage());
            return respond;
        }
    }

    @Override
    public DoRegOut doReg(String synUserName,
                          String synKey,
                          String terminalCode,
                          String hospitalId,
                          String doRegIn) {
        DoRegOut respond = new DoRegOut();
        respond.setResultCode(TerminalVar.FAIL_CODE_01);
        try {
            //从内存中读取配置
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            //检测配置是否合法
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            //发送请求至HIS获取号源
            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.DO_REG, synUserName, synKey, doRegIn);
            respond = JsonTools.json2Bean(res, DoRegOut.class);
            //检测号是否可用
            if (!respond.getResultCode().equals(TerminalVar.SUCCESS_CODE)) {
                return respond;
            }

            //保存订单
            log.debug("----保存订单开始----");
            Long orderId = insertOrder(ReservationVar.Order.ORDER_TYPE_APPOINTMENT, ReservationVar.Order.ORDER_STATUS_WAIT_CONFIRMED,
                    hospitalId, respond.getSourceMark(), respond.getTakeNo(), terminalCode, doRegIn);
            log.debug("----保存订单结束----");

            //设置ID
            respond.setOrderNo(String.valueOf(orderId));

        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg("----预约挂号平台系统错误----" + e.getMessage());
            return respond;
        }
        return respond;
    }


    /**
     * @Param synUserName               用户名
     * @Param synKey                效验码
     * @Param terminalCode                  终端编号
     * @Param hospitalId                医院ID
     * @Param sourceMark                号源编号
     * @Param patientNo                 患者编号
     * @Param sourceDate                号源日期
     * @Param departmentorganId                 科室编号
     * @Param payType               1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
     * @Param payRecord                 支付交易流水号
     * @Param payMoney                  实际支付金额
     * @Param extend                扩展字段Json值，如：{“key1”：”value1” ，”key2”：”value2”}     * 支付宝微信医保支付这里要传订单号（poNo）
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.7    门诊取号
     */
    @Override
    public DoTaketheNoRespond doTaketheNo(String synUserName,
                                          String synKey,
                                          String terminalCode,
                                          String hospitalId,
                                          String sourceMark,
                                          String patientNo,
                                          String sourceDate,
                                          String departmentorganId,
                                          String payType,
                                          String payRecord,
                                          String payMoney,
                                          String orderNo,
                                          String extend) {
        DoTaketheNoRespond respond = new DoTaketheNoRespond();

        try {
            //检测医院是否存在
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                respond.setResultCode(TerminalVar.FAIL_CODE);
                return respond;
            }

            //发送请求
            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            log.info("sourceMark:"+sourceMark + " patientNo:"+patientNo +" sourceDate:"+sourceDate +" departmentorganId:"
            		+departmentorganId +" payType:"+ payType + " payRecord:"+payRecord + " payMoney:"+payMoney);
            String res = HisWSClient.invoke(client, HisFunNameConst.DO_TAKETHE_NO, synUserName, synKey,
                    sourceMark, patientNo, sourceDate, departmentorganId, payType, payRecord, payMoney);
            log.info("res----------:"+res);
            //判断结果是否正确
            respond = JsonTools.json2Bean(res, DoTaketheNoRespond.class);
            if (!respond.getResultCode().equals(TerminalVar.SUCCESS_CODE)) {
                return respond;
            }

            //取号成功，则需要更新订单记录
            updateOrder(Long.parseLong(orderNo), respond.getTakeNo());

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg(e.getMessage());
            return respond;        }
    }


    /**
     * @Param synUserName
     * @Param synKey
     * @Param patientNo
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.9    查询未取号的预约挂号
     */
    @Override
    public QueryRegBypatientNoRespond queryRegBypatientNo(String synUserName, String synKey, String patientNo, String hospitalId) {
        QueryRegBypatientNoRespond respond = new QueryRegBypatientNoRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            //检查医院是否存在
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_REG_BYPATIENT_NO, synUserName, synKey, patientNo);
            log.info("res----------:"+res);
            respond = JSON.parseObject(res, QueryRegBypatientNoRespond.class);
            if (!respond.getResultCode().equals(TerminalVar.SUCCESS_CODE)) {
                return respond;
            }
            //从本地批量找
            if(respond.getHiRegisterRecordList().size() <= 0) {
            	return respond;
            }
            List<HiRegisterRecord> tmp = new ArrayList<>();
            for(HiRegisterRecord hiRegisterRecord : respond.getHiRegisterRecordList()) {
            	if(StringUtils.isNotBlank(hiRegisterRecord.getSourceDate())) {
            		List<Order> orderList = orderMapper.selectRegOrderListByPatient(Integer.parseInt(hospitalId), 
            				hiRegisterRecord.getSourceMark());
            		if(orderList != null && orderList.size() >0) {
            			hiRegisterRecord.setOrderNo(String.valueOf(orderList.get(0).getOrderId()));
            		}
            	}
            	tmp.add(hiRegisterRecord);
            	
            }
            respond.setHiRegisterRecordList(tmp);
            return respond;
        } catch (Exception e) {
            log.error("queryRegBypatientNo",e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("---系统异常----" + e.getMessage());
            return respond;
        }
    }

    @Override
    public String doRegRecord(String synUserName,
                              String synKey,
                              String terminalCode,
                              String hospitalId,
                              String patientNo) {
        DoRegRecordRespond respond = new DoRegRecordRespond();

        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return JSON.toJSONString(respond);
            }

            List<DoRegRecordInfo> list = simpleQueryDao.getDoRegRecordInfo(Long.valueOf(hospitalId), patientNo, (int) ReservationVar.Order.ORDER_TYPE_APPOINTMENT);

            //查该全部挂号, Lisheng 2019/5/7
            Object client = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            QueryRegBypatientNoRespond queryRegBypatientNoRespond = new QueryRegBypatientNoRespond();
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_REG_BYPATIENT_NO, synUserName, synKey, patientNo);
            log.info("res----------:"+res);
            queryRegBypatientNoRespond = JSON.parseObject(res, QueryRegBypatientNoRespond.class);
            List<DoRegRecordInfo> doRegRecordInfoList = new ArrayList<>();
            for (HiRegisterRecord hiRegisterRecord : queryRegBypatientNoRespond.getHiRegisterRecordList()){
                DoRegRecordInfo doRegRecordInfo = new DoRegRecordInfo();
                doRegRecordInfo.setSourceMark(hiRegisterRecord.getSourceMark());
                doRegRecordInfo.setOrgandoctorId(hiRegisterRecord.getOrgandoctorId());
                doRegRecordInfo.setOrgandoctorName(hiRegisterRecord.getOrgandoctorName());
                doRegRecordInfo.setDepartmentorganId(hiRegisterRecord.getDepartmentorganId());
                doRegRecordInfo.setDepartmentName(hiRegisterRecord.getDepartmentName());
                doRegRecordInfo.setVisitAddress(hiRegisterRecord.getVisitAddress());
                doRegRecordInfo.setSourceDate(TimeUtils.getDateFromStr(hiRegisterRecord.getSourceDate()));
                doRegRecordInfo.setSourceTimeType(String.valueOf(hiRegisterRecord.getSourceTimeType()));
                doRegRecordInfo.setTimestypeNo(hiRegisterRecord.getTimestypeNo());
                doRegRecordInfo.setTimestypeNoName(hiRegisterRecord.getTimestypeNoName());
                doRegRecordInfo.setConsultationFee(hiRegisterRecord.getConsultationFee());
                doRegRecordInfoList.add(doRegRecordInfo);
            }
            list.addAll(doRegRecordInfoList);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            respond.setDoRegRecordList(list);
            return JSON.toJSONString(respond);
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg(e.getMessage());
            return JSON.toJSONString(respond);
        }
    }

    @Override
    public DoRegAllRecordRespond    doRegAllRecord(String synUserName,
                                                String synKey,
                                                String terminalCode,
                                                String hospitalId,
                                                String patientNo,
                                                String cardNo,
                                                String startDate,
                                                String endDate) {
        DoRegAllRecordRespond respond = new DoRegAllRecordRespond();
        try {
            //检测医院是否接入
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            //从数据库查询数据
            List<PoState> list = simpleQueryDao.getDoRegAllRecordInfo(Long.valueOf(hospitalId),
                    patientNo,
                    (int) ReservationVar.Order.ORDER_TYPE_REGISTRATION,
                    startDate,
                    endDate);
            //从HIS获取挂号订单的 postate 挂号状态(0未使用，1已使用，2过期)
            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            for (PoState po : list) {
                String queryDoctorList = HisWSClient.invoke(client, HisFunNameConst.QUERY_TAKETHE_NO, synUserName, synKey,
                        po.getReservation(), po.getPatientNo(), DateUtil.formatDate(po.getSourceDateDb(), "yyyy-MM-dd"), po.getDepartmentNum());
                JSONObject object = JSONObject.parseObject(queryDoctorList);
                po.setState(String.valueOf(object.get("resultCode")));
                po.setSourceDate(DateUtil.formatDate(po.getSourceDateDb(), "yyyy-MM-dd"));
                if (po.getRegType().equals(OrderType.ORDER_TYPE_APPOINTMENT + "")) {
                    po.setRegType("预约");
                } else if (po.getRegType().equals(OrderType.ORDER_TYPE_REGISTRATION + "")) {
                    po.setRegType("挂号");
                }
            }
            respond.setPoState(list);
            respond.setResultCode(list.size() == 0 ? TerminalVar.NO_DATA_FAIL_CODE : TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg("----平台系统错误----" + e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryToRegDoctorListRespond queryToRegDoctorList(String synUserName,
                                                            String synKey,
                                                            String terminalCode,
                                                            String hospitalId,
                                                            String startDate,
                                                            String endDate) {
        QueryToRegDoctorListRespond respond = new QueryToRegDoctorListRespond();
        try {
            //获取配置
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            //从HIS获取消息
            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST, synUserName, synKey, startDate, endDate);

            //封装参数
            respond = JSON.parseObject(res, QueryToRegDoctorListRespond.class);


            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg("===系统错误===" + e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryToRegDoctorListByDoctorIdRespond queryToRegDoctorListByDoctorId(String synUserName,
                                                                                String synKey,
                                                                                String terminalCode,
                                                                                String hospitalId,
                                                                                String startDate,
                                                                                String endDate,
                                                                                String doctorId) {
        QueryToRegDoctorListByDoctorIdRespond respond = new QueryToRegDoctorListByDoctorIdRespond();
        try {
            //从内存获取配置
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //从HIS获取数据
            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST_BY_DOCTOR_ID, synUserName, synKey, startDate, endDate, doctorId);

            respond = JsonTools.json2Bean(res, QueryToRegDoctorListByDoctorIdRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            for (RegDoctor regDoctor : respond.getRegDoctor()) {
                // 获取平台部门ID
                String departmentId = simpleQueryDao.getDeptByDeptNo(Long.valueOf(hospitalId), Long.valueOf(regDoctor.getDepartmentorganId()));
                regDoctor.setDepartmentId(departmentId);
                if (regDoctor.getAfternoonFee() == null) {
                    regDoctor.setConsultationFee((float) 0);
                }
            }
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE);
            respond.setResultMsg(e.getMessage() + "系统异常");
            return respond;
        }


    }


    @Override
    public BaseRespond doRegCancel(String synUserName,
                                   String synKey,
                                   String terminalCode,
                                   String hospitalId,
                                   String sourceMark,
                                   String patientNo,
                                   String sourceDate,
                                   String departmentorganId,
                                   String orderNo,
                                   String cancelReason,
                                   String extend) {
        BaseRespond respond = new BaseRespond();
        try {
            //获取医院配置信息
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            //取消订单
            log.debug("-----取消订单--开始-------");
            if(StringUtils.isBlank(orderNo)) {
            	 log.info("订单号为空直接调用his取消");
            	 Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
                //贵港市中医医院取消需要增加终端号 2019-07-18 haomeiling
                String res;
                switch (Integer.parseInt(hospitalId)) {
                    case HospitalCode.GGSZYYY://贵港市中医医院的医院编码
                        res = HisWSClient.invoke(client, HisFunNameConst.DO_REG_CANCEL, synUserName, synKey, sourceMark, patientNo, sourceDate, departmentorganId,terminalCode);
                        break;
                    default:
                        res = HisWSClient.invoke(client, HisFunNameConst.DO_REG_CANCEL, synUserName, synKey, sourceMark, patientNo, sourceDate, departmentorganId);
                }

            	 log.info("调用his取消预约出参:"+res);
            	 respond = JsonTools.json2Bean(res, BaseRespond.class);
                 return respond;
            }
            cancelOrder(Long.parseLong(orderNo), cancelReason);
            log.debug("-----取消订单--结束-------");
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);

        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg("===系统错误===" + e.getMessage());
            return respond;
        }
        return respond;
    }

    @Override
    public QueryTaketheNoRespond queryTaketheNo(String synUserName,
                                                String synKey,
                                                String terminalCode,
                                                String hospitalId,
                                                String sourceMark,
                                                String patientNo,
                                                String sourceDate,
                                                String departmentorganId) {
        QueryTaketheNoRespond respond = new QueryTaketheNoRespond();
        try {
            //检测是否接入
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }


            //查询
            Object client = connectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TAKETHE_NO, synUserName, synKey, sourceMark, patientNo, sourceDate, departmentorganId);
            respond = JsonTools.json2Bean(res, QueryTaketheNoRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.SYSTEM_EXCEPTION_CODE);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public QueryRegWaitNumRespond queryRegWaitNum(String synUserName,
                                                  String synKey,
                                                  String terminalCode,
                                                  String hospitalId,
                                                  String sourceMark,
                                                  String patientNo,
                                                  String sourceDate,
                                                  String departmentorganId) {
        QueryRegWaitNumRespond respond = new QueryRegWaitNumRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }


            //取号
            Object client = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_REG_WAIT_NUM, synUserName, synKey, sourceMark, patientNo, sourceDate, departmentorganId);
            respond = JsonTools.json2Bean(res, QueryRegWaitNumRespond.class);

            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }


    @Override
    public QueryToRegDoctorTimesRespond queryToRegDoctorTimes(@NonNull String synUserName,
                                                              @NonNull String synKey,
                                                              @NonNull String terminalCode,
                                                              @NonNull String hospitalId,
                                                              @NonNull String sourceDate,
                                                              @NonNull String sourceTimeType,
                                                              @NonNull String organdoctorId,
                                                              String departmentorganId) {
        QueryToRegDoctorTimesRespond respond = new QueryToRegDoctorTimesRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            //调用HIS服务
            Object client = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String queryDoctorList = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_REG_DOCTOR_TIMES, synUserName, synKey,
                    sourceDate, organdoctorId, departmentorganId, sourceTimeType);
            if (StringUtils.isBlank(queryDoctorList)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultCode("HIS服务异常");
                return respond;
            }
            respond = JsonTools.gJson2Bean(queryDoctorList, QueryToRegDoctorTimesRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg("系统错误====" + e.getMessage());
            return respond;
        }
    }

    @Override
    public SendMessageRespond sendMessage(String synUserName,
                                          String synKey,
                                          String terminalCode,
                                          String hospitalId,
                                          String phone,
                                          String content) {
        SendMessageRespond respond = new SendMessageRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        respond.setResultMsg(TerminalVar.STATUS_FAIL);
        try {
            SmsSend smsSend = new SmsSend();
            smsSend.setUserName(TerminalVar.MESSAGE_USERNAME);
            smsSend.setUserPwd(TerminalVar.MESSAGE_USERPWD);
            smsSend.setContactPhone(phone);
            smsSend.setContentSms(content);
            smsSend.setMsgId(String.valueOf(System.currentTimeMillis()));
            smsSend.setTemplateCode(TerminalVar.MESSAGE_TEMPLATE_CODE);
            String result = HttpUtils.sendPostRequest(TerminalVar.MESSAGE_URL, JSON.toJSONString(smsSend));
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(result);
            return respond;
        } catch (IOException e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public DoctorSurNumLineRespond doctorSurNumLine(String synUserName,
                                                    String synKey,
                                                    String terminalCode,
                                                    String hospitalId,
                                                    String organdoctorId,
                                                    String sourceDate,
                                                    String departmentorganId) {

        DoctorSurNumLineRespond respond = new DoctorSurNumLineRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            String res;
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            if (clieat instanceof HISInterfaceQkbSoap) {
                HISInterfaceQkbSoap hisInterfaceQkbSoap = (HISInterfaceQkbSoap) clieat;
                res = hisInterfaceQkbSoap.queryDoctorRegWaitNum(synUserName, synKey, organdoctorId, departmentorganId);
            } else {
                ServiceSoap serviceSoap = (ServiceSoap) clieat;
                res = serviceSoap.queryDoctorRegWaitNum(synUserName, synKey, organdoctorId, departmentorganId);
            }

            respond = JsonTools.json2Bean(res, DoctorSurNumLineRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    //TODO HIS未按接口文档提供相关接口

    @Override
    public DoregFeeInterfaceRespond doregFeeInterface(String synUserName,
                                                      String synKey,
                                                      String terminalCode,
                                                      String hospitalId,
                                                      String organdoctorId,
                                                      String departmentorganId,
                                                      String patientNo,
                                                      String sourceDate,
                                                      String timestypeNo,
                                                      String sourceTimeType) {
        DoregFeeInterfaceRespond respond = new DoregFeeInterfaceRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            String res = HisWSClient.invoke(clieat, HisFunNameConst.DOREG_FEE_INTERFACE, synUserName, synKey
                    , organdoctorId, departmentorganId, patientNo, sourceDate, timestypeNo, sourceTimeType);
            respond = JsonTools.gJson2Bean(res, DoregFeeInterfaceRespond.class);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    @Override
    public DoRegMedicareInfoRespond doRegMedicareInfo(String synUserName,
                                                      String synKey,
                                                      String terminalCode,
                                                      String hospitalId,
                                                      String patientNo,
                                                      String doRegIn) {
        DoRegMedicareInfoRespond respond = new DoRegMedicareInfoRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            String res;
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            if (clieat instanceof HISInterfaceQkbSoap) {
                HISInterfaceQkbSoap hisInterfaceQkbSoap = (HISInterfaceQkbSoap) clieat;
                res = hisInterfaceQkbSoap.doRegMedicareInfo(synUserName, synKey, doRegIn);
            } else {
                ServiceSoap serviceSoap = (ServiceSoap) clieat;
                res = serviceSoap.doRegMedicareInfo(synUserName, synKey, doRegIn);
            }

            respond = JsonTools.json2Bean(res, DoRegMedicareInfoRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey        String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param patientNO        String	否	患者编号
     * @Param proCode        String	否	时间段中获取的排班唯一标识
     * @Param cardNo        String	否	身份证号
     * @Param timestypeNo    String	否	时间段标识
     * @Param sourceDate    String 	否	时间格式:yyyy-MM-dd
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.17    号源锁号 TODO HIS未按接口文档提供相关接口
     */
    @Override
    public BaseRespond lockRegisterNo(String synUserName,
                                      String synKey,
                                      String terminalCode,
                                      String hospitalId,
                                      String patientNo,
                                      String proCode,
                                      String cardNo,
                                      String timestypeNo,
                                      String sourceDate) {
        BaseRespond respond = new BaseRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            // 组装HIS入参 参数
            Map<String, Object> map = new HashMap<>();
            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("terminalCode", terminalCode);
            map.put("hospitalId", hospitalId);
            map.put("patientNO", patientNo);
            map.put("proCode", proCode);
            map.put("cardNo", cardNo);
            map.put("timestypeNo", timestypeNo);
            map.put("sourceDate", sourceDate);
            Object res = WSTools.invoke(clieat, "lockRegisterNo", map);
            respond = JsonTools.json2Bean(String.valueOf(res), DoRegMedicareInfoRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

    /**
     * @Param synUserName    String	否	用户名
     * @Param synKey        String	否	效验码
     * @Param terminalCode    String	否	终端编号
     * @Param hospitalId    String	否	医院ID
     * @Param patientNO        String	否	患者编号
     * @Param proCode        String	否	时间段中获取的排班唯一标识
     * @Param cardNo        String	否	身份证号
     * @Param timestypeNo    String	否	时间段标识
     * @Param sourceDate    String 	否	时间格式:yyyy-MM-dd
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.5.18    号源解锁 TODO HIS未按接口文档提供相关接口
     */
    @Override
    public BaseRespond unlockRegisterNo(String synUserName,
                                        String synKey,
                                        String terminalCode,
                                        String hospitalId,
                                        String patientNo,
                                        String proCode,
                                        String cardNo,
                                        String timestypeNo,
                                        String sourceDate) {
        BaseRespond respond = new BaseRespond();
        respond.setResultCode(TerminalVar.FAIL_CODE);
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_REG);
            // 组装HIS入参 参数
            Map<String, Object> map = new HashMap<>();
            map.put("synUserName", synUserName);
            map.put("synKey", synKey);
            map.put("terminalCode", terminalCode);
            map.put("hospitalId", hospitalId);
            map.put("patientNO", patientNo);
            map.put("proCode", proCode);
            map.put("cardNo", cardNo);
            map.put("timestypeNo", timestypeNo);
            map.put("sourceDate", sourceDate);
            Object res = WSTools.invoke(clieat, "unlockRegisterNo", map);
            respond = JsonTools.json2Bean(String.valueOf(res), BaseRespond.class);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond;
        }
    }

}
