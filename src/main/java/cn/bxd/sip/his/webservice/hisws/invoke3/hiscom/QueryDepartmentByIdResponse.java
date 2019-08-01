
package cn.bxd.sip.his.webservice.hisws.invoke3.hiscom;

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
 *         &lt;element name="queryDepartmentByIdResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryDepartmentByIdResult"
})
@XmlRootElement(name = "queryDepartmentByIdResponse")
public class QueryDepartmentByIdResponse {

    protected String queryDepartmentByIdResult;

    /**
     * Gets the value of the queryDepartmentByIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryDepartmentByIdResult() {
        return queryDepartmentByIdResult;
    }

    /**
     * Sets the value of the queryDepartmentByIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryDepartmentByIdResult(String value) {
        this.queryDepartmentByIdResult = value;
    }

}
