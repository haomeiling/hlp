
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for yibaoFallbackResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="yibaoFallbackResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="yibaoFallbackResult" type="{http://webservice.bxd.sip.bxd.cn/}baseRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "yibaoFallbackResponse", propOrder = {
    "yibaoFallbackResult"
})
public class TYibaoFallbackResponse {

    protected TBaseRespond yibaoFallbackResult;

    /**
     * Gets the value of the yibaoFallbackResult property.
     * 
     * @return
     *     possible object is
     *     {@link TBaseRespond }
     *     
     */
    public TBaseRespond getYibaoFallbackResult() {
        return yibaoFallbackResult;
    }

    /**
     * Sets the value of the yibaoFallbackResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBaseRespond }
     *     
     */
    public void setYibaoFallbackResult(TBaseRespond value) {
        this.yibaoFallbackResult = value;
    }

}
