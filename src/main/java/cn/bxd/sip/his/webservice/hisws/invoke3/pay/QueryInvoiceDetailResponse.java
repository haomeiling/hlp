
package cn.bxd.sip.his.webservice.hisws.invoke3.pay;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="QueryInvoiceDetailResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryInvoiceDetailResult"
})
@XmlRootElement(name = "QueryInvoiceDetailResponse")
public class QueryInvoiceDetailResponse {

    @XmlElement(name = "QueryInvoiceDetailResult")
    protected String queryInvoiceDetailResult;

    /**
     * Gets the value of the queryInvoiceDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryInvoiceDetailResult() {
        return queryInvoiceDetailResult;
    }

    /**
     * Sets the value of the queryInvoiceDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryInvoiceDetailResult(String value) {
        this.queryInvoiceDetailResult = value;
    }

}
