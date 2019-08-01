
package cn.bxd.sip.bxd.webservice.client.msgws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendSmsMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendSmsMsg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jsonmsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSmsMsg", propOrder = {
    "jsonmsg"
})
public class SendSmsMsg {

    protected String jsonmsg;

    /**
     * Gets the value of the jsonmsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsonmsg() {
        return jsonmsg;
    }

    /**
     * Sets the value of the jsonmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsonmsg(String value) {
        this.jsonmsg = value;
    }

}
