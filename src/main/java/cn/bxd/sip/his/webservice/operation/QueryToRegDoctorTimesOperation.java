package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description: 查询号类信息 1000 目前暂时写死
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-10
 */
@Slf4j
@Component
public class QueryToRegDoctorTimesOperation extends AbstractOperationProcessor {


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
       /*//掌上医院请求入参
        GetQueueRegReqDates getQueueRegReqDates = JSON.parseObject(reqMsg, GetQueueRegReqDates.class);
        String startTime = getQueueRegReqDates.getStartTime();
        String endTime = getQueueRegReqDates.getEndTime();
        String queryToRegDoctorListByDoctorIdStr;
        try {
            queryToRegDoctorListByDoctorIdStr = wsClient.queryToRegDoctorListByDoctorId(HisConvertConst.SYS_USER_NAME, HisConvertConst.SYS_KEY, startTime, endTime, getQueueRegReqDates.getEMeiNo());
            log.info("HIS返回：" + queryToRegDoctorListByDoctorIdStr);
        } catch (Exception e) {
            log.error("",e);
            log.info("HIS返回有误：" + e.getMessage());
            return errMsgReturn("连接有误");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> allBetweenDates;
        try {
            allBetweenDates = DateUtils.getAllBetweenDates(sdf.parse(startTime), sdf.parse(endTime));
        } catch (ParseException e) {
            log.error("",e);
            log.info("时间格式有误：" + e.getMessage());
            return errMsgReturn("时间格式有误");
        }
        List<GetQueueRegDateListItemResDates> dateList = new ArrayList<>();
        QueryToRegDoctorListByDoctorIdRes queryToRegDoctorListByDoctorIdRes = JSON.parseObject(queryToRegDoctorListByDoctorIdStr, QueryToRegDoctorListByDoctorIdRes.class);
        List<QueryToRegDoctorListItemRes> regDoctor = queryToRegDoctorListByDoctorIdRes.getRegDoctor();
        for (QueryToRegDoctorListItemRes q : regDoctor) {
            for (Date d : allBetweenDates) {
                String format = sdf.format(d);
                if (q.getSourceDate().equals(format)) {

                    GetQueueRegDateListItemResDates getQueueRegDateListItemResDates = new GetQueueRegDateListItemResDates();
                    getQueueRegDateListItemResDates.setDate(q.getSourceDate());
                    List<GetQueueRegQueueListItemResDates> queueList = new ArrayList<>();
                    List<GetQueueRegPeriodListItemResDates> periodList = new ArrayList<>();
                    GetQueueRegQueueListItemResDates getQueueRegQueueListItemResDates = new GetQueueRegQueueListItemResDates();
                    getQueueRegQueueListItemResDates.setQueueId(q.getDepartmentorganId());
                    getQueueRegQueueListItemResDates.setSectionId(q.getDepartmentorganId());
                    getQueueRegQueueListItemResDates.setPeriodList(periodList);
                    queueList.add(getQueueRegQueueListItemResDates);
                    getQueueRegDateListItemResDates.setQueueList(queueList);
                    dateList.add(getQueueRegDateListItemResDates);

                    if (q.getMorningNum() != -1) {
                        GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                        getQueueRegPeriodListItemResDates.setPeriodNo(1);
                        getQueueRegPeriodListItemResDates.setRemainNumber(q.getMorningNum());
                        periodList.add(getQueueRegPeriodListItemResDates);
                    }

                    if (q.getAfternoonNum() != -1) {
                        GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                        getQueueRegPeriodListItemResDates.setPeriodNo(2);
                        getQueueRegPeriodListItemResDates.setRemainNumber(q.getAfternoonNum());
                        periodList.add(getQueueRegPeriodListItemResDates);
                    }

                    if (q.getEveningNum() != -1) {
                        GetQueueRegPeriodListItemResDates getQueueRegPeriodListItemResDates = new GetQueueRegPeriodListItemResDates();
                        getQueueRegPeriodListItemResDates.setPeriodNo(3);
                        getQueueRegPeriodListItemResDates.setRemainNumber(q.getEveningNum());
                        periodList.add(getQueueRegPeriodListItemResDates);
                    }


                }
            }
        }

        GetQueueRegResDates getQueueRegResDates = new GetQueueRegResDates();
        getQueueRegResDates.setOperCode(HisConvertConst.Operation.GET_QUEUE_REG_REQ_CODE);
        getQueueRegResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getQueueRegResDates.setHosId(getQueueRegReqDates.getHosId());
        getQueueRegResDates.setDateList(dateList);
        return JSON.toJSONString(getQueueRegResDates);*/
        return "{\"dataList\":[{\"specifiedDoctor\":0,\"queueTypeName\":\"医师\",\"emergencyAddPrice\":5,\"individualPrice\":0,\"queueTypeId\":1,\"hospitalId\":28,\"queueTypeCode\":\"01\",\"indagationPrice\":0,\"registrationPrice\":0},{\"specifiedDoctor\":0,\"queueTypeName\":\"主治医师\",\"emergencyAddPrice\":10,\"individualPrice\":0,\"queueTypeId\":2,\"hospitalId\":28,\"queueTypeCode\":\"02\",\"indagationPrice\":0,\"registrationPrice\":0},{\"specifiedDoctor\":0,\"queueTypeName\":\"副主任医师\",\"emergencyAddPrice\":15,\"individualPrice\":0,\"queueTypeId\":3,\"hospitalId\":28,\"queueTypeCode\":\"03\",\"indagationPrice\":0,\"registrationPrice\":10},{\"specifiedDoctor\":0,\"queueTypeName\":\"主任医师\",\"emergencyAddPrice\":15,\"individualPrice\":0,\"queueTypeId\":4,\"hospitalId\":28,\"queueTypeCode\":\"04\",\"indagationPrice\":0,\"registrationPrice\":10}],\"operCode\":1000,\"success\":1}";
    }


}