package cn.bxd.sip.bxd.webservice.impl;

import cn.bxd.sip.bxd.dao.HospitalDeptMapper;
import cn.bxd.sip.bxd.dao.HospitalEmpMapper;
import cn.bxd.sip.bxd.dao.SimpleQueryDao;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalDept;
import cn.bxd.sip.bxd.model.entity.HospitalEMP;
import cn.bxd.sip.bxd.model.respond.common.*;
import cn.bxd.sip.bxd.util.JsonTools;
import cn.bxd.sip.bxd.var.TerminalVar;
import cn.bxd.sip.bxd.webservice.ICommonWebService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListByDoctorIdRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListItemRes;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import java.util.*;

/**
 * @author : cRyann
 * @create 2018-08-29
 * //@BindingType(SOAPBinding.SOAP12HTTP_BINDING) //1.2
 **/
@Service
@Slf4j
@WebService(targetNamespace = "http://webservice.bxd.sip.bxd.cn/", endpointInterface = "cn.bxd.sip.bxd.webservice.ICommonWebService")
/*@BindingType(SOAPBinding.SOAP12HTTP_BINDING)*/
public class CommonWebService implements ICommonWebService {
    //平台医院ID
    private static final String PLATFROM_HOSPITAL_ID = "0";
    private static final String WS_CLIENT_KEY = HisConvertConst.WS_CLIENT_KEY_HIS_COM;
    @Resource
    SimpleQueryDao simpleQueryDao;
    @Resource
    HospitalDeptMapper hospitalDeptMapper;
    @Resource
    HospitalEmpMapper hospitalEmpMapper;

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
    @Override
    public QueryDepartmentListRespond queryfirstDepartmentList(@NonNull String synUserName,
                                                               @NonNull String synKey,
                                                               @NonNull String terminalCode,
                                                               @NonNull String hospitalId) {
        QueryDepartmentListRespond respond = new QueryDepartmentListRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //从HIS查询科室
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_DEPARTMENT_LIST, synUserName, synKey);

            respond= new Gson().fromJson(res, QueryDepartmentListRespond.class);

            //从本地查询科室，通过map处理
            List<HospitalDept> hospitalDeptList = hospitalDeptMapper.selectByHospitalId(Integer.parseInt(hospitalId));
            Map<String, String> deptIdMap = new HashMap<>();
            for (HospitalDept dept : hospitalDeptList) {
                deptIdMap.put(dept.getDeptCode().toString(), String.valueOf(dept.getDeptNo()));
            }

            //循环赋值
            List<HisDepartment> hisDepartmentList = respond.getDepartment();
            List<HisDepartment> dbDepartmentList = new ArrayList<>();
            for (HisDepartment hisDept : hisDepartmentList) {
                //平台不存在的科室，不返回
                String deptId = deptIdMap.get(hisDept.getOrganId());
                if (deptId == null || deptId.equals("")) continue;

                //设置好科室ID后添加到列表
                hisDept.setDepartmentId(deptId);
                dbDepartmentList.add(hisDept);
            }

            //封装对象
            respond.setDepartment(dbDepartmentList);

            //设置结果
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);

            return respond;
        } catch (Exception e) {
            log.error("",e);
            //添加code
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            respond.setResultMsg("系统错误===" + e.getMessage());
            return respond;
        }
    }

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
    @Override
    public QueryDoctorListRespond queryDoctorList(@NonNull String synUserName,
                                                  @NonNull String synKey,
                                                  @NonNull String terminalCode,
                                                  @NonNull String hospitalId,
                                                  @NonNull String departmentId,
                                                  @NonNull String organId) {
        QueryDoctorListRespond respond = new QueryDoctorListRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm connectParm = hosConnectParamMaps.get(hospitalId);
            if (connectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultCode(TerminalVar.FAIL_CODE_01);
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond;
            }

            //获取wsClient
            Object client = connectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            //利用反射动态根据类名请求
            String queryDoctorList = HisWSClient.invoke(client, HisFunNameConst.QUERY_DOCTOR_LIST, synUserName, synKey, organId);
            //返回对象
            respond = JSON.parseObject(String.valueOf(queryDoctorList), QueryDoctorListRespond.class);


            //匹配ID
            List<HospitalEMP> empList = hospitalEmpMapper.selectByHospitalId(Integer.parseInt(hospitalId),Integer.parseInt(departmentId));
            Map<String, String> empMap = new HashMap<>();
            for (HospitalEMP emp : empList) {
                empMap.put(emp.getEmeiNo(), String.valueOf(emp.getEmpNo()));
            }

            //  设置平台医生ID
            List<HisDoctor> hisDoctorList = respond.getDoctor();
            List<HisDoctor> dbDoctorList = new ArrayList<>();
            for (HisDoctor doctor : hisDoctorList) {
                //平台不存在的医生，不添加
                String doctorId = empMap.get(doctor.getAccount());
                if (doctorId == null || doctorId.equals("")) continue;

                doctor.setDoctorId(doctorId);
                dbDoctorList.add(doctor);
            }

            //设置返回医生
            respond.setDoctor(dbDoctorList);
            //设置返回状态
            respond.setResultMsg(TerminalVar.STATUS_SUCCESS);
            respond.setResultCode(TerminalVar.SUCCESS_CODE);

            return respond;
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg("系统异常");
            respond.setResultCode(TerminalVar.FAIL_CODE_01);
            return respond;
        }
    }

    @Override
    public String queryTime(String terminalCode) {
        return DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss zzz");
    }

    @Override
    public List<HisDepartment> queryDepartmentList(@NonNull String synUserName,
                                                   @NonNull String synKey,
                                                   @NonNull String terminalCode,
                                                   @NonNull String hospitalId
    ) {
        QueryDepartmentListRespond respond = new QueryDepartmentListRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                respond.setResultMsg(TerminalVar.HOSPITAL_NOT_EXIST);
                return respond.getHisDepartment();
            }

            //从医院获取科室列表
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            String res = HisWSClient.invoke(client, HisFunNameConst.QUERY_DEPARTMENT_LIST, synUserName, synKey, terminalCode, hospitalId);

            //封装平台科室ID
            respond = JsonTools.json2Bean(res, QueryDepartmentListRespond.class);
            List<HisDepartment> dbDepts = simpleQueryDao.getDept(Long.valueOf(hospitalId));
            // 设置平台部门ID
            List<HisDepartment> departmentList=respond.getDepartment();
            for (HisDepartment dep : departmentList) {
                for (HisDepartment depDB : dbDepts) {
                    if (dep.getOrganId().equals(depDB.getOrganId())) {
                        dep.setDepartmentId(depDB.getDepartmentId());
                    }
                }
            }

            return respond.getDepartment();
        } catch (Exception e) {
            log.error("",e);
            respond.setResultMsg(e.getMessage());
            return respond.getHisDepartment();
        }
    }

    @Override
    public QueryDoctorBysourceDateRespond queryDoctorBysourceDate(@NonNull String synUserName,
                                                                  @NonNull String synKey,
                                                                  @NonNull String terminalCode,
                                                                  @NonNull String hospitalId,
                                                                  @NonNull String departmentId,
                                                                  @NonNull String sourceDate) {
        QueryDoctorBysourceDateRespond respond = new QueryDoctorBysourceDateRespond();
        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                //文档没有相关ResMsg返回参数设置
                return respond;
            }
            // 获取当天所有医生号源
           /* Date today = org.apache.commons.lang3.time.DateUtils.parseDate(sourceDate, "yyyy-MM-dd");
            Date tomorrow = DateUtils.getNextDay(today, 1);*/
            String res;
            Object client = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            res = HisWSClient.invoke(client, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST, synUserName, synKey, sourceDate,sourceDate);

            QueryToRegDoctorListByDoctorIdRes doctors = JSON.parseObject(res, QueryToRegDoctorListByDoctorIdRes.class);
            if (doctors == null || doctors.getRegDoctor() == null) {
                return respond;
            }
            // 过滤有号医生list
            List<Map<String, Object>> doctorList = new ArrayList<>();
            // 获取医院的部门ID
            String organId = simpleQueryDao.getDeptByDeptCode(Long.valueOf(hospitalId), Long.valueOf(departmentId));
            if (StringUtils.isBlank(organId)) {
                log.debug("数据库无相关医院ID");
                return respond;
            }

            for (QueryToRegDoctorListItemRes doctor : doctors.getRegDoctor()) {
                Map<String, Object> map = new HashMap<>();
                if (organId.equals(doctor.getDepartmentorganId())
                        && (doctor.getMorningNum() > 0
                        || doctor.getAfternoonNum() > 0
                        || doctor.getEveningNum() > 0
                )) {
                    map.put("account", doctor.getOrgandoctorId());
                    map.put("organId", doctor.getDepartmentorganId());
                    int leaveNum = doctor.getMorningNum() + doctor.getAfternoonNum() + doctor.getEveningNum();
                    map.put("leaveNum", leaveNum);
                    doctorList.add(map);
                }
            }
            // 获取医生关联关系的详细信息
            List<HisDoctor> dbDoctors = simpleQueryDao.getDoctors(Long.valueOf(hospitalId));

            // 医生信息
            res = HisWSClient.invoke(client, HisFunNameConst.QUERY_DOCTOR_LIST, synUserName, synKey, organId);
            QueryDoctorListRespond doctorInfos = JsonTools.json2Bean(res, QueryDoctorListRespond.class);
            for (HisDoctor doctor : doctorInfos.getDoctor()) {
                for (Map<String, Object> map : doctorList) {
                    if (map.get("account").equals(doctor.getAccount())) {
                        doctor.setLeaveNum((Integer) map.get("leaveNum"));
                        for (HisDoctor dbDocter : dbDoctors) {
                            if (dbDocter.getAccount().equals(doctor.getAccount())) {
                                doctor.setDoctorId(dbDocter.getDoctorId());
                                doctor.setSpecialty(dbDocter.getSpecialty());
                            }
                        }
                        respond.getDoctor().add(doctor);
                    }
                }
            }
            return respond;
        } catch (Exception e) {
            log.error("",e);
            return respond;
        }
    }

    @Override
    public QueryDepartmentBysourceDateRespond queryDepartmentBysourceDate(@NonNull String synUserName,
                                                                          @NonNull String synKey,
                                                                          @NonNull String terminalCode,
                                                                          @NonNull String hospitalId,
                                                                          @NonNull String sourceDate
//            ,
//                                                                          @NonNull String departmentId
    ) {
        org.apache.cxf.message.Message message = PhaseInterceptorChain.getCurrentMessage();
        QueryDepartmentBysourceDateRespond respond = new QueryDepartmentBysourceDateRespond();

        try {
            Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
            ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalId);
            if (tRhipConnectParm == null && !hospitalId.equals(PLATFROM_HOSPITAL_ID)) {
                return respond;
            }

            // 获取当天所有医生号源
            Date today = org.apache.commons.lang3.time.DateUtils.parseDate(sourceDate, "yyyy-MM-dd");
            Date tomorrow = DateUtils.getNextDay(today, 1);
            //
            Object clieat = tRhipConnectParm.getHosWsClientMap().get(WS_CLIENT_KEY);
            /*Map<String, Object> map = new CaseInsensitiveMap();
            map.putAll((Map<String, Object>) JSON.toJSON(msgws.get("paramsMap")));
            map.put("endDate", DateUtil.formatDate(tomorrow, "yyyy-MM-dd"));

            Object obj = WSTools.invoke(clieat, "queryToRegDoctorList", map);*/

            String res = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_TO_REG_DOCTOR_LIST, synUserName, synKey, terminalCode, hospitalId, sourceDate);

            QueryToRegDoctorListByDoctorIdRes doctors = JSON.parseObject(String.valueOf(res), QueryToRegDoctorListByDoctorIdRes.class);
            if (doctors == null || doctors.getRegDoctor() == null) {
                return respond;
            }
            // 过滤有号医生list
            List<String> doctorList = new ArrayList<>();
            for (QueryToRegDoctorListItemRes doctor : doctors.getRegDoctor()) {
//                if (departmentId.equals(doctor.getDepartmentorganId())
                if (doctor != null && doctor.getMorningNum() > 0
                        || doctor.getAfternoonNum() > 0
                        || doctor.getEveningNum() > 0) {
                    doctorList.add(doctor.getDepartmentorganId());
                }
            }
            Set<String> depSet = new HashSet<>(doctorList);

            List<HisDepartment> DBDepts = simpleQueryDao.getDept(Long.valueOf(hospitalId));
            // 获取部门的详细信息--
//            Object queryDepartmentList = WSTools.invoke(clieat, "queryDepartmentList", map);
            String queryDepartmentListStr = HisWSClient.invoke(clieat, HisFunNameConst.QUERY_DEPARTMENT_LIST, synUserName, synKey, terminalCode, hospitalId, sourceDate);

            QueryDepartmentListRespond deptRespond = JsonTools.json2Bean(String.valueOf(queryDepartmentListStr), QueryDepartmentListRespond.class);
            if (deptRespond == null || deptRespond.getDepartment() == null) {
                return respond;
            }
            for (HisDepartment dep1 : deptRespond.getDepartment()) {
                for (String str : depSet) {
                    if (dep1.getOrganId().equals(str)) {
                        for (HisDepartment DBDept : DBDepts) {
                            if (DBDept.getOrganId().equals(dep1.getOrganId())) {
                                dep1.setDepartmentId(DBDept.getDepartmentId());
                            }
                        }
                        respond.getHisDepartment().add(dep1);
                    }
                }
            }

            return respond;
        } catch (Exception e) {
            log.error("",e);
            return respond;
        }
    }

    @Override
    public SignOrsignUpRespond signOrsignUp(@NonNull String synUserName,
                                            @NonNull String synKey,
                                            @NonNull String terminalCode,
                                            @NonNull String hospitalId,
                                            @NonNull String medicareType,
                                            @NonNull String handleType) {
        return null;
        // TODO 2018/08/06 与社保签到，业务逻辑仍需讨论
    }


    @Override
    public HospitallistRespond hospitallist(@NonNull String synUserName,
                                            @NonNull String synKey,
                                            @NonNull String terminalCode) {
        HospitallistRespond respond = new HospitallistRespond();
        try {
            respond.setHospitallist(simpleQueryDao.getHospitals());
            return respond;
        } catch (Exception e) {
            log.error("",e);
            return respond;
        }
    }

    @Override
    public String commonInterface(String synUserName,
                                  String synKey,
                                  String terminalCode,
                                  String hospitalId,
                                  String code,
                                  String params) {
        return null;
        // TODO 对医院接口未实现

    }

    @Override
    public String commonInterfaceURL(String synUserName,
                                     String synKey,
                                     String terminalCode,
                                     String hospitalId,
                                     String patientNo,
                                     String medicareType,
                                     String medicareMess,
                                     String extend) {
        return null;
        // TODO 对医院接口未实现
    }
}
