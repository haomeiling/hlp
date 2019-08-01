
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateRecipeStatusStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateRecipeStatusStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateRecipeStatusStatusResult" type="{http://webservice.bxd.sip.bxd.cn/}baseRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateRecipeStatusStatusResponse", propOrder = {
    "updateRecipeStatusStatusResult"
})
public class TUpdateRecipeStatusStatusResponse {

    protected TBaseRespond updateRecipeStatusStatusResult;

    /**
     * Gets the value of the updateRecipeStatusStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link TBaseRespond }
     *     
     */
    public TBaseRespond getUpdateRecipeStatusStatusResult() {
        return updateRecipeStatusStatusResult;
    }

    /**
     * Sets the value of the updateRecipeStatusStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBaseRespond }
     *     
     */
    public void setUpdateRecipeStatusStatusResult(TBaseRespond value) {
        this.updateRecipeStatusStatusResult = value;
    }

}
