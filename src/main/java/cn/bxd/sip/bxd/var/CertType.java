package cn.bxd.sip.bxd.var;

/**
 * 证件类型
 *
 * @author haomeiling
 * @description
 * @vesion 1.0
 * @date 2018/12/28
 */

 /* 1	1	01	居民身份证	1
            2	2	02	居民户口簿	2
            3	3	03	护照	3
            4	4	04	军官证	4
            5	5	05	驾驶证	5
            6	6	06	港涣居民来往内地通行证	6
            7	7	07	台湾居民来往内地通行证	7
            8	8	99	其他法定有效证件	9
   */

public enum CertType {
    ID_CARD((short) 1, "01", "居民身份证"),
    HOUSEHOLD_REGISTERS((short) 2, "02", "居民户口簿"),
    PASSPORT((short) 3, "03", "护照"),
    OFFICIAL_CARD((short) 4, "04", "军官证"),
    DRIVING_LICENCE((short) 5, "05", "驾驶证"),
    HK((short) 6, "06", "港涣居民来往内地通行证"),
    TW((short) 7, "07", "台湾居民来往内地通行证"),
    OTHER((short) 8, "99", "其他法定有效证件");


    private short certTypeId;//数据库ID
    private String certTypeCode; //编码
    private String certTypeName;//名称

    CertType(short certTypeId, String certTypeCode, String certTypeName) {
        this.certTypeId = certTypeId;
        this.certTypeCode = certTypeCode;
        this.certTypeName = certTypeName;
    }

    public short getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(short certTypeId) {
        this.certTypeId = certTypeId;
    }

    public String getCertTypeCode() {
        return certTypeCode;
    }

    public void setCertTypeCode(String certTypeCode) {
        this.certTypeCode = certTypeCode;
    }

    public String getCertTypeName() {
        return certTypeName;
    }

    public void setCertTypeName(String certTypeName) {
        this.certTypeName = certTypeName;
    }
}
