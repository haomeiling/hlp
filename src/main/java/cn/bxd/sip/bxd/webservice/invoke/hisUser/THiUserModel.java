
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hiUserModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hiUserModel">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balanceSpecified" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clinicNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicareNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientBirthday" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientIdcardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientSex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="patientSexSpecified" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialsecurityNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "THiUserModel", propOrder = {
    "address",
    "balance",
    "balanceSpecified",
    "bankCardNumber",
    "clinicNo",
    "doType",
    "extend",
    "medicareNO",
    "passWord",
    "patientBirthday",
    "patientIdcardNo",
    "patientName",
    "patientNo",
    "patientSex",
    "patientSexSpecified",
    "patientTelephone",
    "payAmount",
    "payNo",
    "payType",
    "personCategory",
    "socialsecurityNO"
})
public class THiUserModel
    extends TBaseRespond
{

    @XmlElement(namespace = "")
    protected String address;
    @XmlElement(namespace = "")
    protected String balance;
    @XmlElement(namespace = "")
    protected String balanceSpecified;
    @XmlElement(namespace = "")
    protected String bankCardNumber;
    @XmlElement(namespace = "")
    protected String clinicNo;
    @XmlElement(namespace = "")
    protected String doType;
    @XmlElement(namespace = "")
    protected String extend;
    @XmlElement(namespace = "")
    protected String medicareNO;
    @XmlElement(namespace = "")
    protected String passWord;
    @XmlElement(namespace = "")
    protected String patientBirthday;
    @XmlElement(namespace = "")
    protected String patientIdcardNo;
    @XmlElement(namespace = "")
    protected String patientName;
    @XmlElement(namespace = "")
    protected String patientNo;
    @XmlElement(namespace = "")
    protected Integer patientSex;
    @XmlElement(namespace = "")
    protected String patientSexSpecified;
    @XmlElement(namespace = "")
    protected String patientTelephone;
    @XmlElement(namespace = "")
    protected String payAmount;
    @XmlElement(namespace = "")
    protected String payNo;
    @XmlElement(namespace = "")
    protected String payType;
    @XmlElement(namespace = "")
    protected String personCategory;
    @XmlElement(namespace = "")
    protected String socialsecurityNO;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalance(String value) {
        this.balance = value;
    }

    /**
     * Gets the value of the balanceSpecified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalanceSpecified() {
        return balanceSpecified;
    }

    /**
     * Sets the value of the balanceSpecified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalanceSpecified(String value) {
        this.balanceSpecified = value;
    }

    /**
     * Gets the value of the bankCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCardNumber() {
        return bankCardNumber;
    }

    /**
     * Sets the value of the bankCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCardNumber(String value) {
        this.bankCardNumber = value;
    }

    /**
     * Gets the value of the clinicNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicNo() {
        return clinicNo;
    }

    /**
     * Sets the value of the clinicNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicNo(String value) {
        this.clinicNo = value;
    }

    /**
     * Gets the value of the doType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoType() {
        return doType;
    }

    /**
     * Sets the value of the doType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoType(String value) {
        this.doType = value;
    }

    /**
     * Gets the value of the extend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtend() {
        return extend;
    }

    /**
     * Sets the value of the extend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtend(String value) {
        this.extend = value;
    }

    /**
     * Gets the value of the medicareNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicareNO() {
        return medicareNO;
    }

    /**
     * Sets the value of the medicareNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicareNO(String value) {
        this.medicareNO = value;
    }

    /**
     * Gets the value of the passWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the value of the passWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassWord(String value) {
        this.passWord = value;
    }

    /**
     * Gets the value of the patientBirthday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientBirthday() {
        return patientBirthday;
    }

    /**
     * Sets the value of the patientBirthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientBirthday(String value) {
        this.patientBirthday = value;
    }

    /**
     * Gets the value of the patientIdcardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientIdcardNo() {
        return patientIdcardNo;
    }

    /**
     * Sets the value of the patientIdcardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientIdcardNo(String value) {
        this.patientIdcardNo = value;
    }

    /**
     * Gets the value of the patientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Sets the value of the patientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientName(String value) {
        this.patientName = value;
    }

    /**
     * Gets the value of the patientNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientNo() {
        return patientNo;
    }

    /**
     * Sets the value of the patientNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientNo(String value) {
        this.patientNo = value;
    }

    /**
     * Gets the value of the patientSex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPatientSex() {
        return patientSex;
    }

    /**
     * Sets the value of the patientSex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPatientSex(Integer value) {
        this.patientSex = value;
    }

    /**
     * Gets the value of the patientSexSpecified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientSexSpecified() {
        return patientSexSpecified;
    }

    /**
     * Sets the value of the patientSexSpecified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientSexSpecified(String value) {
        this.patientSexSpecified = value;
    }

    /**
     * Gets the value of the patientTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientTelephone() {
        return patientTelephone;
    }

    /**
     * Sets the value of the patientTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientTelephone(String value) {
        this.patientTelephone = value;
    }

    /**
     * Gets the value of the payAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayAmount() {
        return payAmount;
    }

    /**
     * Sets the value of the payAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayAmount(String value) {
        this.payAmount = value;
    }

    /**
     * Gets the value of the payNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayNo() {
        return payNo;
    }

    /**
     * Sets the value of the payNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayNo(String value) {
        this.payNo = value;
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
     * Gets the value of the personCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonCategory() {
        return personCategory;
    }

    /**
     * Sets the value of the personCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonCategory(String value) {
        this.personCategory = value;
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

}
