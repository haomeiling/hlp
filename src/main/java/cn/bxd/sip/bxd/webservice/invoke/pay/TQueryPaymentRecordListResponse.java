
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPaymentRecordListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryPaymentRecordListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryPaymentRecordListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryPaymentRecordListResponse", propOrder = {
    "queryPaymentRecordListResult"
})
public class TQueryPaymentRecordListResponse {

    protected String queryPaymentRecordListResult;

    /**
     * Gets the value of the queryPaymentRecordListResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryPaymentRecordListResult() {
        return queryPaymentRecordListResult;
    }

    /**
     * Sets the value of the queryPaymentRecordListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryPaymentRecordListResult(String value) {
        this.queryPaymentRecordListResult = value;
    }

}
