package cn.bxd.sip.bxd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 终端配置
 * Description:
 * User: HaoMeiLing
 * Date: 2018-10-09
 * Time: 13:47
 */

@Component
@ConfigurationProperties(prefix = "terminal")
public class YmlTerminalConfig {
    String hisUserQualifier;
    String payQualifier;
    String regQualifier;
    String inHosQualifier;
    String commonQualifier;

    //服务器地址
    String baseUrl;
    String hisUserUrl;
    String payUrl;
    String regUrl;
    String inHosUrl;
    String commonUrl;


    public String getHisUserQualifier() {
        return hisUserQualifier;
    }

    public void setHisUserQualifier(String hisUserQualifier) {
        this.hisUserQualifier = hisUserQualifier;
    }

    public String getPayQualifier() {
        return payQualifier;
    }

    public void setPayQualifier(String payQualifier) {
        this.payQualifier = payQualifier;
    }

    public String getRegQualifier() {
        return regQualifier;
    }

    public void setRegQualifier(String regQualifier) {
        this.regQualifier = regQualifier;
    }

    public String getInHosQualifier() {
        return inHosQualifier;
    }

    public void setInHosQualifier(String inHosQualifier) {
        this.inHosQualifier = inHosQualifier;
    }

    public String getCommonQualifier() {
        return commonQualifier;
    }

    public void setCommonQualifier(String commonQualifier) {
        this.commonQualifier = commonQualifier;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getHisUserUrl() {
        return this.baseUrl +hisUserUrl;
    }

    public void setHisUserUrl(String hisUserUrl) {
        this.hisUserUrl = hisUserUrl;
    }

    public String getPayUrl() {
        return this.baseUrl +payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getRegUrl() {
        return this.baseUrl +regUrl;
    }

    public void setRegUrl(String regUrl) {
        this.regUrl = regUrl;
    }

    public String getInHosUrl() {
        return this.baseUrl +inHosUrl;
    }

    public void setInHosUrl(String inHosUrl) {
        this.inHosUrl = inHosUrl;
    }

    public String getCommonUrl() {
        return this.baseUrl +commonUrl;
    }

    public void setCommonUrl(String commonUrl) {
        this.commonUrl = commonUrl;
    }
}
