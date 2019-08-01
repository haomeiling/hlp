
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registeredConfirmsExistResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registeredConfirmsExistResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registeredConfirmsExistResult" type="{http://webservice.bxd.sip.bxd.cn/}registeredConfirmsExistRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registeredConfirmsExistResponse", propOrder = {
    "registeredConfirmsExistResult"
})
public class TRegisteredConfirmsExistResponse {

    protected TRegisteredConfirmsExistRespond registeredConfirmsExistResult;

    /**
     * Gets the value of the registeredConfirmsExistResult property.
     * 
     * @return
     *     possible object is
     *     {@link TRegisteredConfirmsExistRespond }
     *     
     */
    public TRegisteredConfirmsExistRespond getRegisteredConfirmsExistResult() {
        return registeredConfirmsExistResult;
    }

    /**
     * Sets the value of the registeredConfirmsExistResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRegisteredConfirmsExistRespond }
     *     
     */
    public void setRegisteredConfirmsExistResult(TRegisteredConfirmsExistRespond value) {
        this.registeredConfirmsExistResult = value;
    }

}
