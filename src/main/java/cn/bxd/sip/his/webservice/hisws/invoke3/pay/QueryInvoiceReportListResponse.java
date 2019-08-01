
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
 *         &lt;element name="QueryInvoiceReportListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryInvoiceReportListResult"
})
@XmlRootElement(name = "QueryInvoiceReportListResponse")
public class QueryInvoiceReportListResponse {

    @XmlElement(name = "QueryInvoiceReportListResult")
    protected String queryInvoiceReportListResult;

    /**
     * Gets the value of the queryInvoiceReportListResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryInvoiceReportListResult() {
        return queryInvoiceReportListResult;
    }

    /**
     * Sets the value of the queryInvoiceReportListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryInvoiceReportListResult(String value) {
        this.queryInvoiceReportListResult = value;
    }

}
