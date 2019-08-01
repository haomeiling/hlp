
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doRegPayMgs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doRegPayMgs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clearingNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispensaryWin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="guidelinesInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reserve" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doRegPayMgs", propOrder = {
    "clearingNO",
    "dispensaryWin",
    "guidelinesInfo",
    "medicalCode",
    "reserve",
    "transactionTime"
})
public class TDoRegPayMgs {

    @XmlElement(namespace = "")
    protected String clearingNO;
    @XmlElement(namespace = "")
    protected String dispensaryWin;
    @XmlElement(namespace = "")
    protected String guidelinesInfo;
    @XmlElement(namespace = "")
    protected String medicalCode;
    @XmlElement(namespace = "")
    protected String reserve;
    @XmlElement(namespace = "")
    protected String transactionTime;

    /**
     * Gets the value of the clearingNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearingNO() {
        return clearingNO;
    }

    /**
     * Sets the value of the clearingNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearingNO(String value) {
        this.clearingNO = value;
    }

    /**
     * Gets the value of the dispensaryWin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensaryWin() {
        return dispensaryWin;
    }

    /**
     * Sets the value of the dispensaryWin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensaryWin(String value) {
        this.dispensaryWin = value;
    }

    /**
     * Gets the value of the guidelinesInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuidelinesInfo() {
        return guidelinesInfo;
    }

    /**
     * Sets the value of the guidelinesInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuidelinesInfo(String value) {
        this.guidelinesInfo = value;
    }

    /**
     * Gets the value of the medicalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicalCode() {
        return medicalCode;
    }

    /**
     * Sets the value of the medicalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicalCode(String value) {
        this.medicalCode = value;
    }

    /**
     * Gets the value of the reserve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReserve() {
        return reserve;
    }

    /**
     * Sets the value of the reserve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReserve(String value) {
        this.reserve = value;
    }

    /**
     * Gets the value of the transactionTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTime() {
        return transactionTime;
    }

    /**
     * Sets the value of the transactionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTime(String value) {
        this.transactionTime = value;
    }

}
