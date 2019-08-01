
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSMSCodeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSMSCodeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getSMSCodeResult" type="{http://webservice.bxd.sip.bxd.cn/}getSMSCodeRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSMSCodeResponse", propOrder = {
    "getSMSCodeResult"
})
public class TGetSMSCodeResponse {

    protected TGetSMSCodeRespond getSMSCodeResult;

    /**
     * Gets the value of the getSMSCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link TGetSMSCodeRespond }
     *     
     */
    public TGetSMSCodeRespond getGetSMSCodeResult() {
        return getSMSCodeResult;
    }

    /**
     * Sets the value of the getSMSCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TGetSMSCodeRespond }
     *     
     */
    public void setGetSMSCodeResult(TGetSMSCodeRespond value) {
        this.getSMSCodeResult = value;
    }

}
