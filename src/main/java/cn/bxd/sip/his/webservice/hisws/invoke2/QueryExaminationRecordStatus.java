
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
 *         &lt;element name="reportnos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reporttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reportnos",
    "reporttype"
})
@XmlRootElement(name = "queryExaminationRecordStatus")
public class QueryExaminationRecordStatus {

    protected String synusername;
    protected String synkey;
    protected String reportnos;
    protected String reporttype;

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
     * Gets the value of the reportnos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportnos() {
        return reportnos;
    }

    /**
     * Sets the value of the reportnos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportnos(String value) {
        this.reportnos = value;
    }

    /**
     * Gets the value of the reporttype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * Sets the value of the reporttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReporttype(String value) {
        this.reporttype = value;
    }

}
