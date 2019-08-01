
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOverallAreaRespond complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOverallAreaRespond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="overallArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOverallAreaRespond", propOrder = {
    "overallArea"
})
public class TGetOverallAreaRespond
    extends TBaseRespond
{

    @XmlElement(namespace = "")
    protected String overallArea;

    /**
     * Gets the value of the overallArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverallArea() {
        return overallArea;
    }

    /**
     * Sets the value of the overallArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverallArea(String value) {
        this.overallArea = value;
    }

}
