package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.GetEarlyArrangeItemResDates;
import cn.bxd.sip.his.model.dto.reservation.GetEarlyArrangeReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetEarlyArrangeResDates;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: 查询最早排班日期 2020
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@Slf4j
@Component
public class GetEarlyArrangeOperation extends AbstractOperationProcessor {


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        //入参：掌上医院请求入参
        GetEarlyArrangeReqDates getEarlyArrangeReqDates = JSON.parseObject(reqMsg, GetEarlyArrangeReqDates.class);

        //转换：进行参数转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GetEarlyArrangeResDates getEarlyArrangeResDates = new GetEarlyArrangeResDates();
        getEarlyArrangeResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getEarlyArrangeResDates.setOperCode(HisConvertConst.Operation.GET_EARLY_ARRANGE_REQ_CODE);
        getEarlyArrangeResDates.setHosId(getEarlyArrangeReqDates.getHosId());
        List<GetEarlyArrangeItemResDates> dateList = new ArrayList<>();
        GetEarlyArrangeItemResDates getEarlyArrangeItemResDates = new GetEarlyArrangeItemResDates();
        getEarlyArrangeItemResDates.setDate(sdf.format(new Date()));
        dateList.add(getEarlyArrangeItemResDates);
        getEarlyArrangeResDates.setDateList(dateList);
        getEarlyArrangeResDates.setMsg("获取数据成功");
        return JSON.toJSONString(getEarlyArrangeResDates);
    }

}