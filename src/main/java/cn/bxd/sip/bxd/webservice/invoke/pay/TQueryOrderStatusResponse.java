
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for query_orderStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="query_orderStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryOrderStatusResult" type="{http://webservice.bxd.sip.bxd.cn/}queryOrderStatusRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "query_orderStatusResponse", propOrder = {
    "queryOrderStatusResult"
})
public class TQueryOrderStatusResponse {

    protected TQueryOrderStatusRespond queryOrderStatusResult;

    /**
     * Gets the value of the queryOrderStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link TQueryOrderStatusRespond }
     *     
     */
    public TQueryOrderStatusRespond getQueryOrderStatusResult() {
        return queryOrderStatusResult;
    }

    /**
     * Sets the value of the queryOrderStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TQueryOrderStatusRespond }
     *     
     */
    public void setQueryOrderStatusResult(TQueryOrderStatusRespond value) {
        this.queryOrderStatusResult = value;
    }

}
