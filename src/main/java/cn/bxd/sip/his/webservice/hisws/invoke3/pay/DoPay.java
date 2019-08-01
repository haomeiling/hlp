
package cn.bxd.sip.his.webservice.hisws.invoke3.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="synUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hiFeeNos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payRecord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicareMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialsecurityNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overRecord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicareReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankReturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicareType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "synUserName",
    "synKey",
    "hiFeeNos",
    "payType",
    "payRecord",
    "payMoney",
    "medicareMoney",
    "socialsecurityNO",
    "overRecord",
    "overMoney",
    "medicareReturn",
    "bankReturn",
    "terminalCode",
    "userNo",
    "medicareType"
})
@XmlRootElement(name = "doPay")
public class DoPay {

    protected String synUserName;
    protected String synKey;
    protected String hiFeeNos;
    protected String payType;
    protected String payRecord;
    protected String payMoney;
    protected String medicareMoney;
    protected String socialsecurityNO;
    protected String overRecord;
    protected String overMoney;
    protected String medicareReturn;
    protected String bankReturn;
    protected String terminalCode;
    protected String userNo;
    protected String medicareType;

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
     * Gets the value of the hiFeeNos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHiFeeNos() {
        return hiFeeNos;
    }

    /**
     * Sets the value of the hiFeeNos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHiFeeNos(String value) {
        this.hiFeeNos = value;
    }

    /**
     * Gets the value of the payType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayType() {
        return payType;
    }

    /**
     * Sets the value of the payType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayType(String value) {
        this.payType = value;
    }

    /**
     * Gets the value of the payRecord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayRecord() {
        return payRecord;
    }

    /**
     * Sets the value of the payRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayRecord(String value) {
        this.payRecord = value;
    }

    /**
     * Gets the value of the payMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayMoney() {
        return payMoney;
    }

    /**
     * Sets the value of the payMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayMoney(String value) {
        this.payMoney = value;
    }

    /**
     * Gets the value of the medicareMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicareMoney() {
        return medicareMoney;
    }

    /**
     * Sets the value of the medicareMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicareMoney(String value) {
        this.medicareMoney = value;
    }

    /**
     * Gets the value of the socialsecurityNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialsecurityNO() {
        return socialsecurityNO;
    }

    /**
     * Sets the value of the socialsecurityNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialsecurityNO(String value) {
        this.socialsecurityNO = value;
    }

    /**
     * Gets the value of the overRecord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverRecord() {
        return overRecord;
    }

    /**
     * Sets the value of the overRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverRecord(String value) {
        this.overRecord = value;
    }

    /**
     * Gets the value of the overMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverMoney() {
        return overMoney;
    }

    /**
     * Sets the value of the overMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverMoney(String value) {
        this.overMoney = value;
    }

    /**
     * Gets the value of the medicareReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicareReturn() {
        return medicareReturn;
    }

    /**
     * Sets the value of the medicareReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicareReturn(String value) {
        this.medicareReturn = value;
    }

    /**
     * Gets the value of the bankReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankReturn() {
        return bankReturn;
    }

    /**
     * Sets the value of the bankReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankReturn(String value) {
        this.bankReturn = value;
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

}
