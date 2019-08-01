package cn.bxd.sip.his.model.dto.reservation;

/**
 * @author：moqp
 * @Description：
 * @Date：2016/10/28 11:33.
 */
public class PartItem {//部位项 对象类
    private String PartCode;// 部位/方式编码
    private String PartName;//部位 /方式名称

    public String getPartCode() {
        return PartCode;
    }

    public void setPartCode(String partCode) {
        PartCode = partCode;
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String partName) {
        PartName = partName;
    }
}
