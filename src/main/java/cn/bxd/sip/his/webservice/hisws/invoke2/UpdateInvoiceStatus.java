
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
 *         &lt;element name="outinvoiceid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clinictrueno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardtypeid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitcardno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "outinvoiceid",
    "clinictrueno",
    "cardtypeid",
    "cardno",
    "visitcardno"
})
@XmlRootElement(name = "updateInvoiceStatus")
public class UpdateInvoiceStatus {

    protected String synusername;
    protected String synkey;
    protected String outinvoiceid;
    protected String clinictrueno;
    protected String cardtypeid;
    protected String cardno;
    protected String visitcardno;

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
     * Gets the value of the outinvoiceid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutinvoiceid() {
        return outinvoiceid;
    }

    /**
     * Sets the value of the outinvoiceid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutinvoiceid(String value) {
        this.outinvoiceid = value;
    }

    /**
     * Gets the value of the clinictrueno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinictrueno() {
        return clinictrueno;
    }

    /**
     * Sets the value of the clinictrueno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinictrueno(String value) {
        this.clinictrueno = value;
    }

    /**
     * Gets the value of the cardtypeid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardtypeid() {
        return cardtypeid;
    }

    /**
     * Sets the value of the cardtypeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardtypeid(String value) {
        this.cardtypeid = value;
    }

    /**
     * Gets the value of the cardno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * Sets the value of the cardno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardno(String value) {
        this.cardno = value;
    }

    /**
     * Gets the value of the visitcardno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitcardno() {
        return visitcardno;
    }

    /**
     * Sets the value of the visitcardno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitcardno(String value) {
        this.visitcardno = value;
    }

}
