
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registterUserResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registterUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registterUserResult" type="{http://webservice.bxd.sip.bxd.cn/}registterUserRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registterUserResponse", propOrder = {
    "registterUserResult"
})
public class TRegisterUserResponse {

    protected TRegisterUserRespond registterUserResult;

    /**
     * Gets the value of the registterUserResult property.
     * 
     * @return
     *     possible object is
     *     {@link TRegisterUserRespond }
     *     
     */
    public TRegisterUserRespond getRegistterUserResult() {
        return registterUserResult;
    }

    /**
     * Sets the value of the registterUserResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRegisterUserRespond }
     *     
     */
    public void setRegistterUserResult(TRegisterUserRespond value) {
        this.registterUserResult = value;
    }

}
