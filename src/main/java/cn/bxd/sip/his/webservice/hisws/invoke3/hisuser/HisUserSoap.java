
package cn.bxd.sip.his.webservice.hisws.invoke3.hisuser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HisUserSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HisUserSoap {


    /**
     * 身份证号码查看患者信息
     * 
     * @param cardNo
     * @param synKey
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryPatientInfoByIdNO")
    @WebResult(name = "queryPatientInfoByIdNOResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryPatientInfoByIdNO", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryPatientInfoByIdNO")
    @ResponseWrapper(localName = "queryPatientInfoByIdNOResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryPatientInfoByIdNOResponse")
    public String queryPatientInfoByIdNO(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "cardNo", targetNamespace = "http://tempuri.org/")
        String cardNo);

    /**
     * 社保、银行、就诊卡号查看患者信息
     * 
     * @param socialsecurityNO
     * @param synKey
     * @param synUserName
     * @param visitCardNo
     * @param bankCardNumber
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryPatientInfoBySocialsecurityNO")
    @WebResult(name = "queryPatientInfoBySocialsecurityNOResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryPatientInfoBySocialsecurityNO", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryPatientInfoBySocialsecurityNO")
    @ResponseWrapper(localName = "queryPatientInfoBySocialsecurityNOResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryPatientInfoBySocialsecurityNOResponse")
    public String queryPatientInfoBySocialsecurityNO(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "socialsecurityNO", targetNamespace = "http://tempuri.org/")
        String socialsecurityNO,
        @WebParam(name = "bankCardNumber", targetNamespace = "http://tempuri.org/")
        String bankCardNumber,
        @WebParam(name = "visitCardNo", targetNamespace = "http://tempuri.org/")
        String visitCardNo);

    /**
     * 创建患者详细档案：姓名、性别、身份证号、社保号、电话
     * 
     * @param synKey
     * @param paramList
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/doCreatCardInfo")
    @WebResult(name = "doCreatCardInfoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "doCreatCardInfo", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.DoCreatCardInfo")
    @ResponseWrapper(localName = "doCreatCardInfoResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.DoCreatCardInfoResponse")
    public String doCreatCardInfo(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "paramList", targetNamespace = "http://tempuri.org/")
        String paramList);

    /**
     * 更新患者信息
     * 
     * @param cardNo
     * @param socialsecurityNO
     * @param synKey
     * @param synUserName
     * @param visitCardNo
     * @param bankCardNumber
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/updatePatientInfo")
    @WebResult(name = "updatePatientInfoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "updatePatientInfo", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.UpdatePatientInfo")
    @ResponseWrapper(localName = "updatePatientInfoResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.UpdatePatientInfoResponse")
    public String updatePatientInfo(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "cardNo", targetNamespace = "http://tempuri.org/")
        String cardNo,
        @WebParam(name = "socialsecurityNO", targetNamespace = "http://tempuri.org/")
        String socialsecurityNO,
        @WebParam(name = "bankCardNumber", targetNamespace = "http://tempuri.org/")
        String bankCardNumber,
        @WebParam(name = "visitCardNo", targetNamespace = "http://tempuri.org/")
        String visitCardNo);

    /**
     * 查询检查,检验数据打印状态状态
     * 
     * @param reportNos
     * @param reportType
     * @param synKey
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryExaminationRecordStatus")
    @WebResult(name = "queryExaminationRecordStatusResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryExaminationRecordStatus", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryExaminationRecordStatus")
    @ResponseWrapper(localName = "queryExaminationRecordStatusResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryExaminationRecordStatusResponse")
    public String queryExaminationRecordStatus(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "reportNos", targetNamespace = "http://tempuri.org/")
        String reportNos,
        @WebParam(name = "reportType", targetNamespace = "http://tempuri.org/")
        String reportType);

    /**
     * 更新检查,检验数据打印状态
     * 
     * @param reportNos
     * @param reportType
     * @param synKey
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/updateExaminationRecordStatus")
    @WebResult(name = "updateExaminationRecordStatusResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "updateExaminationRecordStatus", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.UpdateExaminationRecordStatus")
    @ResponseWrapper(localName = "updateExaminationRecordStatusResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.UpdateExaminationRecordStatusResponse")
    public String updateExaminationRecordStatus(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "reportNos", targetNamespace = "http://tempuri.org/")
        String reportNos,
        @WebParam(name = "reportType", targetNamespace = "http://tempuri.org/")
        String reportType);

    /**
     * 获取检查,检验数据
     * 
     * @param startDate
     * @param synKey
     * @param synUserName
     * @param endDate
     * @param patientNo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryExaminationRecordList")
    @WebResult(name = "queryExaminationRecordListResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryExaminationRecordList", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryExaminationRecordList")
    @ResponseWrapper(localName = "queryExaminationRecordListResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryExaminationRecordListResponse")
    public String queryExaminationRecordList(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "patientNo", targetNamespace = "http://tempuri.org/")
        String patientNo,
        @WebParam(name = "startDate", targetNamespace = "http://tempuri.org/")
        String startDate,
        @WebParam(name = "endDate", targetNamespace = "http://tempuri.org/")
        String endDate);

    /**
     * 病历记录查询
     * 
     * @param startDate
     * @param synKey
     * @param synUserName
     * @param endDate
     * @param patientNo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryHisUserRecordList")
    @WebResult(name = "queryHisUserRecordListResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryHisUserRecordList", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryHisUserRecordList")
    @ResponseWrapper(localName = "queryHisUserRecordListResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.QueryHisUserRecordListResponse")
    public String queryHisUserRecordList(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "patientNo", targetNamespace = "http://tempuri.org/")
        String patientNo,
        @WebParam(name = "startDate", targetNamespace = "http://tempuri.org/")
        String startDate,
        @WebParam(name = "endDate", targetNamespace = "http://tempuri.org/")
        String endDate);

}