package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListByDoctorIdRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListItemRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorTimesItemRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorTimesRes;
import cn.bxd.sip.his.model.dto.reservation.*;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description: 查询号源时段 2003 查询医生号源时间段 1.5 预约挂号
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-10
 */
@Slf4j
@Component
public class GetQueueRegOperation extends AbstractOperationProcessor {

    /**
     * 上午时段
     */
    private static final int MORNING_PERIOD_NO = 1;
    /**
     * 下午时段
     */
    private static final int AFTERNOON_PERIOD_NO = 2;

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        //入参：掌上医院请求入参
        GetQueueRegReqDates getQueueRegReqDates = JSON.parseObject(reqMsg, GetQueueRegReqDates.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = getQueueRegReqDates.getStartTime();
        String endTime = getQueueRegReqDates.getEndTime();
        int periodNo = getQueueRegReqDates.getPeriodNo();
        // 分割 queueId|deptId
        String[] queueIdSplit = StringUtils.split(getQueueRegReqDates.getQueueId(), "|");
        if (queueIdSplit.length != 2) {
            log.info(" 2003 queueId有误：" + getQueueRegReqDates.getQueueId());
            return errMsgReturn("queueId有误");
        }
        String queueId = queueIdSplit[0];
        String deptId = queueIdSplit[1];

        List<Date> allBetweenDates;
        try {
            allBetweenDates = DateUtils.getAllBetweenDates(sdf.parse(startTime), sdf.parse(endTime));
        } catch (ParseException e) {
            log.error("",e);
            log.info("时间格式有误：" + e.getMessage());
            return errMsgReturn("时间格式有误");
        }

        //转换：进行参数转换
        List<GetQueueRegDateListItemResDates> dateList = new ArrayList<>();

        //ws客户端：1.5 预约挂号
        Object hosWsClient4 = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
        String sysUserName = tRhipConnectParm.getUserName();
        String sysKey = tRhipConnectParm.getCheckCode();

        //需要具体上下午时段，则调用queryToRegDoctorTimes接口
        if (periodNo == -1) {
            //循环上下午
            for (Date d : allBetweenDates) {

                GetQueueRegDateListItemResDates getQueueRegDateListItemResDates = new GetQueueRegDateListItemResDates();
                List<GetQueueRegQueueListItemResDates> queueList = new ArrayList<>();
                // 2019/1/14 发现bug，处理方式，增加HashMap去重
                HashMap<String, List<GetQueueRegPeriodListItemResDates>> queueMap = new HashMap<>();
                List<GetQueueRegPeriodListItemResDates> periodList = new ArrayList<>();
                GetQueueRegQueueListItemResDates getQueueRegQueueListItemResDates = new GetQueueRegQueueListItemResDates();
                for (int i = MORNING_PERIOD_NO; i <= AFTERNOON_PERIOD_NO; i++) {

                    //封装：向HIS发送请求
                    String formatDate = sdf.format(d);
                    String queryToRegDoctorTimes;

                    try {
                        queryToRegDoctorTimes = HisWSClient.invoke(hosWsClient4, HisFunNameConst.QUERY_TO_REG_DOCTOR_TIMES, sysUserName, sysKey, formatDate, queueId, deptId, String.valueOf(i));
                        log.debug(" 2003 1 2 查询医生号源时间段 HIS返回：" + queryToRegDoctorTimes);
                    } catch (Exception e) {
                        log.error("",e);
                        log.debug(" 2003 1 2 查询医生号源时间段 HIS返回有误：" + e.getMessage());
                        return errMsgReturn("连接有误");
                    }

                    //解析：his返回成对象
                    QueryToRegDoctorTimesRes queryToRegDoctorTimesRes = JSON.parseObject(queryToRegDoctorTimes, QueryToRegDoctorTimesRes.class);
                    //判断：是否返回有错误
                    String resultCode = queryToRegDoctorTimesRes.getResultCode();
                    String resultMsg = queryToRegDoctorTimesRes.getResultMsg();

                    if (i == MORNING_PERIOD_NO && !HisConvertConst.HisCode.SUCCESS_CODE.equals(resultCode) && HisConvertConst.HisCode.NO_DATA.equals(resultMsg)) {
                        continue;
                    } else if (i == AFTERNOON_PERIOD_NO && !HisConvertConst.HisCode.SUCCESS_CODE.equals(resultCode) && HisConvertConst.HisCode.NO_DATA.equals(resultMsg)) {
                        continue;
                    }

                    List<QueryToRegDoctorTimesItemRes> regDoctorTimes = queryToRegDoctorTimesRes.getRegDoctorTimes();
                    getQueueRegDateListItemResDates.setDateInt(Integer.parseInt(StringUtils.replaceAll(formatDate, "-", "")));
                    getQueueRegDateListItemResDates.setDate(formatDate);
                    int size = regDoctorTimes != null ? regDoctorTimes.size() : 0;
                    for (int j = 0; j < size; j++) {
                        GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                        getQueueRegPeriodListItemResDates.setPeriodStart(regDoctorTimes.get(j).getTimestypeNoName());

                        //开始时段做特殊处理 haomeiling 20180920
                        String replace = StringUtils.replace(regDoctorTimes.get(j).getTimestypeNoName(), ":", "");
                        int startInt;
                        if (replace.contains("-")) {
                            startInt = Integer.parseInt(replace.substring(0, replace.indexOf("-")));
                        } else {
                            startInt = Integer.parseInt(replace);
                        }
                        getQueueRegPeriodListItemResDates.setPeriodStartInt(startInt);

                        getQueueRegPeriodListItemResDates.setPeriodNo(regDoctorTimes.get(j).getSourceTimeType());
                        getQueueRegPeriodListItemResDates.setRemainNumber(Integer.valueOf(regDoctorTimes.get(j).getSourceDateNum()));
                        getQueueRegPeriodListItemResDates.setTotalNumber(Integer.valueOf(regDoctorTimes.get(j).getSourceDateNum()));
                        getQueueRegPeriodListItemResDates.setPeriodName(regDoctorTimes.get(j).getSourceTimeType() == MORNING_PERIOD_NO ? "上午" : "下午");
                        getQueueRegPeriodListItemResDates.setTimesTypeNo(regDoctorTimes.get(j).getTimestypeNo());
                        getQueueRegPeriodListItemResDates.setTimesTypeNoName(regDoctorTimes.get(j).getTimestypeNoName());
                        periodList.add(getQueueRegPeriodListItemResDates);
                        if (j == size - 1) {
                            String qId = regDoctorTimes.get(j).getOrgandoctorId() + "|" + regDoctorTimes.get(j).getDepartmentorganId();
                            queueMap.put(qId,periodList);

                            if (queueList.isEmpty()){
                                getQueueRegQueueListItemResDates.setQueueId(qId);
                                getQueueRegQueueListItemResDates.setSectionId(regDoctorTimes.get(j).getDepartmentorganId());
                                getQueueRegQueueListItemResDates.setPeriodList(queueMap.get(qId));
                                queueList.add(getQueueRegQueueListItemResDates);
                                getQueueRegDateListItemResDates.setQueueList(queueList);
                                dateList.add(getQueueRegDateListItemResDates);
                            }else {
                                for (GetQueueRegQueueListItemResDates q :queueList){
                                    if (!q.getQueueId().equals(qId)){
                                        getQueueRegQueueListItemResDates.setQueueId(qId);
                                        getQueueRegQueueListItemResDates.setSectionId(regDoctorTimes.get(j).getDepartmentorganId());
                                        getQueueRegQueueListItemResDates.setPeriodList(queueMap.get(qId));
                                        queueList.add(getQueueRegQueueListItemResDates);
                                        getQueueRegDateListItemResDates.setQueueList(queueList);
                                        dateList.add(getQueueRegDateListItemResDates);
                                    }
                                }
                            }

                        }
                    }

                    //兼容10667贵港市中医医院，只查询1次   lisheng 2019/7/18
                    if ("10667".equals(getQueueRegReqDates.getHosId())){
                        break;
                    }
                }
            }

        } else if (periodNo == MORNING_PERIOD_NO || periodNo == AFTERNOON_PERIOD_NO) {
            for (Date d : allBetweenDates) {
                //封装：向HIS发送请求
                String formatDate = sdf.format(d);

                String queryToRegDoctorTimes;
                try {
                    queryToRegDoctorTimes = HisWSClient.invoke(hosWsClient4, HisFunNameConst.QUERY_TO_REG_DOCTOR_TIMES,
                            sysUserName, sysKey, formatDate, queueId, deptId, String.valueOf(getQueueRegReqDates.getPeriodNo()));
                    log.debug(" 2003 1 2 查询医生号源时间段 HIS返回：" + queryToRegDoctorTimes);
                } catch (Exception e) {
                    log.debug(" 2003 1 2 查询医生号源时间段 HIS返回有误：" + e.getMessage());
                    return errMsgReturn("连接有误");
                }

                //解析：his返回成对象
                QueryToRegDoctorTimesRes queryToRegDoctorTimesRes = JSON.parseObject(queryToRegDoctorTimes, QueryToRegDoctorTimesRes.class);
                //判断：是否返回有错误
                String resultCode = queryToRegDoctorTimesRes.getResultCode();
                if (!HisConvertConst.HisCode.SUCCESS_CODE.equals(resultCode)) {
                    return errMsgReturn(queryToRegDoctorTimesRes.getResultMsg());
                }

                GetQueueRegDateListItemResDates getQueueRegDateListItemResDates = new GetQueueRegDateListItemResDates();
                List<GetQueueRegQueueListItemResDates> queueList = new ArrayList<>();
                List<GetQueueRegPeriodListItemResDates> periodList = new ArrayList<>();
                List<QueryToRegDoctorTimesItemRes> regDoctorTimes = queryToRegDoctorTimesRes.getRegDoctorTimes();
                getQueueRegDateListItemResDates.setDateInt(Integer.parseInt(StringUtils.replaceAll(formatDate, "-", "")));
                getQueueRegDateListItemResDates.setDate(formatDate);
                int size = regDoctorTimes.size();
                for (int j = 0; j < size; j++) {
                    if (j == 0) {
                        GetQueueRegQueueListItemResDates getQueueRegQueueListItemResDates = new GetQueueRegQueueListItemResDates();
                        getQueueRegQueueListItemResDates.setQueueId(regDoctorTimes.get(j).getOrgandoctorId() + "|" + regDoctorTimes.get(j).getDepartmentorganId());
                        getQueueRegQueueListItemResDates.setSectionId(regDoctorTimes.get(j).getDepartmentorganId());
                        getQueueRegQueueListItemResDates.setPeriodList(periodList);
                        queueList.add(getQueueRegQueueListItemResDates);
                        getQueueRegDateListItemResDates.setQueueList(queueList);
                        dateList.add(getQueueRegDateListItemResDates);
                    }
                    GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                    getQueueRegPeriodListItemResDates.setPeriodStart(regDoctorTimes.get(j).getTimestypeNoName());
                    //getQueueRegPeriodListItemResDates.setPeriodStartInt(q.getTimestypeNo());
                    getQueueRegPeriodListItemResDates.setPeriodNo(regDoctorTimes.get(j).getSourceTimeType());

                    //做兼容处理
                    String sourceDateNum=regDoctorTimes.get(j).getSourceDateNum();
                    int sourceDateNumInt=sourceDateNum.equals("")?0:Integer.parseInt(sourceDateNum);
                    getQueueRegPeriodListItemResDates.setRemainNumber(sourceDateNumInt);
                    getQueueRegPeriodListItemResDates.setTotalNumber(sourceDateNumInt);

                    getQueueRegPeriodListItemResDates.setPeriodName(regDoctorTimes.get(j).getSourceTimeType() == MORNING_PERIOD_NO ? "上午" : "下午");
                    getQueueRegPeriodListItemResDates.setTimesTypeNo(regDoctorTimes.get(j).getTimestypeNo());
                    getQueueRegPeriodListItemResDates.setTimesTypeNoName(regDoctorTimes.get(j).getTimestypeNoName());
                    periodList.add(getQueueRegPeriodListItemResDates);
                }
            }


        } else {
            //封装：向HIS发送请求
            String queryToRegDoctorListByDoctorIdStr;
            try {
                queryToRegDoctorListByDoctorIdStr = HisWSClient.invoke(hosWsClient4, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST_BY_DOCTOR_ID,
                        sysUserName, sysKey, getQueueRegReqDates.getStartTime(), getQueueRegReqDates.getEndTime(), queueId);

                log.debug(" 2003 根据医生查询号源 HIS返回：" + queryToRegDoctorListByDoctorIdStr);
            } catch (Exception e) {
                log.error("",e);
                log.info(" 2003 根据医生查询号源 HIS返回有误：" + e.getMessage());
                return errMsgReturn("连接有误");
            }

            //解析：his返回成对象
            QueryToRegDoctorListByDoctorIdRes queryToRegDoctorListByDoctorIdRes = JSON.parseObject(queryToRegDoctorListByDoctorIdStr, QueryToRegDoctorListByDoctorIdRes.class);
            //判断：是否返回有错误
            String resultCode = queryToRegDoctorListByDoctorIdRes.getResultCode();
            if (!HisConvertConst.HisCode.SUCCESS_CODE.equals(resultCode)) {
                return errMsgReturn(queryToRegDoctorListByDoctorIdRes.getResultMsg());
            }

            //转换：进行参数转换
            List<QueryToRegDoctorListItemRes> regDoctor = queryToRegDoctorListByDoctorIdRes.getRegDoctor();

            for (QueryToRegDoctorListItemRes q : regDoctor) {
                //过滤：就诊日志是否一致
                for (Date d : allBetweenDates) {
                    String format = sdf.format(d);
                    if (q.getSourceDate().equals(format) && q.getDepartmentorganId().equals(deptId)) {

                        GetQueueRegDateListItemResDates getQueueRegDateListItemResDates = new GetQueueRegDateListItemResDates();
                        getQueueRegDateListItemResDates.setDateInt(Integer.parseInt(StringUtils.replaceAll(q.getSourceDate(), "-", "")));
                        getQueueRegDateListItemResDates.setDate(q.getSourceDate());
                        List<GetQueueRegQueueListItemResDates> queueList = new ArrayList<>();
                        List<GetQueueRegPeriodListItemResDates> periodList = new ArrayList<>();
                        GetQueueRegQueueListItemResDates getQueueRegQueueListItemResDates = new GetQueueRegQueueListItemResDates();
                        getQueueRegQueueListItemResDates.setQueueId(q.getOrgandoctorId() + "|" + q.getDepartmentorganId());
                        getQueueRegQueueListItemResDates.setSectionId(q.getDepartmentorganId());
                        getQueueRegQueueListItemResDates.setPeriodList(periodList);
                        queueList.add(getQueueRegQueueListItemResDates);
                        getQueueRegDateListItemResDates.setQueueList(queueList);
                        dateList.add(getQueueRegDateListItemResDates);

                        if (q.getMorningNum() != -1 && (periodNo == 0)) {
                            GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                            getQueueRegPeriodListItemResDates.setPeriodNo(1);
                            getQueueRegPeriodListItemResDates.setRemainNumber(q.getMorningNum());
                            getQueueRegPeriodListItemResDates.setTotalNumber(q.getMorningNum());
                            getQueueRegPeriodListItemResDates.setPeriodName("上午");
                            periodList.add(getQueueRegPeriodListItemResDates);
                            getQueueRegQueueListItemResDates.setRegisterPrice(q.getConsultationFee().equals("") ? "0.0000" : q.getConsultationFee());
                        }

                        if (q.getAfternoonNum() != -1 && (periodNo == 0)) {
                            GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                            getQueueRegPeriodListItemResDates.setPeriodNo(2);
                            getQueueRegPeriodListItemResDates.setRemainNumber(q.getAfternoonNum());
                            getQueueRegPeriodListItemResDates.setTotalNumber(q.getAfternoonNum());
                            getQueueRegPeriodListItemResDates.setPeriodName("下午");
                            periodList.add(getQueueRegPeriodListItemResDates);
                            getQueueRegQueueListItemResDates.setRegisterPrice(q.getAfternoonFee());

                        }

                        if (q.getEveningNum() != -1 && (periodNo == 0)) {
                            GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                            getQueueRegPeriodListItemResDates.setPeriodNo(3);
                            getQueueRegPeriodListItemResDates.setRemainNumber(q.getEveningNum());
                            getQueueRegPeriodListItemResDates.setTotalNumber(q.getEveningNum());
                            getQueueRegPeriodListItemResDates.setPeriodName("晚上");
                            periodList.add(getQueueRegPeriodListItemResDates);
                            getQueueRegQueueListItemResDates.setRegisterPrice(q.getEveningFee());
                        }

                    }
                }
            }
        }

        //掌上医院返回
        GetQueueRegResDates getQueueRegResDates = new GetQueueRegResDates();
        getQueueRegResDates.setOperCode(HisConvertConst.Operation.GET_QUEUE_REG_REQ_CODE);
        getQueueRegResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getQueueRegResDates.setHosId(getQueueRegReqDates.getHosId());
        getQueueRegResDates.setDateList(dateList);
        return JSON.toJSONString(getQueueRegResDates, SerializerFeature.DisableCircularReferenceDetect);
    }


}