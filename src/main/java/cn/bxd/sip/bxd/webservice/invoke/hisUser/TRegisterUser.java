
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registterUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registterUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="synUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="synKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="terminalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="hospitalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="mobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="THiUser" type="{http://webservice.bxd.sip.bxd.cn/}THiUser" minOccurs="0" form="qualified"/>
 *         &lt;element name="userNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="medicareType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="cardinfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="overallArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="bankCardInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registterUser", propOrder = {
    "synUserName",
    "synKey",
    "terminalCode",
    "hospitalId",
    "mobileNo",
    "password",
        "THiUser",
    "userNo",
    "medicareType",
    "cardinfo",
    "overallArea",
    "bankCardInfo"
})
public class TRegisterUser {

    protected String synUserName;
    protected String synKey;
    protected String terminalCode;
    protected String hospitalId;
    protected String mobileNo;
    protected String password;
    protected THiUser THiUser;
    protected String userNo;
    protected String medicareType;
    protected String cardinfo;
    protected String overallArea;
    protected String bankCardInfo;

    /**
     * Gets the value of the synUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynUserName() {
        return synUserName;
    }

    /**
     * Sets the value of the synUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynUserName(String value) {
        this.synUserName = value;
    }

    /**
     * Gets the value of the synKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynKey() {
        return synKey;
    }

    /**
     * Sets the value of the synKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynKey(String value) {
        this.synKey = value;
    }

    /**
     * Gets the value of the terminalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * Sets the value of the terminalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalCode(String value) {
        this.terminalCode = value;
    }

    /**
     * Gets the value of the hospitalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHospitalId() {
        return hospitalId;
    }

    /**
     * Sets the value of the hospitalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHospitalId(String value) {
        this.hospitalId = value;
    }

    /**
     * Gets the value of the mobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNo(String value) {
        this.mobileNo = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the THiUser property.
     * 
     * @return
     *     possible object is
     *     {@link THiUser }
     *     
     */
    public THiUser getTHiUser() {
        return THiUser;
    }

    /**
     * Sets the value of the THiUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link THiUser }
     *     
     */
    public void setTHiUser(THiUser value) {
        this.THiUser = value;
    }

    /**
     * Gets the value of the userNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * Sets the value of the userNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNo(String value) {
        this.userNo = value;
    }

    /**
     * Gets the value of the medicareType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicareType() {
        return medicareType;
    }

    /**
     * Sets the value of the medicareType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicareType(String value) {
        this.medicareType = value;
    }

    /**
     * Gets the value of the cardinfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardinfo() {
        return cardinfo;
    }

    /**
     * Sets the value of the cardinfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardinfo(String value) {
        this.cardinfo = value;
    }

    /**
     * Gets the value of the overallArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverallArea() {
        return overallArea;
    }

    /**
     * Sets the value of the overallArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverallArea(String value) {
        this.overallArea = value;
    }

    /**
     * Gets the value of the bankCardInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCardInfo() {
        return bankCardInfo;
    }

    /**
     * Sets the value of the bankCardInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCardInfo(String value) {
        this.bankCardInfo = value;
    }

}
