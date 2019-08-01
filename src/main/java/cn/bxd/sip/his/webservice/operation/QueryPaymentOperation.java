package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.model.MedicareType;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.var.SIVar;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.comm.HisFunNameConst;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListByDoctorIdRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorListItemRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorTimesItemRes;
import cn.bxd.sip.his.model.dto.his.QueryToRegDoctorTimesRes;
import cn.bxd.sip.his.model.dto.reservation.*;
import cn.bxd.sip.his.utils.DateUtils;
import cn.bxd.sip.his.webservice.hisws.client.HisWSClient;
import cn.bxd.sip.si.service.SiService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 查询支付信息
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年11月26日 下午3:23:50
 */
@Slf4j
@Component
public class QueryPaymentOperation extends AbstractOperationProcessor {

    @Autowired
    private SiService hisSer;
    
    @Autowired
    private HospitalSiConfigMapper tRhipHospitalSiConfigMapper;

    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {
    	GetPaymentReqDatas input = JSON.parseObject(reqMsg, GetPaymentReqDatas.class);
    	HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(input.getHosId(), MedicareType.CITY.getHisCode());
    	PayInfo payInfo = hisSer.selectPayment(hospitalSiConfig, input.getFeeIds(), input.getPoNo());
    	GetPaymentResDatas resDatas = new GetPaymentResDatas(input.getOperCode(), SIVar.BusinessCode.RESULT_CODE_SUCCESS, input.getHosId(), payInfo.getAppCode()
    			, payInfo.getRecordId());
    	return JSON.toJSONString(resDatas, SerializerFeature.DisableCircularReferenceDetect);
    }


}