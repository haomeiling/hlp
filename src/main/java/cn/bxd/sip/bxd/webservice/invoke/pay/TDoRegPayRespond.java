
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doRegPayRespond complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doRegPayRespond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="resultmessage" type="{http://webservice.bxd.sip.bxd.cn/}doRegPayMgs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doRegPayRespond", propOrder = {
    "resultmessage"
})
public class TDoRegPayRespond
    extends TBaseRespond
{

    @XmlElement(namespace = "")
    protected TDoRegPayMgs resultmessage;

    /**
     * Gets the value of the resultmessage property.
     * 
     * @return
     *     possible object is
     *     {@link TDoRegPayMgs }
     *     
     */
    public TDoRegPayMgs getResultmessage() {
        return resultmessage;
    }

    /**
     * Sets the value of the resultmessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDoRegPayMgs }
     *     
     */
    public void setResultmessage(TDoRegPayMgs value) {
        this.resultmessage = value;
    }

}
