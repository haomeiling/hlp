
package cn.bxd.sip.his.webservice.hisws.invoke3.hisuser;

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
 *         &lt;element name="cardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialsecurityNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitCardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cardNo",
    "socialsecurityNO",
    "bankCardNumber",
    "visitCardNo"
})
@XmlRootElement(name = "updatePatientInfo")
public class UpdatePatientInfo {

    protected String synUserName;
    protected String synKey;
    protected String cardNo;
    protected String socialsecurityNO;
    protected String bankCardNumber;
    protected String visitCardNo;

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
     * Gets the value of the cardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * Sets the value of the cardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNo(String value) {
        this.cardNo = value;
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
     * Gets the value of the visitCardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitCardNo() {
        return visitCardNo;
    }

    /**
     * Sets the value of the visitCardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitCardNo(String value) {
        this.visitCardNo = value;
    }

}
