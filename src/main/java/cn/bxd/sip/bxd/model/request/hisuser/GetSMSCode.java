package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@Data
public class GetSMSCode {
    String synUserName;
    String synKey;
    String terminalCode;
    String hospitalId;
    String mobileNo;
}
