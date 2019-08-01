
package cn.bxd.sip.his.webservice.hisws.invoke3.hisuser;

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
 *         &lt;element name="queryPatientInfoByIdNOResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryPatientInfoByIdNOResult"
})
@XmlRootElement(name = "queryPatientInfoByIdNOResponse")
public class QueryPatientInfoByIdNOResponse {

    protected String queryPatientInfoByIdNOResult;

    /**
     * Gets the value of the queryPatientInfoByIdNOResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryPatientInfoByIdNOResult() {
        return queryPatientInfoByIdNOResult;
    }

    /**
     * Sets the value of the queryPatientInfoByIdNOResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryPatientInfoByIdNOResult(String value) {
        this.queryPatientInfoByIdNOResult = value;
    }

}
