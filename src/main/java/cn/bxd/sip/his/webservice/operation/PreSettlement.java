package cn.bxd.sip.his.webservice.operation;

import cn.bxd.sip.bxd.dao.ConnectParmMapper;
import cn.bxd.sip.bxd.dao.HospitalSiConfigMapper;
import cn.bxd.sip.bxd.model.dto.PayInfo;
import cn.bxd.sip.bxd.model.entity.ConnectParm;
import cn.bxd.sip.bxd.model.entity.HospitalSiConfig;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.model.dto.reservation.*;
import cn.bxd.sip.si.service.SiService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 预结算
 *
 * @author:tangliang
 * @date:2018/7/23
 * @description:
 */
@Slf4j
@Component
public class PreSettlement extends AbstractOperationProcessor {
    private static final String C_SUCCESS = "1";
    private static final String C_FAIL = "0";

    private static final String SI_SUCCESS = "0";
    private static final String SI_FAIL = "1";


    @Autowired
    private SiService hisSer;

    @Autowired
    private ConnectParmMapper connectParmMapper;

    @Autowired
    private HospitalSiConfigMapper tRhipHospitalSiConfigMapper;

    @Autowired
    private OperationProcessor geBillInfoListOperation;


    @Override
    protected String sendToHisOperation(String reqMsg, ConnectParm tRhipConnectParm) throws Exception {
        log.info("入參：" + reqMsg);
        PreSettlementOutput preSettlementOutput = new PreSettlementOutput();//出参
        //解析入参
        PreSettlementInput preSettlementInput = JSON.parseObject(reqMsg, PreSettlementInput.class);//入参

        String medicareInfo;
        String check = checkParam(preSettlementInput);
        if (check == null || check.equals("")) {

            //调用his  1.4.1 queryToPayRecipeInfoList
            ConnectParm myTRhipConnectParm = connectParmMapper.selectByPrimaryKey(Integer.parseInt(preSettlementInput.getHosId().toString()));


            if (myTRhipConnectParm != null && myTRhipConnectParm.getUserName() != null && myTRhipConnectParm.getCheckCode() != null) {
                GetBillInfoListReqDatas info = new GetBillInfoListReqDatas();
                info.setEmpi(preSettlementInput.getEmpi());
                info.setClinicNo(preSettlementInput.getClinicNo());
                info.setClinicType(preSettlementInput.getClinicType());
                info.setStatus(preSettlementInput.getStatus());
                info.setHosId(preSettlementInput.getHosId().toString());
                info.setMedicareType(11);
                info.setName(preSettlementInput.getName());
                info.setIdNumber(preSettlementInput.getIdNumber());
                info.setSocialNo(preSettlementInput.getSocialNo());
                info.setOrderId(preSettlementInput.getOrderId());
                info.setStatus(HisConvertConst.ReservationCode.BILL_STAUTS_NO_PAY_CODE);

                //兼容挂号缴费 haomeiling 2019-02-21
                info.setOrderDay(preSettlementInput.getOrderDay());
                info.setDeptCode(preSettlementInput.getDeptCode());
                info.setDoctorCode(preSettlementInput.getDoctorCode());
                info.setPeriodNo(preSettlementInput.getPeriodNo());
                info.setTimestypeNo(preSettlementInput.getTimestypeNo());


                String ws_res = geBillInfoListOperation.getDataFromHis(JSON.toJSONString(info), tRhipConnectParm);
                log.info("ws_Res: " + ws_res);
                //解析
                GetBillInfoListResDatas  infoRes = JSON.parseObject(ws_res, GetBillInfoListResDatas.class);


                if (infoRes != null) {
                    if (infoRes.getData() != null && infoRes.getData().size() > 0) {
                        GetBillInfoListItemResDatas item = infoRes.getData().get(0);
                        if (item != null) {
                            medicareInfo = item.getMedicareInfo();
                            if (medicareInfo != null && !medicareInfo.equals("")) {

                                String medicareType = medicareConvert(preSettlementInput.getClinicType());
                                log.info(preSettlementInput.getHosId().toString() + " ---- " + medicareType);
                                HospitalSiConfig hospitalSiConfig = tRhipHospitalSiConfigMapper.selectTRhipHospitalSiConfig(preSettlementInput.getHosId().toString(), medicareType);

                                PayInfo payInfo = hisSer.payBefore(hospitalSiConfig, preSettlementInput.getClinicNo(), preSettlementInput.getName(),
                                        preSettlementInput.getIdNumber(), preSettlementInput.getSocialNo(), medicareInfo, preSettlementInput.getOrderId(), infoRes.getMedicareMess());
                                //构建出参
                                preSettlementOutput.setSuccess(payInfo.getAppCode().equals(SI_SUCCESS) ? C_SUCCESS : C_FAIL);//payInfo的成功标志位 0：成功；1：失败
                                preSettlementOutput.setMsg(payInfo.getDetailMessage());
                                preSettlementOutput.setOperCode(HisConvertConst.Operation.PRE_SETTLEMENT);
                                if (payInfo.getAppCode().equals(SI_SUCCESS)) {
                                    preSettlementOutput.setRecordId(payInfo.getRecordId() + "");
                                    preSettlementOutput.setHosId(preSettlementInput.getHosId().toString());
                                    preSettlementOutput.setMedicareType(payInfo.getMedicareType());
                                    preSettlementOutput.setOverMoney(payInfo.getOverMoney());
                                    preSettlementOutput.setPayMoney(payInfo.getPayMoney());
                                    preSettlementOutput.setCashMoney(payInfo.getCashMoney());
                                    preSettlementOutput.setTotalMoney(payInfo.getTotalMoney());
                                }
                            } else {
                                preSettlementOutput.setSuccess(C_FAIL);
                                preSettlementOutput.setMsg("社保信息为空！");
                            }
                        } else {
                            preSettlementOutput.setSuccess(C_FAIL);
                            preSettlementOutput.setMsg("无法获取待缴费列表！");
                        }
                    } else {
                        preSettlementOutput.setSuccess(C_FAIL);
                        preSettlementOutput.setMsg(infoRes.getMsg());
                    }
                } else {
                    preSettlementOutput.setSuccess(C_FAIL);
                    preSettlementOutput.setMsg("调用his待缴费接口错误！");
                }
            } else {
                preSettlementOutput.setSuccess(C_FAIL);
                preSettlementOutput.setMsg("获取医院互联配置出错！");
            }
        } else {
            preSettlementOutput.setSuccess(C_FAIL);
            preSettlementOutput.setMsg("入参格式错误：" + check);
        }
        return JSON.toJSONString(preSettlementOutput);
    }


    private String checkParam(PreSettlementInput input) {
        String res = "";
        if (input == null) {
            res = "入参格式错误！";
        }
        if (input.getHosId() == null) {
            res = "入参医院ID不能为空！";
        }
        if (input.getEmpi() == null) {
            res = "入参患者院内编码不能为空！";
        }
        if (input.getClinicType() == null) {
            res = "入参诊疗类型不能为空！";
        }
        if (input.getStatus() == null) {
            res = "入参状态不能为空！";
        }
        if (input.getClinicNo() == null) {
            res = "入参诊疗编号不能为空！";
        }
        if (input.getName() == null) {
            res = "入参姓名不能为空！";
        }
        if (input.getIdNumber() == null) {
            res = "入参身份证号不能为空！";
        }
        if (input.getSocialNo() == null) {
            res = "入参身社保卡号不能为空！";
        }
        if (input.getOrderId() == null) {
            res = "入参订单号不能为空！";
        }
        if (medicareConvert(input.getClinicType()).equals("")) {
            res = "没有此医疗类别！";
        }

        return res;
    }

    private String medicareConvert(Integer clinicType) {
        String res;
        switch (clinicType) {
            case 1: {
                res = "11";
                break;
            }
            case 2: {
                res = "21";
                break;
            }
            case 5: {
                res = "11";
                break;
            }
            default:
                res = "";
                break;
        }
        return res;
    }
}
