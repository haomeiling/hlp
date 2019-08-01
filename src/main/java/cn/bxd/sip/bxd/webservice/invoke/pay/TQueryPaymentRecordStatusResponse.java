
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPaymentRecordStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryPaymentRecordStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryPaymentRecordStatusResult" type="{http://webservice.bxd.sip.bxd.cn/}queryPaymentRecordStatusRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryPaymentRecordStatusResponse", propOrder = {
    "queryPaymentRecordStatusResult"
})
public class TQueryPaymentRecordStatusResponse {

    protected TQueryPaymentRecordStatusRespond queryPaymentRecordStatusResult;

    /**
     * Gets the value of the queryPaymentRecordStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link TQueryPaymentRecordStatusRespond }
     *     
     */
    public TQueryPaymentRecordStatusRespond getQueryPaymentRecordStatusResult() {
        return queryPaymentRecordStatusResult;
    }

    /**
     * Sets the value of the queryPaymentRecordStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TQueryPaymentRecordStatusRespond }
     *     
     */
    public void setQueryPaymentRecordStatusResult(TQueryPaymentRecordStatusRespond value) {
        this.queryPaymentRecordStatusResult = value;
    }

}
