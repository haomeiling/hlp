
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
 *         &lt;element name="queryToPayRecipeInfoListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryToPayRecipeInfoListResult"
})
@XmlRootElement(name = "queryToPayRecipeInfoListResponse")
public class QueryToPayRecipeInfoListResponse {

    protected String queryToPayRecipeInfoListResult;

    /**
     * Gets the value of the queryToPayRecipeInfoListResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryToPayRecipeInfoListResult() {
        return queryToPayRecipeInfoListResult;
    }

    /**
     * Sets the value of the queryToPayRecipeInfoListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryToPayRecipeInfoListResult(String value) {
        this.queryToPayRecipeInfoListResult = value;
    }

}
