
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentRecordStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentRecordStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hiFeeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isPrint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentRecordStatus", propOrder = {
    "hiFeeNo",
    "isPrint",
    "payStatus"
})
public class TPaymentRecordStatus {

    @XmlElement(namespace = "")
    protected String hiFeeNo;
    @XmlElement(namespace = "")
    protected String isPrint;
    @XmlElement(namespace = "")
    protected String payStatus;

    /**
     * Gets the value of the hiFeeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHiFeeNo() {
        return hiFeeNo;
    }

    /**
     * Sets the value of the hiFeeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHiFeeNo(String value) {
        this.hiFeeNo = value;
    }

    /**
     * Gets the value of the isPrint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPrint() {
        return isPrint;
    }

    /**
     * Sets the value of the isPrint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPrint(String value) {
        this.isPrint = value;
    }

    /**
     * Gets the value of the payStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * Sets the value of the payStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayStatus(String value) {
        this.payStatus = value;
    }

}
