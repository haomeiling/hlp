
package cn.bxd.sip.his.webservice.hisws.invoke2;

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
 *         &lt;element name="synusername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "synusername",
    "synkey"
})
@XmlRootElement(name = "queryDepartmentList")
public class QueryDepartmentList {

    protected String synusername;
    protected String synkey;

    /**
     * Gets the value of the synusername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynusername() {
        return synusername;
    }

    /**
     * Sets the value of the synusername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynusername(String value) {
        this.synusername = value;
    }

    /**
     * Gets the value of the synkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynkey() {
        return synkey;
    }

    /**
     * Sets the value of the synkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynkey(String value) {
        this.synkey = value;
    }

}
