package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.GetLastArrangeReqDates;
import cn.bxd.sip.his.model.dto.reservation.GetLastArrangeResDates;
import cn.bxd.sip.his.utils.DateUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 查询最晚排班日期 1
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@Component
public class GetLastArrangeOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        //入参：掌上医院请求入参
        GetLastArrangeReqDates getLastArrangeReqDates = JSON.parseObject(reqMsg, GetLastArrangeReqDates.class);

        //转换：进行参数转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GetLastArrangeResDates getLastArrangeResDates = new GetLastArrangeResDates();
        getLastArrangeResDates.setSuccess(HisConvertConst.Operation.OPERATION_SUCCESS);
        getLastArrangeResDates.setOperCode(HisConvertConst.Operation.GET_LAST_ARRANGE_REQ_CODE);
        getLastArrangeResDates.setHosId(getLastArrangeReqDates.getHosId());
        String lastDate = sdf.format(DateUtils.getNextDay(new Date(), 7));
        getLastArrangeResDates.setDate(lastDate);

        return JSON.toJSONString(getLastArrangeResDates);
    }

}