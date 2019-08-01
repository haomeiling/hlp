
package cn.bxd.sip.bxd.webservice.invoke.pay;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for queryPaymentRecordStatusRespond complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryPaymentRecordStatusRespond">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.bxd.sip.bxd.cn/}baseRespond">
 *       &lt;sequence>
 *         &lt;element name="hiFeeList" type="{http://webservice.bxd.sip.bxd.cn/}paymentRecordStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryPaymentRecordStatusRespond", propOrder = {
    "hiFeeList"
})
public class TQueryPaymentRecordStatusRespond
    extends TBaseRespond
{

    @XmlElement(namespace = "", nillable = true)
    protected List<TPaymentRecordStatus> hiFeeList;

    /**
     * Gets the value of the hiFeeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hiFeeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHiFeeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPaymentRecordStatus }
     * 
     * 
     */
    public List<TPaymentRecordStatus> getHiFeeList() {
        if (hiFeeList == null) {
            hiFeeList = new ArrayList<TPaymentRecordStatus>();
        }
        return this.hiFeeList;
    }

}
