
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wipe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wipe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="civilServantMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="declareMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="largeMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicalType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicareMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overalMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wipedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wipedMoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wipe", propOrder = {
    "civilServantMoney",
    "declareMoney",
    "largeMoney",
    "medicalType",
    "medicareMoney",
    "otherMoney",
    "overalMoney",
    "wipedDate",
    "wipedMoney"
})
public class Wipe {

    @XmlElement(namespace = "")
    protected String civilServantMoney;
    @XmlElement(namespace = "")
    protected String declareMoney;
    @XmlElement(namespace = "")
    protected String largeMoney;
    @XmlElement(namespace = "")
    protected String medicalType;
    @XmlElement(namespace = "")
    protected String medicareMoney;
    @XmlElement(namespace = "")
    protected String otherMoney;
    @XmlElement(namespace = "")
    protected String overalMoney;
    @XmlElement(namespace = "")
    protected String wipedDate;
    @XmlElement(namespace = "")
    protected String wipedMoney;

    /**
     * Gets the value of the civilServantMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivilServantMoney() {
        return civilServantMoney;
    }

    /**
     * Sets the value of the civilServantMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivilServantMoney(String value) {
        this.civilServantMoney = value;
    }

    /**
     * Gets the value of the declareMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeclareMoney() {
        return declareMoney;
    }

    /**
     * Sets the value of the declareMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeclareMoney(String value) {
        this.declareMoney = value;
    }

    /**
     * Gets the value of the largeMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLargeMoney() {
        return largeMoney;
    }

    /**
     * Sets the value of the largeMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLargeMoney(String value) {
        this.largeMoney = value;
    }

    /**
     * Gets the value of the medicalType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicalType() {
        return medicalType;
    }

    /**
     * Sets the value of the medicalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicalType(String value) {
        this.medicalType = value;
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
     * Gets the value of the otherMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherMoney() {
        return otherMoney;
    }

    /**
     * Sets the value of the otherMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherMoney(String value) {
        this.otherMoney = value;
    }

    /**
     * Gets the value of the overalMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOveralMoney() {
        return overalMoney;
    }

    /**
     * Sets the value of the overalMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOveralMoney(String value) {
        this.overalMoney = value;
    }

    /**
     * Gets the value of the wipedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWipedDate() {
        return wipedDate;
    }

    /**
     * Sets the value of the wipedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWipedDate(String value) {
        this.wipedDate = value;
    }

    /**
     * Gets the value of the wipedMoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWipedMoney() {
        return wipedMoney;
    }

    /**
     * Sets the value of the wipedMoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWipedMoney(String value) {
        this.wipedMoney = value;
    }

}
