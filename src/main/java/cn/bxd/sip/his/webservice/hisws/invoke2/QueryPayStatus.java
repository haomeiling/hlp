
package cn.bxd.sip.his.webservice.hisws.invoke2;

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
 *         &lt;element name="synusername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hifeeno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paytype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payrecord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicaremoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialsecurityno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overrecord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overmoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "synusername",
    "synkey",
    "hifeeno",
    "paytype",
    "payrecord",
    "paymoney",
    "medicaremoney",
    "socialsecurityno",
    "overrecord",
    "overmoney"
})
@XmlRootElement(name = "queryPayStatus")
public class QueryPayStatus {

    protected String synusername;
    protected String synkey;
    protected String hifeeno;
    protected String paytype;
    protected String payrecord;
    protected String paymoney;
    protected String medicaremoney;
    protected String socialsecurityno;
    protected String overrecord;
    protected String overmoney;

    /**
     * Gets the value of the synusername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynusername() {
        return synusername;
    }

    /**
     * Sets the value of the synusername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynusername(String value) {
        this.synusername = value;
    }

    /**
     * Gets the value of the synkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynkey() {
        return synkey;
    }

    /**
     * Sets the value of the synkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynkey(String value) {
        this.synkey = value;
    }

    /**
     * Gets the value of the hifeeno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHifeeno() {
        return hifeeno;
    }

    /**
     * Sets the value of the hifeeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHifeeno(String value) {
        this.hifeeno = value;
    }

    /**
     * Gets the value of the paytype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * Sets the value of the paytype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaytype(String value) {
        this.paytype = value;
    }

    /**
     * Gets the value of the payrecord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayrecord() {
        return payrecord;
    }

    /**
     * Sets the value of the payrecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayrecord(String value) {
        this.payrecord = value;
    }

    /**
     * Gets the value of the paymoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymoney() {
        return paymoney;
    }

    /**
     * Sets the value of the paymoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymoney(String value) {
        this.paymoney = value;
    }

    /**
     * Gets the value of the medicaremoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicaremoney() {
        return medicaremoney;
    }

    /**
     * Sets the value of the medicaremoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicaremoney(String value) {
        this.medicaremoney = value;
    }

    /**
     * Gets the value of the socialsecurityno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialsecurityno() {
        return socialsecurityno;
    }

    /**
     * Sets the value of the socialsecurityno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialsecurityno(String value) {
        this.socialsecurityno = value;
    }

    /**
     * Gets the value of the overrecord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverrecord() {
        return overrecord;
    }

    /**
     * Sets the value of the overrecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverrecord(String value) {
        this.overrecord = value;
    }

    /**
     * Gets the value of the overmoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOvermoney() {
        return overmoney;
    }

    /**
     * Sets the value of the overmoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOvermoney(String value) {
        this.overmoney = value;
    }

}
