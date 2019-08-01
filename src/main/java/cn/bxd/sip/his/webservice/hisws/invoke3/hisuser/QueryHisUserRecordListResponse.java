
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
 *         &lt;element name="queryHisUserRecordListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryHisUserRecordListResult"
})
@XmlRootElement(name = "queryHisUserRecordListResponse")
public class QueryHisUserRecordListResponse {

    protected String queryHisUserRecordListResult;

    /**
     * Gets the value of the queryHisUserRecordListResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryHisUserRecordListResult() {
        return queryHisUserRecordListResult;
    }

    /**
     * Sets the value of the queryHisUserRecordListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryHisUserRecordListResult(String value) {
        this.queryHisUserRecordListResult = value;
    }

}
