package cn.bxd.sip.bxd.var;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-02-21
 * Time: 11:48
 */
public enum ClinicType {
    //诊疗类型 1：门诊 2：住院  3：体检4：其他 5：挂号
    MZ(1, "门诊"),

    ZY(2, "住院"),

    TJ(3, "体检"),

    QT(4, "其他"),

    GH(5, "挂号");

    ClinicType(int typeVal, String desc) {
        this.typeVal = typeVal;
        this.desc = desc;
    }

    private int typeVal;
    private String desc;

    public int getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(int typeVal) {
        this.typeVal = typeVal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
