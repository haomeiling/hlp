package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.model.MedicareType;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.CancelOrderOutput;
import cn.bxd.sip.his.model.dto.reservation.RefundSocialInput;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 社保退款
 * @author Administrator
 *
 */
@Slf4j
@Component
public class RefundSocialOperation extends AbstractOperationProcessor {

    @Autowired
    private SiService hisSer;

    @Autowired
    private HospitalSiConfigMapper tRhipHospitalSiConfigMapper;
	
	@Override
    public String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
		RefundSocialInput input = new RefundSocialInput();
        input = JSON.parseObject(reqMsg,RefundSocialInput.class);
        String check = checkParam(input);
        if(!check.equals("")) {
        	return JSON.toJSONString(new CancelOrderOutput(Integer.parseInt(HisConvertConst.Operation.REFUND_SOCIAL)
        			, 0, check));
        }
        String resultMsg = doCancelSocial(String.valueOf(input.getHosId()), input.getRecordId(), input.getPatientNo(), input.getTransId()
        		, input.getPatientName(), input.getIdNumber(), input.getSocialNo());
        if(resultMsg.equals("")) {
	        return JSON.toJSONString(new CancelOrderOutput(Integer.parseInt(HisConvertConst.Operation.REFUND_SOCIAL)
	    			, 1, ""));
        }
        return JSON.toJSONString(new CancelOrderOutput(Integer.parseInt(HisConvertConst.Operation.REFUND_SOCIAL)
    			, 0, resultMsg));
    }

	
    private String doCancelSocial(String hosId, String recordId, String patientNo, String transId, String patientName, 
    		String idNumber, String socialNo){
            HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hosId, MedicareType.CITY.getHisCode());
            PayInfo info = hisSer.cancelPayment(hospitalSiConfig, Integer.valueOf(recordId), patientNo,
            		transId, patientName, idNumber,socialNo, Long.valueOf(transId));
            return info.getAppCode().equals("0") ? "" : info.getDetailMessage();
    }
    
    private String checkParam(RefundSocialInput input) {
        String res = "";
        if (input == null) {
            res = "入参格式错误！";
        }
        if(input.getCancelReason() == null){
            res = "入参取消原因不能为空！";
        }
        if(input.getHosId() == null){
            res = "入参医院Id不能为空！";
        }
        if(input.getOperCode() == null){
            res = "入参操作码不能为空！";
        }
        if(input.getRecordId() == null || "".equals(input.getRecordId())){
            res = "医保交易号不能为空！";
        }
        if(input.getPatientName() == null || "".equals(input.getPatientName())){
            res = "患者姓名不能为空！";
        }
        if(input.getIdNumber() == null || "".equals(input.getIdNumber())){
            res = "患者身份证号不能为空";
        }
        if(input.getTransId() == null || "".equals(input.getTransId())){
            res = "订单号不能为空！";
        }
        if(input.getSocialNo() == null || "".equals(input.getSocialNo())){
            res = "医保卡号不能为空！";
        }
        return res;
    }

}