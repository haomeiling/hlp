package cn.bxd.sip.bxd.model.dto.pay;

/**
 * Description: TODO
 * Package: com.bxd.medicalinsurance.model.pay
 *
 * @author Leeves
 * @date 2018-01-11
 */

public class TokenReq {

    private String accountNo;
    private String accountSecret;
    private String sign;

    @Override
    public String toString() {
        return "TokenReq{" +
                "accountNo='" + accountNo + '\'' +
                ", accountSecret='" + accountSecret + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public TokenReq() {
    }
    
    public TokenReq(String accountNo, String accountSecret) {
    	this.accountNo = accountNo;
        this.accountSecret = accountSecret;
    }

    public TokenReq(String accountNo, String accountSecret, String sign) {
        this.accountNo = accountNo;
        this.accountSecret = accountSecret;
        this.sign = sign;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountSecret() {
        return accountSecret;
    }

    public void setAccountSecret(String accountSecret) {
        this.accountSecret = accountSecret;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
