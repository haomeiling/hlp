
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tradePrecreateRespond complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tradePrecreateRespond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="qrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tradePrecreateRespond", propOrder = {
    "qrCode"
})
public class TTradePrecreateRespond
    extends TBaseRespond
{

    @XmlElement(namespace = "")
    protected String qrCode;

    /**
     * Gets the value of the qrCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Sets the value of the qrCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrCode(String value) {
        this.qrCode = value;
    }

}
