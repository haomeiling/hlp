package cn.bxd.sip.bxd.webservice;

import cn.bxd.sip.bxd.model.respond.common.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * @author : cRyann
 * @create 2018-08-29
 **/
@WebService
public interface ICommonWebService {

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param departmentId 平台科室ID
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.1    查询医生列表信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryDoctorList")
    @WebResult(name = "queryDoctorListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryDoctorListRespond queryDoctorList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "departmentId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentId,
            @WebParam(name = "organId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String organId
    );

    /**
     * @param terminalCode 终端编号
     * @author cRyann
     * @Description 1.3.2    同步时间
     **/
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryTime")
    @WebResult(name = "queryTimeResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    String queryTime(
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param departmentId 一级科室ID（有一级科室的时候传）
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.3    查询部门列表信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryDepartmentList")
    @WebResult(name = "queryDepartmentListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    List<HisDepartment> queryDepartmentList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId
//            ,
//            @WebParam(name = "departmentId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentId
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param departmentId 平台科室Id
     * @Param sourceDate   号源日期yyyy-MM-dd
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.4    查询当天有号的医生
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryDoctorBysourceDate")
    @WebResult(name = "queryDoctorBysourceDateResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryDoctorBysourceDateRespond queryDoctorBysourceDate(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "departmentId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentId,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param sourceDate   号源日期yyyy-MM-dd
     * @Param departmentId 一级科室ID（有一级科室的时候传）
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.5    查询当天有号的科室
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryDepartmentBysourceDate")
    @WebResult(name = "queryDepartmentBysourceDateResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryDepartmentBysourceDateRespond queryDepartmentBysourceDate(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "sourceDate", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String sourceDate
//            ,
//            @WebParam(name = "departmentId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String departmentId
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param sourceDate   号源日期yyyy-MM-dd
     * @Param departmentId 一级科室ID（有一级科室的时候传）
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.6    签到和签退
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/signOrsignUp")
    @WebResult(name = "signOrsignUpResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    SignOrsignUpRespond signOrsignUp(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "handleType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String handleType
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.7    查询一级部门列表信息
     * 已联调
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/queryfirstDepartmentList")
    @WebResult(name = "queryfirstDepartmentListResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    QueryDepartmentListRespond queryfirstDepartmentList(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.7    查询一级部门列表信息
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/hospitallist")
    @WebResult(name = "hospitallistResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    HospitallistRespond hospitallist(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param code         接口名称(根据code值判断接口类型)
     * @Param params       Json值
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.9    公共接口扩展
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/commonInterface")
    @WebResult(name = "commonInterfaceResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    String commonInterface(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "code", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String code,
            @WebParam(name = "params", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String params
    );

    /**
     * @Param synUserName  用户名
     * @Param synKey       效验码
     * @Param terminalCode 终端编号
     * @Param hospitalId   医院ID
     * @Param patientNo         患者编号
     * @Param medicareType      0，非医保；1,市医保；
     * @Param medicareMess      医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
     * @Param extend            扩展字段Json值，如：     * {“key1”：”value1”，”key2”：”value2”}
     * @Author cRyann
     * @Create 2018/9/12
     * @Description 1.3.10    公共接口扩展（自定义URL）
     */
    @WebMethod(action = "http://webservice.bxd.sip.bxd.cn/commonInterfaceURL")
    @WebResult(name = "commonInterfaceURLResult", targetNamespace = "http://webservice.bxd.sip.bxd.cn/")
    String commonInterfaceURL(
            @WebParam(name = "synUserName", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synUserName,
            @WebParam(name = "synKey", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String synKey,
            @WebParam(name = "terminalCode", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String terminalCode,
            @WebParam(name = "hospitalId", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String hospitalId,
            @WebParam(name = "patientNo", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String patientNo,
            @WebParam(name = "medicareType", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareType,
            @WebParam(name = "medicareMess", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String medicareMess,
            @WebParam(name = "extend", targetNamespace = "http://webservice.bxd.sip.bxd.cn/") String extend
    );
}
