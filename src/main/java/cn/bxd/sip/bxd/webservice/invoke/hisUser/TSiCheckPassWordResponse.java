
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for siCheckPassWordResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="siCheckPassWordResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="siCheckPassWordResult" type="{http://webservice.bxd.sip.bxd.cn/}siCheckPassWordRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "siCheckPassWordResponse", propOrder = {
    "siCheckPassWordResult"
})
public class TSiCheckPassWordResponse {

    protected TSiCheckPassWordRespond siCheckPassWordResult;

    /**
     * Gets the value of the siCheckPassWordResult property.
     * 
     * @return
     *     possible object is
     *     {@link TSiCheckPassWordRespond }
     *     
     */
    public TSiCheckPassWordRespond getSiCheckPassWordResult() {
        return siCheckPassWordResult;
    }

    /**
     * Sets the value of the siCheckPassWordResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSiCheckPassWordRespond }
     *     
     */
    public void setSiCheckPassWordResult(TSiCheckPassWordRespond value) {
        this.siCheckPassWordResult = value;
    }

}
