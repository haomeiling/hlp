
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerConfirmsExistResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerConfirmsExistResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registerConfirmsExistResult" type="{http://webservice.bxd.sip.bxd.cn/}registerConfirmsExistRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerConfirmsExistResponse", propOrder = {
    "registerConfirmsExistResult"
})
public class TRegisterConfirmsExistResponse {

    protected TRegisterConfirmsExistRespond registerConfirmsExistResult;

    /**
     * Gets the value of the registerConfirmsExistResult property.
     * 
     * @return
     *     possible object is
     *     {@link TRegisterConfirmsExistRespond }
     *     
     */
    public TRegisterConfirmsExistRespond getRegisterConfirmsExistResult() {
        return registerConfirmsExistResult;
    }

    /**
     * Sets the value of the registerConfirmsExistResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRegisterConfirmsExistRespond }
     *     
     */
    public void setRegisterConfirmsExistResult(TRegisterConfirmsExistRespond value) {
        this.registerConfirmsExistResult = value;
    }

}
