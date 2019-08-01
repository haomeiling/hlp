
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for certificationUserResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="certificationUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificationUserResult" type="{http://webservice.bxd.sip.bxd.cn/}certificationUserRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "certificationUserResponse", propOrder = {
    "certificationUserResult"
})
public class TCertificationUserResponse {

    protected TCertificationUserRespond certificationUserResult;

    /**
     * Gets the value of the certificationUserResult property.
     * 
     * @return
     *     possible object is
     *     {@link TCertificationUserRespond }
     *     
     */
    public TCertificationUserRespond getCertificationUserResult() {
        return certificationUserResult;
    }

    /**
     * Sets the value of the certificationUserResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCertificationUserRespond }
     *     
     */
    public void setCertificationUserResult(TCertificationUserRespond value) {
        this.certificationUserResult = value;
    }

}
