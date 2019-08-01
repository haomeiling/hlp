
package cn.bxd.sip.his.webservice.hisws.invoke3.reg;

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
 *         &lt;element name="synUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organdoctorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentorganId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timestypeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceTimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "synUserName",
    "synKey",
    "organdoctorId",
    "departmentorganId",
    "patientNo",
    "sourceDate",
    "timestypeNo",
    "sourceTimeType"
})
@XmlRootElement(name = "doregFeeInterface")
public class DoregFeeInterface {

    protected String synUserName;
    protected String synKey;
    protected String organdoctorId;
    protected String departmentorganId;
    protected String patientNo;
    protected String sourceDate;
    protected String timestypeNo;
    protected String sourceTimeType;

    /**
     * Gets the value of the synUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynUserName() {
        return synUserName;
    }

    /**
     * Sets the value of the synUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynUserName(String value) {
        this.synUserName = value;
    }

    /**
     * Gets the value of the synKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSynKey() {
        return synKey;
    }

    /**
     * Sets the value of the synKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynKey(String value) {
        this.synKey = value;
    }

    /**
     * Gets the value of the organdoctorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgandoctorId() {
        return organdoctorId;
    }

    /**
     * Sets the value of the organdoctorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgandoctorId(String value) {
        this.organdoctorId = value;
    }

    /**
     * Gets the value of the departmentorganId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentorganId() {
        return departmentorganId;
    }

    /**
     * Sets the value of the departmentorganId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentorganId(String value) {
        this.departmentorganId = value;
    }

    /**
     * Gets the value of the patientNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientNo() {
        return patientNo;
    }

    /**
     * Sets the value of the patientNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientNo(String value) {
        this.patientNo = value;
    }

    /**
     * Gets the value of the sourceDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDate() {
        return sourceDate;
    }

    /**
     * Sets the value of the sourceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDate(String value) {
        this.sourceDate = value;
    }

    /**
     * Gets the value of the timestypeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestypeNo() {
        return timestypeNo;
    }

    /**
     * Sets the value of the timestypeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestypeNo(String value) {
        this.timestypeNo = value;
    }

    /**
     * Gets the value of the sourceTimeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceTimeType() {
        return sourceTimeType;
    }

    /**
     * Sets the value of the sourceTimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceTimeType(String value) {
        this.sourceTimeType = value;
    }

}
