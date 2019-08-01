
package cn.bxd.sip.his.webservice.hisws.invoke3.inhos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inHosDailyResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "inHosDailyResult"
})
@XmlRootElement(name = "inHosDailyResponse")
public class InHosDailyResponse {

    protected String inHosDailyResult;

    /**
     * Gets the value of the inHosDailyResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInHosDailyResult() {
        return inHosDailyResult;
    }

    /**
     * Sets the value of the inHosDailyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInHosDailyResult(String value) {
        this.inHosDailyResult = value;
    }

}
