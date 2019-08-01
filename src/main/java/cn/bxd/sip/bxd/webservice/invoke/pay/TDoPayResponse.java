
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doPayResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doPayResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="doPayResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doPayResponse", propOrder = {
    "doPayResult"
})
public class TDoPayResponse {

    protected String doPayResult;

    /**
     * Gets the value of the doPayResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoPayResult() {
        return doPayResult;
    }

    /**
     * Sets the value of the doPayResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoPayResult(String value) {
        this.doPayResult = value;
    }

}
