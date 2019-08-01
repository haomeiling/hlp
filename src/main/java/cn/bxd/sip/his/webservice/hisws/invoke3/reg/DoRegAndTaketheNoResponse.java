
package cn.bxd.sip.his.webservice.hisws.invoke3.reg;

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
 *         &lt;element name="doRegAndTaketheNoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "doRegAndTaketheNoResult"
})
@XmlRootElement(name = "doRegAndTaketheNoResponse")
public class DoRegAndTaketheNoResponse {

    protected String doRegAndTaketheNoResult;

    /**
     * Gets the value of the doRegAndTaketheNoResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoRegAndTaketheNoResult() {
        return doRegAndTaketheNoResult;
    }

    /**
     * Sets the value of the doRegAndTaketheNoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoRegAndTaketheNoResult(String value) {
        this.doRegAndTaketheNoResult = value;
    }

}
