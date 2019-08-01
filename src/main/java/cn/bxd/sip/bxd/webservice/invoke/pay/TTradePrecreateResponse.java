
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trade_precreateResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trade_precreateResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradePrecreateResult" type="{http://webservice.bxd.sip.bxd.cn/}tradePrecreateRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trade_precreateResponse", propOrder = {
    "tradePrecreateResult"
})
public class TTradePrecreateResponse {

    protected TTradePrecreateRespond tradePrecreateResult;

    /**
     * Gets the value of the tradePrecreateResult property.
     * 
     * @return
     *     possible object is
     *     {@link TTradePrecreateRespond }
     *     
     */
    public TTradePrecreateRespond getTradePrecreateResult() {
        return tradePrecreateResult;
    }

    /**
     * Sets the value of the tradePrecreateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTradePrecreateRespond }
     *     
     */
    public void setTradePrecreateResult(TTradePrecreateRespond value) {
        this.tradePrecreateResult = value;
    }

}
