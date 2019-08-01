
package cn.bxd.sip.bxd.webservice.client.msgws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userMessageQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userMessageQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="query_json" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userMessageQuery", propOrder = {
    "queryJson"
})
public class UserMessageQuery {

    @XmlElement(name = "query_json")
    protected String queryJson;

    /**
     * Gets the value of the queryJson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryJson() {
        return queryJson;
    }

    /**
     * Sets the value of the queryJson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryJson(String value) {
        this.queryJson = value;
    }

}
