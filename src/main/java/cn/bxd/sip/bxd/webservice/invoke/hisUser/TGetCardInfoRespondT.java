
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCardInfoRespond complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCardInfoRespond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="cardInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCardInfoRespond", propOrder = {
    "cardInfo"
})
public class TGetCardInfoRespondT
    extends TBaseRespond
{

    @XmlElement(namespace = "")
    protected String cardInfo;

    /**
     * Gets the value of the cardInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardInfo() {
        return cardInfo;
    }

    /**
     * Sets the value of the cardInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardInfo(String value) {
        this.cardInfo = value;
    }

}
