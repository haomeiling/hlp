
package cn.bxd.sip.his.webservice.hisws.invoke3.hiscom;

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
@WebService(name = "HisComSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HisComSoap {


    /**
     * 查询医生列表信息
     * 
     * @param synKey
     * @param departmentorganId
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryDoctorList")
    @WebResult(name = "queryDoctorListResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryDoctorList", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDoctorList")
    @ResponseWrapper(localName = "queryDoctorListResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDoctorListResponse")
    public String queryDoctorList(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "departmentorganId", targetNamespace = "http://tempuri.org/")
        String departmentorganId);

    /**
     * 根据科室Id查询科室信息
     * 
     * @param organId
     * @param synKey
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryDepartmentById")
    @WebResult(name = "queryDepartmentByIdResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryDepartmentById", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDepartmentById")
    @ResponseWrapper(localName = "queryDepartmentByIdResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDepartmentByIdResponse")
    public String queryDepartmentById(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "organId", targetNamespace = "http://tempuri.org/")
        String organId);

    /**
     * 查询部门列表信息
     * 
     * @param synKey
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryDepartmentList")
    @WebResult(name = "queryDepartmentListResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryDepartmentList", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDepartmentList")
    @ResponseWrapper(localName = "queryDepartmentListResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDepartmentListResponse")
    public String queryDepartmentList(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey);

    /**
     * 查询医生信息
     * 
     * @param synKey
     * @param organdoctorId
     * @param synUserName
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryDoctorById")
    @WebResult(name = "queryDoctorByIdResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryDoctorById", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDoctorById")
    @ResponseWrapper(localName = "queryDoctorByIdResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryDoctorByIdResponse")
    public String queryDoctorById(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "organdoctorId", targetNamespace = "http://tempuri.org/")
        String organdoctorId);

    /**
     * 查询医生信息
     * 
     * @param cardNo
     * @param synKey
     * @param terminalNo
     * @param synUserName
     * @param endDate
     * @param code
     * @param beginDate
     * @param cardType
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://tempuri.org/queryExamineReport")
    @WebResult(name = "queryExamineReportResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "queryExamineReport", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryExamineReport")
    @ResponseWrapper(localName = "queryExamineReportResponse", targetNamespace = "http://tempuri.org/", className = "cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.QueryExamineReportResponse")
    public String queryExamineReport(
        @WebParam(name = "synUserName", targetNamespace = "http://tempuri.org/")
        String synUserName,
        @WebParam(name = "synKey", targetNamespace = "http://tempuri.org/")
        String synKey,
        @WebParam(name = "TerminalNo", targetNamespace = "http://tempuri.org/")
        String terminalNo,
        @WebParam(name = "CardType", targetNamespace = "http://tempuri.org/")
        String cardType,
        @WebParam(name = "CardNo", targetNamespace = "http://tempuri.org/")
        String cardNo,
        @WebParam(name = "BeginDate", targetNamespace = "http://tempuri.org/")
        String beginDate,
        @WebParam(name = "EndDate", targetNamespace = "http://tempuri.org/")
        String endDate,
        @WebParam(name = "Code", targetNamespace = "http://tempuri.org/")
        String code);

}
