
package cn.bxd.sip.his.webservice.hisws.invoke3.hisuser;

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
 *         &lt;element name="updateExaminationRecordStatusResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "updateExaminationRecordStatusResult"
})
@XmlRootElement(name = "updateExaminationRecordStatusResponse")
public class UpdateExaminationRecordStatusResponse {

    protected String updateExaminationRecordStatusResult;

    /**
     * Gets the value of the updateExaminationRecordStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateExaminationRecordStatusResult() {
        return updateExaminationRecordStatusResult;
    }

    /**
     * Sets the value of the updateExaminationRecordStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateExaminationRecordStatusResult(String value) {
        this.updateExaminationRecordStatusResult = value;
    }

}
