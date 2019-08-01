package cn.bxd.sip.his.controller;

import cn.bxd.sip.bxd.dao.HospitalDeptMapper;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalDept;
import cn.bxd.sip.bxd.model.respond.BaseRespond;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.Department;
import cn.bxd.sip.his.model.dto.his.QueryDepartmentRes;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Description: 同步更新科室
 * Package: cn.bxd.sip.his.controller
 * @author Lisheng
 * @version 1.0.0  2019-5-5
 */
@Slf4j
@RestController
public class QueryDepartmentListController {

@Autowired
private HospitalDeptMapper hospitalDeptMapper;

    /**
	 * 1.2.3	同步部门列表信息
	 * @param hospitalCode
	 * @return
	 */
	@RequestMapping(value = "query/queryDeptList", method = RequestMethod.POST)
	@ResponseBody
    @Transactional
	public String queryDepartmentList(@RequestParam String hospitalCode) {

		//封装：向HIS发送请求
		QueryDepartmentRes queryDepartmentRes;
        int count = 0;//成功条数
		//根据医院id，获取到连接配置
		Map<String, ConnectParm> hosConnectParamMaps = HisConvertConst.allHosWsMap;
		ConnectParm tRhipConnectParm = hosConnectParamMaps.get(hospitalCode);
		try {
			//1.2.3	查询部门列表信息 queryDepartmentList
			//获取wsClient
			Object client = tRhipConnectParm.getHosWsClientMap().get(HisConvertConst.WS_CLIENT_KEY_HIS_COM);
			String sysUserName = tRhipConnectParm.getUserName();
			String sysKey = tRhipConnectParm.getCheckCode();

			//利用反射动态根据类名请求
			String queryDepartmentList = HisWSClient.invoke(client, HisFunNameConst.QUERY_DEPARTMENT_LIST, sysUserName, sysKey);
			//返回对象
			queryDepartmentRes = JSON.parseObject(String.valueOf(queryDepartmentList), QueryDepartmentRes.class);
            log.debug("请求部门列表信息:" + queryDepartmentList);
            //遍历返回的数据
            for (Department department:queryDepartmentRes.getDepartment()){
                //查询数据库比对是否有code一致的科室
                HospitalDept hospitalDept = new HospitalDept();
                hospitalDept.setDeptCode(department.getDepartmentId());
                if (hospitalDeptMapper.selectByHospitalDept(hospitalDept).size()==0){
                    insert(department);
                    count++;
                }else {
                    update(department);
                    count++;
                }
            }
            return new Gson().toJson(new BaseRespond(ReservationVar.Code.SUCCESS, "成功更新条数："+count));
		} catch (Exception e) {
		    e.printStackTrace();
			log.error("查询部门列表信息 queryDepartmentList 错误", e);
            return new Gson().toJson(new BaseRespond(ReservationVar.Code.FAIL, "同步失败"));
		}
	}

	//根据deptCode修改科室
	public int update(Department department){
        HospitalDept hospitalDept = new HospitalDept();
        hospitalDept.setDeptCode(department.getDepartmentId());
        hospitalDept.setDeptName(department.getName());
        hospitalDept.setDeptAddr(department.getDepartAddr());
        return hospitalDeptMapper.updateByPrimaryKeySelective(hospitalDept);
    }
    //添加科室
    public int insert(Department department){
        HospitalDept hospitalDept = new HospitalDept();
        hospitalDept.setDeptCode(department.getDepartmentId());
        hospitalDept.setDeptName(department.getName());
        hospitalDept.setDeptAddr(department.getDepartAddr());
        return hospitalDeptMapper.insertSelective(hospitalDept);
    }
}
