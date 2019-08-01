package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author：moqp
 * @Description：
 * @Date：2016/10/28 11:34.
 */
@Data
public class Item {//项类对象
    private String ItemCode;//项目编码
    private String ItemName;//项目名称
    private String Unit;//计量单位
    private String RefValue;//参考值
    private String Value;//结果值
}
