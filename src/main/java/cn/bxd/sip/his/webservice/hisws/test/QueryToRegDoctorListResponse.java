
package cn.bxd.sip.his.webservice.hisws.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryToRegDoctorListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "queryToRegDoctorListResult"
})
@XmlRootElement(name = "queryToRegDoctorListResponse")
public class QueryToRegDoctorListResponse {

    protected String queryToRegDoctorListResult;

    /**
     * 获取queryToRegDoctorListResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryToRegDoctorListResult() {
        return queryToRegDoctorListResult;
    }

    /**
     * 设置queryToRegDoctorListResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryToRegDoctorListResult(String value) {
        this.queryToRegDoctorListResult = value;
    }

}
