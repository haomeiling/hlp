package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListItemRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListRes;
import cn.bxd.sip.his.model.dto.reservation.GetQueueListItemResDates;
import cn.bxd.sip.his.model.dto.reservation.GetQueueListReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetQueueListResDates;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description: 查询队列列表 2021 根据医生ID查询号源 1.5 预约挂号
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@Slf4j
@Component
public class GetQueueListOperation extends AbstractOperationProcessor {


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        //入参：掌上医院请求入参
        GetQueueListReqDates getQueueListReqDates = JSON.parseObject(reqMsg, GetQueueListReqDates.class);
        String deptIds = getQueueListReqDates.getDeptIds();

        //封装：向HIS发送请求
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] deptIdsSplit = StringUtils.split(deptIds, "|");
        String startTime = getQueueListReqDates.getStartTime();
        String endTime = getQueueListReqDates.getEndTime();
        if (StringUtils.isBlank(endTime)) {
            endTime = sdf.format(DateUtils.getNextDay(new Date(), 7));
        }

        String sysUserName = tRhipConnectParm.getUserName();
        String sysKey = tRhipConnectParm.getCheckCode();

        String queryToRegDoctorListStr;
        try {

            Object reflectClass = getHosWsClient(HisConvertConst.WS_CLIENT_KEY_REG, tRhipConnectParm);
            queryToRegDoctorListStr = HisWSClient.invoke(reflectClass, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST, sysUserName, sysKey, startTime, endTime);
            log.debug(" 2021 查询号源 HIS返回--：" + queryToRegDoctorListStr);
        } catch (Exception e) {
            log.debug(" 2021 查询号源 HIS返回有误：" + e.getMessage());
            log.error("",e);
            return errMsgReturn("连接有误");

        }

        //判断：是否是json格式
        QueryToRegDoctorListRes queryToRegDoctorListRes;
        //解析：his返回成对象
        try {
            queryToRegDoctorListRes = JSON.parseObject(queryToRegDoctorListStr, QueryToRegDoctorListRes.class);
        } catch (Exception e) {
            log.error("",e);
            return errMsgReturn(queryToRegDoctorListStr);
        }

        //判断：是否为空
        if (StringUtils.equals(HisConvertConst.HisCode.NO_DATA_CODE, queryToRegDoctorListRes.getResultCode())) {
            return noDataMsgReturn(queryToRegDoctorListRes.getResultCode(), queryToRegDoctorListRes.getResultMsg());
        }

        //判断：是否返回有错误
        if (!StringUtils.equals(HisConvertConst.HisCode.SUCCESS_CODE, queryToRegDoctorListRes.getResultCode())) {
            return errMsgReturn(queryToRegDoctorListRes.getResultMsg());
        }

        //转换：进行参数转换
        List<QueryToRegDoctorListItemRes> regDoctorList = queryToRegDoctorListRes.getRegDoctor();
        List<GetQueueListItemResDates> itemList = new ArrayList<>();
        Map<String, String> doctorMap = new HashMap<>();


        //获取最小日期
        Map<String, String> minDateMap = new HashMap<>();
        for (QueryToRegDoctorListItemRes q : regDoctorList) {
            String organDoctorId = q.getOrgandoctorId();
            String nowDate = minDateMap.get(organDoctorId);
            if (nowDate != null && !nowDate.equals("")) {//如果不为空，继续比较
                Integer nowDateInt = TimeUtils.transDateStr2Int(nowDate);
                Integer sourceDateInt = TimeUtils.transDateStr2Int(q.getSourceDate());
                if (nowDateInt > sourceDateInt) {
                    minDateMap.put(organDoctorId, q.getSourceDate());
                }
            } else {//如果为空，直接存入
                minDateMap.put(organDoctorId, q.getSourceDate());
            }
        }

        for (QueryToRegDoctorListItemRes q : regDoctorList) {
            //判断：去重
            if (doctorMap.containsKey(q.getOrgandoctorId()+"|"+q.getDepartmentorganId())) {
                continue;
            }
            for (String s : deptIdsSplit)
                if (s.equals(q.getDepartmentorganId())) {
                   //判断可用号源数目，如果可用号源数目为0，则不返回

                    GetQueueListItemResDates queueListItemResDates = new GetQueueListItemResDates();
                    queueListItemResDates.setQueueId(q.getOrgandoctorId()+"|"+q.getDepartmentorganId() );
                    queueListItemResDates.setEmpNo("0");
                    queueListItemResDates.setRegisterPrice(q.getConsultationFee());

                    //数字类型的时间
                    queueListItemResDates.setPlanDate(minDateMap.get(q.getOrgandoctorId()));
                    queueListItemResDates.setClinicDate(minDateMap.get(q.getOrgandoctorId()));

                    //-1显示未排班  0显示约满
                    if (q.getMorningNum() == -1 && q.getAfternoonNum() == -1 && q.getEveningNum() == -1) {
                        //0：可预约  1：无排班  2：已约满
                        queueListItemResDates.setFlag((byte) 1);
                    } else if (q.getMorningNum() == 0 && q.getAfternoonNum() == 0 && q.getEveningNum() == 0) {
                        queueListItemResDates.setFlag((byte) 2);
                    } else {
                        queueListItemResDates.setFlag((byte) 0);
                       //可用号数
                        int totalAvailable=0;
                        if(q.getMorningNum()>0){
                            totalAvailable+=q.getMorningNum();
                        }
                        if(q.getAfternoonNum()>0){
                            totalAvailable+=q.getAfternoonNum();
                        }
                        if(q.getEveningNum()>0){
                            totalAvailable+=q.getEveningNum();
                        }
                        queueListItemResDates.setTotalAvailable(totalAvailable);
                    }
                    queueListItemResDates.setEMeiNo(q.getOrgandoctorId());


                    itemList.add(queueListItemResDates);
                }

            doctorMap.put(q.getOrgandoctorId()+"|"+q.getDepartmentorganId(), q.getOrgandoctorId()+"|"+q.getDepartmentorganId());
        }

        //返回：掌上医院需要的响应
        GetQueueListResDates getQueueListResDates = new GetQueueListResDates();
        getQueueListResDates.setOperCode(HisConvertConst.Operation.GET_QUEUE_LIST_REQ_CODE);
        getQueueListResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getQueueListResDates.setHosId(getQueueListReqDates.getHosId());
        getQueueListResDates.setQueueList(itemList);
        return JSON.toJSONString(getQueueListResDates);
    }

}