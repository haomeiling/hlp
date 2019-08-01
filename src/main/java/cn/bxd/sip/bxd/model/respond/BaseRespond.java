package cn.bxd.sip.bxd.model.respond;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-06
 **/
@Data
public class BaseRespond {

    private String resultCode;
    private String resultMsg;

    public BaseRespond() {

    }

    public BaseRespond(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String toString() {
        return "TBaseRespond{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}
