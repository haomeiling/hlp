
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tradeCloseRespond complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tradeCloseRespond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="closeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tradeCloseRespond", propOrder = {
    "closeStatus"
})
public class TTradeCloseRespond
    extends TBaseRespond
{

    @XmlElement(namespace = "")
    protected String closeStatus;

    /**
     * Gets the value of the closeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCloseStatus() {
        return closeStatus;
    }

    /**
     * Sets the value of the closeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCloseStatus(String value) {
        this.closeStatus = value;
    }

}
