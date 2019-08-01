
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for makeRegProductOrderResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="makeRegProductOrderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="makeRegProductOrderResult" type="{http://webservice.bxd.sip.bxd.cn/}makeProductOrderRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "makeRegProductOrderResponse", propOrder = {
    "makeRegProductOrderResult"
})
public class TMakeRegProductOrderResponse {

    protected TMakeProductOrderRespond makeRegProductOrderResult;

    /**
     * Gets the value of the makeRegProductOrderResult property.
     * 
     * @return
     *     possible object is
     *     {@link TMakeProductOrderRespond }
     *     
     */
    public TMakeProductOrderRespond getMakeRegProductOrderResult() {
        return makeRegProductOrderResult;
    }

    /**
     * Sets the value of the makeRegProductOrderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TMakeProductOrderRespond }
     *     
     */
    public void setMakeRegProductOrderResult(TMakeProductOrderRespond value) {
        this.makeRegProductOrderResult = value;
    }

}
