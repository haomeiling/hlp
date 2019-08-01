
package cn.bxd.sip.bxd.webservice.invoke.hisUser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOverallAreaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOverallAreaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOverallAreaResult" type="{http://webservice.bxd.sip.bxd.cn/}getOverallAreaRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOverallAreaResponse", propOrder = {
    "getOverallAreaResult"
})
public class TGetOverallAreaResponse {

    protected TGetOverallAreaRespond getOverallAreaResult;

    /**
     * Gets the value of the getOverallAreaResult property.
     * 
     * @return
     *     possible object is
     *     {@link TGetOverallAreaRespond }
     *     
     */
    public TGetOverallAreaRespond getGetOverallAreaResult() {
        return getOverallAreaResult;
    }

    /**
     * Sets the value of the getOverallAreaResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TGetOverallAreaRespond }
     *     
     */
    public void setGetOverallAreaResult(TGetOverallAreaRespond value) {
        this.getOverallAreaResult = value;
    }

}
