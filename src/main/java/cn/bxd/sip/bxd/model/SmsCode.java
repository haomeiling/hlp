package cn.bxd.sip.bxd.model;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-18
 **/
@Data
public class SmsCode {
    String code;

    public SmsCode(String code) {
        this.code = code;
    }
}
