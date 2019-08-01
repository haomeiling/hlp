
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preDoPayResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="preDoPayResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preDoPayResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preDoPayResponse", propOrder = {
    "preDoPayResult"
})
public class TPreDoPayResponse {

    protected String preDoPayResult;

    /**
     * Gets the value of the preDoPayResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreDoPayResult() {
        return preDoPayResult;
    }

    /**
     * Sets the value of the preDoPayResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreDoPayResult(String value) {
        this.preDoPayResult = value;
    }

}
