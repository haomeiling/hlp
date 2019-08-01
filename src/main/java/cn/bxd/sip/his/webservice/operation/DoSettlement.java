package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.bxd.model.entity.SiMedicareRecordsWithBLOBs;
import cn.bxd.sip.bxd.service.ISiMedicareRecordsService;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.DoSettlementInput;
import cn.bxd.sip.his.model.dto.reservation.DoSettlementOutput;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 结算
 * @author:tangliang
 * @date:2018/7/23
 * @description:
 */
@Slf4j
@Component
public class DoSettlement extends AbstractOperationProcessor {

    @Autowired
    private SiService hisSer;

    @Autowired
    private ISiMedicareRecordsService tSiSer;

    @Autowired
    private HospitalSiConfigMapper tRhipHospitalSiConfigMapper;


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) {

        DoSettlementOutput output = new DoSettlementOutput();
        DoSettlementInput input = JSON.parseObject(reqMsg, DoSettlementInput.class);
        String check = checkParam(input);
        if (check == null || check.equals("")) {
            SiMedicareRecordsWithBLOBs records = tSiSer.selectByPrimaryKey(Integer.parseInt(input.getRecordId().toString()));
            if (records != null && records.getMedicareinfo() != null) {

                String hosId = input.getHosId().toString();
                String name = input.getName();
                String idCard = input.getIdNumber();
                String socialNo = input.getSocialNo();
               log.info("medicareType = " + input.getMedicareType());
                HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(hosId, input.getMedicareType());
                PayInfo payInfo = hisSer.payment(hospitalSiConfig, records.getRecordid(), records.getFeeids(), String.valueOf(input.getOrderId()), name, idCard, socialNo, records.getInputstr(),input.getOrderId());
                if (payInfo != null) {
                    output.setMsg(payInfo.getDetailMessage());
                    output.setSuccess(payInfo.getAppCode().equals("0") ? "1" : "0");
                    output.setOperCode(HisConvertConst.Operation.SETTLE_ACCOUNTS);
                    output.setCardInfo(payInfo.getCardInfo());
                    output.setHosId(payInfo.getHospitalno());
                    output.setMedicareMess(payInfo.getMedicareInfo());
                    output.setOrderId(input.getOrderId().toString());
                    output.setMedicareType(payInfo.getMedicareType());
                    output.setCashMoney(payInfo.getCashMoney());
                    output.setOverMoney(payInfo.getOverMoney());
                    output.setPayMoney(payInfo.getPayMoney());
                    output.setTotalMoney(payInfo.getTotalMoney());
                    output.setIsOverAll(payInfo.getIsOverall());
                    output.setRecordId(input.getRecordId().toString());
                    //TODO:改造
                    output.setMPoPayState("1");
                    //output.setMPoPayTime(new Date());
                    output.setSocialSecurityNo(socialNo);

                    //增加封装  20181206 add by haomeiling
                    output.setUserNo(payInfo.getUserNo());
                    output.setMedicareReturn(payInfo.getMedicareInfo());

                } else {
                    output.setSuccess("0");
                    output.setMsg("调用社保费用结算接口失败！");
                }
            } else {
                output.setSuccess("0");
                output.setMsg("获取不到患者信息！");
            }
        } else {
            output.setSuccess("0");
            output.setMsg("入参格式错误：" + check);
        }

        return JSON.toJSONString(output);
    }


    private String checkParam(DoSettlementInput input) {
        String res = "";
        if (input == null) {
            res = "入参格式错误！";
        }
        if (input.getHosId() == null) {
            res = "入参医院ID不能为空！";
        }
        if (input.getOrderId() == null) {
            res = "入参订单ID不能为空！";
        }
        if (input.getRecordId() == null) {
            res = "入参社保支付记录ID不能为空！";
        }
        if (input.getTotalMoney() == null) {
            res = "入参医疗费总额不能为空！";
        }
        if (input.getPayMoney() == null) {
            res = "入参本次帐户支付不能为空！";
        }
        if (input.getOverMoney() == null) {
            res = "入参统筹支出不能为空！";
        }
        if (input.getCashMoney() == null) {
            res = "入参现金支付金额不能为空！";
        }
        return res;
    }
}