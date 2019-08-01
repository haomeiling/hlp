
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
 *         &lt;element name="doctorStoppedResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "doctorStoppedResult"
})
@XmlRootElement(name = "doctorStoppedResponse")
public class DoctorStoppedResponse {

    protected String doctorStoppedResult;

    /**
     * Gets the value of the doctorStoppedResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorStoppedResult() {
        return doctorStoppedResult;
    }

    /**
     * Sets the value of the doctorStoppedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorStoppedResult(String value) {
        this.doctorStoppedResult = value;
    }

}
