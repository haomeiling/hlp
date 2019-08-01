package cn.bxd.sip.his.controller;

import cn.bxd.sip.bxd.dao.HospitalDeptMapper;
import cn.bxd.sip.bxd.dao.HospitalEmpMapper;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalDept;
import cn.bxd.sip.bxd.model.entity.HospitalEMP;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.model.respond.common.HisDoctor;
import cn.bxd.sip.bxd.model.respond.common.QueryDoctorListRespond;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description: 同步更新医生
 * Package: cn.bxd.sip.his.controller
 * @author Lisheng
 * @version 1.0.0  2019-5-5
 */
@Slf4j
@RestController
public class QueryDoctorListController {

    @Autowired
    private HospitalEmpMapper hospitalEmpMapper;
    @Autowired
    private HospitalDeptMapper hospitalDeptMapper;

    /**
     * 医生同步
     *
     * @param hospitalCode
     * @return
     */
    @RequestMapping(value = "query/queryDoctorList", method = RequestMethod.POST)
    @ResponseBody
    public String queryDoctorList(@RequestParam String hospitalCode, @RequestParam String deptNo) {

        //封装：向HIS发送请求
        QueryDoctorListRespond respond = new QueryDoctorListRespond();
        int count = 0;//成功条数
        //根据医院id，获取到连接配置
        Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
        ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalCode);
        try {
            //ws客户端：1.2.1.	查询医生列表信息 queryDoctorList
            //获取wsClient
            Object client = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_HIS_COM);
            String sysUserName = tRhipConnectParm.getUserName();
            String sysKey = tRhipConnectParm.getCheckCode();

            //从数据库中查询科室
            List<HospitalDept> hospitalDepts;
            if (deptNo == null || "".equals(deptNo)) {
                hospitalDepts = hospitalDeptMapper.selectByHospitalId(Integer.parseInt(hospitalCode));
            } else {
                HospitalDept hospitalDept = new HospitalDept();
                hospitalDept.setDeptCode(deptNo);
                hospitalDepts = hospitalDeptMapper.selectByHospitalDept(hospitalDept);
            }
            //遍历科室，请求获取数据
            for (HospitalDept hospitalDept : hospitalDepts) {
                //利用反射动态根据类名请求
                String queryDoctorList = HisWSClient.invoke(client, HisFunNameConst.QUERY_DOCTOR_LIST, sysUserName, sysKey, hospitalDept.getDeptCode());
                log.debug("请求医生列表信息:" + queryDoctorList);
                //返回对象
                QueryDoctorListRespond queryDoctorListRespond = JSON.parseObject(String.valueOf(queryDoctorList), QueryDoctorListRespond.class);
                respond.getDoctor().addAll(queryDoctorListRespond.getDoctor());
                respond.setResultMsg(queryDoctorListRespond.getResultMsg());
                respond.setResultCode(queryDoctorListRespond.getResultCode());
            }
            //修改数据库医生信息
            for (HisDoctor hisDoctor : respond.getDoctor()) {
                HospitalEMP hospitalEMP = new HospitalEMP();
                hospitalEMP.setEmpNo(Integer.parseInt(hisDoctor.getDoctorId()));
                if (hospitalEmpMapper.selectByEMP(hospitalEMP).size() == 0) {
                    insert(hisDoctor);
                    count++;
                } else {
                    update(hisDoctor);
                    count++;
                }
            }
            return new Gson().toJson(new BaseRespond(ReservationVar.Code.SUCCESS, "成功更新条数："+count));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询医生列表信息 queryDoctorList 错误",e.getMessage());
            return new Gson().toJson(new BaseRespond(ReservationVar.Code.FAIL, "同步失败"));
        }
    }

    //根据deptCode修改科室
    public int update(HisDoctor hisDoctor) {
        HospitalEMP hospitalEMP = new HospitalEMP();
        hospitalEMP.setMeiNo(hisDoctor.getAccount());//医生工号
        hospitalEMP.setSpeciality(hisDoctor.getAchievement());//荣誉成就
        hospitalEMP.setMajorIn(hisDoctor.getDdesc());//医生简介
        hospitalEMP.setDeptNo(Integer.parseInt(hisDoctor.getDepartmentorganId()));//科室编号
        hospitalEMP.setMeiNo(hisDoctor.getDoctorId());//平台医生ID
        hospitalEMP.setQualificationId(Short.parseShort(hisDoctor.getJob()));//医生职称
        hospitalEMP.setEmpName(hisDoctor.getName());//医生姓名
        hospitalEMP.setMeiNo(hisDoctor.getOrgandoctorId());//医生编号
//        papersPublished;//发表过论文
//        peopleHomepage;//个人主页
//        photoUrl;//医生头像
        hospitalEMP.setSocialTitles(hisDoctor.getSocialResponsibility());//社会职责
        hospitalEMP.setSpeciality(hospitalEMP.getSpeciality() + "|" + hisDoctor.getSpecialty());//医生专长
        hospitalEMP.setSpeciality(hospitalEMP.getSpeciality() + "|" + hisDoctor.getSpecialtyName());//专科名称
        hospitalEMP.setSpeciality(hospitalEMP.getSpeciality() + "|" + hisDoctor.getTreatdiseases());//治疗疾病
        return hospitalEmpMapper.updateByPrimaryKeySelective(hospitalEMP);
    }

    //添加科室
    public int insert(HisDoctor hisDoctor) {
        HospitalEMP hospitalEMP = new HospitalEMP();
        hospitalEMP.setMeiNo(hisDoctor.getAccount());//医生工号
        hospitalEMP.setSpeciality(hisDoctor.getAchievement());//荣誉成就
        hospitalEMP.setMajorIn(hisDoctor.getDdesc());//医生简介
        hospitalEMP.setDeptNo(Integer.parseInt(hisDoctor.getDepartmentorganId()));//科室编号
        hospitalEMP.setMeiNo(hisDoctor.getDoctorId());//平台医生ID
        hospitalEMP.setQualificationId(Short.parseShort(hisDoctor.getJob()));//医生职称
        hospitalEMP.setEmpName(hisDoctor.getName());//医生姓名
        hospitalEMP.setMeiNo(hisDoctor.getOrgandoctorId());//医生编号
//        papersPublished;//发表过论文
//        peopleHomepage;//个人主页
//        photoUrl;//医生头像
        hospitalEMP.setSocialTitles(hisDoctor.getSocialResponsibility());//社会职责
        hospitalEMP.setSpeciality(hospitalEMP.getSpeciality() + "|" + hisDoctor.getSpecialty());//医生专长
        hospitalEMP.setSpeciality(hospitalEMP.getSpeciality() + "|" + hisDoctor.getSpecialtyName());//专科名称
        hospitalEMP.setSpeciality(hospitalEMP.getSpeciality() + "|" + hisDoctor.getTreatdiseases());//治疗疾病
        return hospitalEmpMapper.insertSelective(hospitalEMP);
    }
}
