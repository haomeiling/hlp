package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * @author:tangliang
 * @date:2018/8/16
 * @description:
 */
@Data
public class DoRegCancelOutput {
    public String resultCode;   //00=表示成功，01=参数错误，03=其他错误

    public String resultMsg;
}
