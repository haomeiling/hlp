package cn.bxd.sip.bxd.var;

/**
 * @author haomeiling
 * @description
 * @vesion 1.0
 * @date 2018/12/28
 */
public enum PatientType {
    OWN((short) 1, "本人"), OTHER((short) 2, "他人"), CHILD((short) 3, "儿童");
    private short typeId;
    private String name;

    PatientType(short typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public short getTypeId() {
        return typeId;
    }

    public void setTypeId(short typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
