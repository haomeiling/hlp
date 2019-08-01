package cn.bxd.sip.bxd.model.dto.pay;

/**
 * Description: TODO
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-02-05
 */

public class AppPayData {

    /** 应用ID */
    private String appid;

    /** 商户号 */
    private String partnerid;

    /** 预支付交易会话ID */
    private String prepayid;

    /** 扩展字段:暂填写固定值Sign=WXPay */
    private String packageValue;

    /** 随机字符串 */
    private String noncestr;

    /** 时间戳 */
    private long timestamp;

    /** 签名 */
    private String sign;

    @Override
    public String toString() {
        return "AppPayData{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", packageValue='" + packageValue + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
