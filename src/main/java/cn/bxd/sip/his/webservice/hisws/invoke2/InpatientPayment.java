
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
 *         &lt;element name="synusername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inhosno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bedno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paytype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payrecord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymoney" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payreturn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "synusername",
    "synkey",
    "inhosno",
    "bedno",
    "paytype",
    "payrecord",
    "paymoney",
    "payreturn",
    "terminalcode"
})
@XmlRootElement(name = "inpatientPayment")
public class InpatientPayment {

    protected String synusername;
    protected String synkey;
    protected String inhosno;
    protected String bedno;
    protected String paytype;
    protected String payrecord;
    protected String paymoney;
    protected String payreturn;
    protected String terminalcode;

    /**
     * Gets the value of the synusername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynusername() {
        return synusername;
    }

    /**
     * Sets the value of the synusername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynusername(String value) {
        this.synusername = value;
    }

    /**
     * Gets the value of the synkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynkey() {
        return synkey;
    }

    /**
     * Sets the value of the synkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynkey(String value) {
        this.synkey = value;
    }

    /**
     * Gets the value of the inhosno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInhosno() {
        return inhosno;
    }

    /**
     * Sets the value of the inhosno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInhosno(String value) {
        this.inhosno = value;
    }

    /**
     * Gets the value of the bedno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBedno() {
        return bedno;
    }

    /**
     * Sets the value of the bedno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBedno(String value) {
        this.bedno = value;
    }

    /**
     * Gets the value of the paytype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * Sets the value of the paytype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaytype(String value) {
        this.paytype = value;
    }

    /**
     * Gets the value of the payrecord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayrecord() {
        return payrecord;
    }

    /**
     * Sets the value of the payrecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayrecord(String value) {
        this.payrecord = value;
    }

    /**
     * Gets the value of the paymoney property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymoney() {
        return paymoney;
    }

    /**
     * Sets the value of the paymoney property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymoney(String value) {
        this.paymoney = value;
    }

    /**
     * Gets the value of the payreturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayreturn() {
        return payreturn;
    }

    /**
     * Sets the value of the payreturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayreturn(String value) {
        this.payreturn = value;
    }

    /**
     * Gets the value of the terminalcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalcode() {
        return terminalcode;
    }

    /**
     * Sets the value of the terminalcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalcode(String value) {
        this.terminalcode = value;
    }

}
