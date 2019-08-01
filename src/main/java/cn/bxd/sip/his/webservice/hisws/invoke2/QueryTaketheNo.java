
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
 *         &lt;element name="sourcemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourcedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentorganid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sourcemark",
    "patientno",
    "sourcedate",
    "departmentorganid"
})
@XmlRootElement(name = "queryTaketheNo")
public class QueryTaketheNo {

    protected String synusername;
    protected String synkey;
    protected String sourcemark;
    protected String patientno;
    protected String sourcedate;
    protected String departmentorganid;

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
     * Gets the value of the sourcemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcemark() {
        return sourcemark;
    }

    /**
     * Sets the value of the sourcemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcemark(String value) {
        this.sourcemark = value;
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
     * Gets the value of the sourcedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcedate() {
        return sourcedate;
    }

    /**
     * Sets the value of the sourcedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcedate(String value) {
        this.sourcedate = value;
    }

    /**
     * Gets the value of the departmentorganid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentorganid() {
        return departmentorganid;
    }

    /**
     * Sets the value of the departmentorganid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentorganid(String value) {
        this.departmentorganid = value;
    }

}
