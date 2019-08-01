package cn.bxd.sip.bxd.model.request.hisuser;

import cn.bxd.sip.bxd.model.respond.hisuser.HiUser;
import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@Data
public class GetOverallArea {
    String synUserName;
    String synKey;
    String terminalCode;
    String hospitalId;
    String hospitalNO;
    String cycleNo;
    HiUser dynamic;
    String cardinfo;
    String medicareType;
}
