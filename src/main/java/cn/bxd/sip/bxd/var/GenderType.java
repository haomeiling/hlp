package cn.bxd.sip.bxd.var;

/**
 * @author lisheng
 * @date 2019/03/05
 * */
public enum GenderType {
    UNKNOW((short) 1, "未知的性别"), MAN((short) 2, "男性"), WOMAN((short) 3, "女性"), NO((short) 4, "未说明的性别");
    private short genderId;
    private String patientSexName;

    GenderType(short genderId, String patientSexName) {
        this.genderId = genderId;
        this.patientSexName = patientSexName;
    }

    public short getGenderId() {
        return genderId;
    }

    public void setGenderId(short genderId) {
        this.genderId = genderId;
    }

    public String getPatientSexName() {
        return patientSexName;
    }

    public void setPatientSexName(String patientSexName) {
        this.patientSexName = patientSexName;
    }
}
