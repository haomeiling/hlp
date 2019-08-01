package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.var.HospitalCode;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.DoRegExtend;
import cn.bxd.sip.his.model.dto.his.DoRegReq;
import cn.bxd.sip.his.model.dto.his.DoRegRes;
import cn.bxd.sip.his.model.dto.reservation.GetLockRegReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetLockRegResDates;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 取号接口 2004 1.5 预约挂号
 * Package: cn.bxd.sip.his.webservice.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@Slf4j
@Component
public class LockRegOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //入参：掌上医院请求入参
        GetLockRegReqDates getLockRegReqDates = JSON.parseObject(reqMsg, GetLockRegReqDates.class);
        //分割：入参中的 queueId|deptId
        String[] split = StringUtils.split(getLockRegReqDates.getQueueId(), "|");
        if (split.length != 2) {
            log.info(" 2004 queueId有误：" + getLockRegReqDates.getQueueId());
            return errMsgReturn("queueId有误");
        }
        String queueId = split[0];
        String deptId = split[1];
        String sourceDate = getLockRegReqDates.getClinicDate();
        //封装：his入参
        DoRegReq doRegReq = new DoRegReq();
        doRegReq.setOrgandoctorId(queueId);
        doRegReq.setDepartmentorganId(deptId);

        //doRegReq.setCardNo("452528198009278777");
        doRegReq.setPatientNo(getLockRegReqDates.getEmpi());
        doRegReq.setSourceDate(sourceDate);
        //时间 string
        doRegReq.setTimestypeNo(getLockRegReqDates.getTimesTypeNo());
        //时间段显示名称      Lisheng  2018/09/26
        doRegReq.setTimestypeNoName(getLockRegReqDates.getTimestypeNoName());
        //doRegReq.setTimestypeNo("12660");
        doRegReq.setSourceTimeType(getLockRegReqDates.getPeriodNo());
        //是否支付 0 否 1是
        doRegReq.setIsDopay("0");


        doRegReq.setTerminalCode(tRhipConnectParm.getPalmTerminalCode());
        //现场付款3
        doRegReq.setPayType("3");

        //设置扩展字段
        DoRegExtend doRegExtend = new DoRegExtend();

        //doRegExtend.setLxdh("13807880552");
        doRegExtend.setLxdh(getLockRegReqDates.getTelephone());
        doRegReq.setExtend(doRegExtend);

        //2019/1/21
        doRegReq.setPatientName(getLockRegReqDates.getPatientName());//患者姓名
        doRegReq.setGenderCode(getLockRegReqDates.getGenderCode());//患者性别  0未知的性别,1男性,2女性,9未说明的性别
        doRegReq.setSourceCode(getLockRegReqDates.getSourceCode());//预约来源  1微信,2支付宝,13云pos,14自助机

        //贵港市中医医院，如果没有身份证，身份证的字段传patientNo haomeiling 20190719
        Integer hospitalId = tRhipConnectParm.getHospitalId();
        String cardNo=getLockRegReqDates.getCardNo();
        switch (hospitalId) {
            case HospitalCode.GGSZYYY:
                if(cardNo==null||cardNo.equals("")) {
                    doRegReq.setCardNo(getLockRegReqDates.getEmpi());
                }else {
                    doRegReq.setCardNo(cardNo);
                }
                break;
            default://其他医院传空
                if(cardNo==null||cardNo.equals("")) {
                    doRegReq.setCardNo("");
                }else {
                    doRegReq.setCardNo(cardNo);
                }
        }

        //设置联系电话和联系电话 Lisheng  2019/6/29
        doRegReq.setPatientTelephone(getLockRegReqDates.getTelephone());//联系电话 lisheng
        doRegReq.setVisitCardNo(getLockRegReqDates.getVisitCardNo()); //就诊卡号 lisheng

        doRegReq.setSocialsecurityNO("");//社保号 lisheng 2019/7/16

        //设置payNo和payAmount为空，否则部分医院会报错 20190718 haomeiling
        doRegReq.setPayAmount("");
        doRegReq.setPayNo("");

        String doRegReqStr = JSON.toJSONString(doRegReq);
        //用户名 校验码
        String sysUserName = tRhipConnectParm.getUserName();
        String sysKey = tRhipConnectParm.getCheckCode();

        //封装：向HIS发送请求
        //ws客户端：1.5 预约挂号
        Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
        String doReg;
        try {
            //如果是当天的，调用预约当天并取号doRegAndTaketheNo。否则预约挂号doReg
            if (sdf.format(new Date()).equals(sourceDate)) {
                log.debug("---his doRegAndTaketheNo入参 ---:" + doRegReqStr);
                doReg = HisWSClient.invoke(hosWsClient4, HisFunNameConst.DO_REG_AND_TAKETHE_NO, sysUserName, sysKey, doRegReqStr);
                log.debug(" 2004 预约当天并取号 向HIS发送返回：" + doReg);
            } else {
                log.debug("---his doReg入参 ---:" + doRegReqStr);
                doReg = HisWSClient.invoke(hosWsClient4, HisFunNameConst.DO_REG, sysUserName, sysKey, doRegReqStr);
                log.debug(" 2004 预约挂号 向HIS发送返回：" + doReg);
            }
        } catch (Exception e) {
            log.error("", e);
            log.info(" 2004 向HIS发送返回有误：" + e.getMessage());
            return errMsgReturn("连接有误");
        }

        //解析：his返回成对象

        DoRegRes doRegRes = JSON.parseObject(doReg, DoRegRes.class);

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, doRegRes.getResultCode())) {
            return errMsgReturn(doRegRes.getResultMsg());
        }

        //转换：进行参数转换
        GetLockRegResDates getLockRegResDates = new GetLockRegResDates();
        getLockRegResDates.setOperCode(HisConvertConst.Operation.GET_LOCK_REG_REQ_CODE);
        getLockRegResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getLockRegResDates.setHosId(getLockRegReqDates.getHosId());
        getLockRegResDates.setSectionAddr(doRegRes.getDepartmentNum());
        getLockRegResDates.setPeerOrderNo(doRegRes.getSourceMark());
        getLockRegResDates.setDisplayQueueNo(doRegRes.getMedicalCode());
        getLockRegResDates.setRegNo(doRegRes.getTakeNo());
        getLockRegResDates.setTimeoutSeconds(1800);
        getLockRegResDates.setClinicDate(sourceDate);
        getLockRegResDates.setClinicDateInt(Integer.valueOf(StringUtils.replaceAll(sourceDate, "-", "")));
        getLockRegReqDates.setPeriodNo(getLockRegReqDates.getPeriodNo());
        return JSON.toJSONString(getLockRegResDates);
    }
}