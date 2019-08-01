
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUserNoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUserNoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getUserNoResult" type="{http://webservice.bxd.sip.bxd.cn/}getUserNoRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserNoResponse", propOrder = {
    "getUserNoResult"
})
public class TGetUserNoResponse {

    protected TGetUserNoRespond getUserNoResult;

    /**
     * Gets the value of the getUserNoResult property.
     * 
     * @return
     *     possible object is
     *     {@link TGetUserNoRespond }
     *     
     */
    public TGetUserNoRespond getGetUserNoResult() {
        return getUserNoResult;
    }

    /**
     * Sets the value of the getUserNoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TGetUserNoRespond }
     *     
     */
    public void setGetUserNoResult(TGetUserNoRespond value) {
        this.getUserNoResult = value;
    }

}
