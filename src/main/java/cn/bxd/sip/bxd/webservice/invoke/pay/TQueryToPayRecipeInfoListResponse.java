
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryToPayRecipeInfoListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryToPayRecipeInfoListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryToPayRecipeInfoListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryToPayRecipeInfoListResponse", propOrder = {
    "queryToPayRecipeInfoListResult"
})
public class TQueryToPayRecipeInfoListResponse {

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
