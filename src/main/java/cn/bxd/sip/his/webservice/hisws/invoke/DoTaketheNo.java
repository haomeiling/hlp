
package cn.bxd.sip.his.webservice.hisws.invoke;

import javax.jws.WebParam;
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
 *         &lt;element name="synUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doTakeIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "synUserName",
        "synKey",
        "sourceMark",
        "patientNo",
        "sourceDate",
        "departmentorganId",
        "payType",
        "payRecord",
        "payMoney"
})

@XmlRootElement(name = "doTaketheNo")
public class DoTaketheNo {

    protected String synUserName;
    protected String synKey;
    //protected String doTakeIn;
    protected String sourceMark;
    protected String patientNo;
    protected String sourceDate;
    protected String departmentorganId;
    protected String payType;
    protected String payRecord;
    protected String payMoney;


    /**
     * 获取synUserName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getSynUserName() {
        return synUserName;
    }

    /**
     * 设置synUserName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSynUserName(String value) {
        this.synUserName = value;
    }

    /**
     * 获取synKey属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getSynKey() {
        return synKey;
    }

    /**
     * 设置synKey属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSynKey(String value) {
        this.synKey = value;
    }

    /**
     * 获取doTakeIn属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
   /* public String getDoTakeIn() {
        return doTakeIn;
    }*/

    /**
     * 设置doTakeIn属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    /*public void setDoTakeIn(String value) {
        this.doTakeIn = value;
    }*/


    public String getSourceMark() {
        return sourceMark;
    }

    public void setSourceMark(String sourceMark) {
        this.sourceMark = sourceMark;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

    public String getDepartmentorganId() {
        return departmentorganId;
    }

    public void setDepartmentorganId(String departmentorganId) {
        this.departmentorganId = departmentorganId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayRecord() {
        return payRecord;
    }

    public void setPayRecord(String payRecord) {
        this.payRecord = payRecord;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }
}
