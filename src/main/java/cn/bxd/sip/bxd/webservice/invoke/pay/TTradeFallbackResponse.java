
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tradeFallbackResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tradeFallbackResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeFallbackResult" type="{http://webservice.bxd.sip.bxd.cn/}baseRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tradeFallbackResponse", propOrder = {
    "tradeFallbackResult"
})
public class TTradeFallbackResponse {

    protected TBaseRespond tradeFallbackResult;

    /**
     * Gets the value of the tradeFallbackResult property.
     * 
     * @return
     *     possible object is
     *     {@link TBaseRespond }
     *     
     */
    public TBaseRespond getTradeFallbackResult() {
        return tradeFallbackResult;
    }

    /**
     * Sets the value of the tradeFallbackResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBaseRespond }
     *     
     */
    public void setTradeFallbackResult(TBaseRespond value) {
        this.tradeFallbackResult = value;
    }

}
