
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCardInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCardInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getCardInfoResult" type="{http://webservice.bxd.sip.bxd.cn/}getCardInfoRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCardInfoResponse", propOrder = {
    "getCardInfoResult"
})
public class TGetCardInfoResponse {

    protected TGetCardInfoRespond getCardInfoResult;

    /**
     * Gets the value of the getCardInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link TGetCardInfoRespond }
     *     
     */
    public TGetCardInfoRespond getGetCardInfoResult() {
        return getCardInfoResult;
    }

    /**
     * Sets the value of the getCardInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TGetCardInfoRespond }
     *     
     */
    public void setGetCardInfoResult(TGetCardInfoRespond value) {
        this.getCardInfoResult = value;
    }

}
