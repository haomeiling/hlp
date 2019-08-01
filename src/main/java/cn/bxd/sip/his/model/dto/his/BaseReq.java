package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class BaseReq {
    private String synUserName;
    private String synKey;

    public String getSynUserName() {
        return synUserName;
    }

    public void setSynUserName(String synUserName) {
        this.synUserName = synUserName;
    }

    public String getSynKey() {
        return synKey;
    }

    public void setSynKey(String synKey) {
        this.synKey = synKey;
    }
}