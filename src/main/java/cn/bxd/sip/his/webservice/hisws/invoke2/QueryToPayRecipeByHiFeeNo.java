
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
 *         &lt;element name="hifeeno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicaretype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medicaremess" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hifeeno",
    "patientno",
    "medicaretype",
    "medicaremess"
})
@XmlRootElement(name = "queryToPayRecipeByHiFeeNo")
public class QueryToPayRecipeByHiFeeNo {

    protected String synusername;
    protected String synkey;
    protected String hifeeno;
    protected String patientno;
    protected String medicaretype;
    protected String medicaremess;

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
     * Gets the value of the hifeeno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHifeeno() {
        return hifeeno;
    }

    /**
     * Sets the value of the hifeeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHifeeno(String value) {
        this.hifeeno = value;
    }

    /**
     * Gets the value of the patientno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientno() {
        return patientno;
    }

    /**
     * Sets the value of the patientno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientno(String value) {
        this.patientno = value;
    }

    /**
     * Gets the value of the medicaretype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicaretype() {
        return medicaretype;
    }

    /**
     * Sets the value of the medicaretype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicaretype(String value) {
        this.medicaretype = value;
    }

    /**
     * Gets the value of the medicaremess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicaremess() {
        return medicaremess;
    }

    /**
     * Sets the value of the medicaremess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicaremess(String value) {
        this.medicaremess = value;
    }

}
