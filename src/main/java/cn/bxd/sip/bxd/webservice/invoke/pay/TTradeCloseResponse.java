
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trade_closeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trade_closeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tradeCloseResult" type="{http://webservice.bxd.sip.bxd.cn/}tradeCloseRespond" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trade_closeResponse", propOrder = {
    "tradeCloseResult"
})
public class TTradeCloseResponse {

    protected TTradeCloseRespond tradeCloseResult;

    /**
     * Gets the value of the tradeCloseResult property.
     * 
     * @return
     *     possible object is
     *     {@link TTradeCloseRespond }
     *     
     */
    public TTradeCloseRespond getTradeCloseResult() {
        return tradeCloseResult;
    }

    /**
     * Sets the value of the tradeCloseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTradeCloseRespond }
     *     
     */
    public void setTradeCloseResult(TTradeCloseRespond value) {
        this.tradeCloseResult = value;
    }

}
