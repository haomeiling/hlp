
package cn.bxd.sip.his.webservice.hisws.invoke3.inhos;

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
 *         &lt;element name="payDetailResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "payDetailResult"
})
@XmlRootElement(name = "payDetailResponse")
public class PayDetailResponse {

    protected String payDetailResult;

    /**
     * Gets the value of the payDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayDetailResult() {
        return payDetailResult;
    }

    /**
     * Sets the value of the payDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayDetailResult(String value) {
        this.payDetailResult = value;
    }

}
