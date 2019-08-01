package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@Data
public class GetCardInfo {
    String synUserName;
    String synKey;
    String terminalCode;
    String hospitalId;
    String socialsecurityNO;
    String hospitalNO;
    String operatorNo;
    String cycleNo;
    String dynamic;
    String medicareType;
    String cardinfo;
}
