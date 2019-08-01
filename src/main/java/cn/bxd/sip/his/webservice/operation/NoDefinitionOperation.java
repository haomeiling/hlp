package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.his.model.dto.reservation.BaseErrResDates;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * Description: 无定义操作
 * Package: com.bxd.sip.reservation.operation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-10
 */
@Component
public class NoDefinitionOperation extends AbstractOperationProcessor {

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
        return JSON.toJSONString(new BaseErrResDates("操作码有误"));
    }

}