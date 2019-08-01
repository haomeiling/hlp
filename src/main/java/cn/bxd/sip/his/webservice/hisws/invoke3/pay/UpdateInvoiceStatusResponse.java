
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
 *         &lt;element name="UpdateInvoiceStatusResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "updateInvoiceStatusResult"
})
@XmlRootElement(name = "UpdateInvoiceStatusResponse")
public class UpdateInvoiceStatusResponse {

    @XmlElement(name = "UpdateInvoiceStatusResult")
    protected String updateInvoiceStatusResult;

    /**
     * Gets the value of the updateInvoiceStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateInvoiceStatusResult() {
        return updateInvoiceStatusResult;
    }

    /**
     * Sets the value of the updateInvoiceStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateInvoiceStatusResult(String value) {
        this.updateInvoiceStatusResult = value;
    }

}
