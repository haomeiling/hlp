package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@Data
public class DoRegReq {
    private String organdoctorId;
    private String departmentorganId;
    private String patientNo;
    private String cardNo;
    private String sourceDate;
    private String timestypeNo;
    private String timestypeNoName;    //Lisheng  2018/9/28
    private String sourceTimeType;
    private String isToday;
    private String terminalCode;//终端编号
    private DoRegExtend extend;
    private String isDopay;

    private Payment paymentData;
    //当isDopay为1时，paymentData需传以下字段
    private String payType;
    private String payRecord;
    private String totalMoney;
    private String hasMPay;
    //hasMPay为1时，medicareData需传以下字段
    private String medicareMoney;//医保个账支付金额
    private String cashMoney;
    private String medicareRecord;//医保结算流水号
    private String overMoney;//统筹金额
    private String medicareReturn;
    private String userNo;
    private String medicareType;//医保类型0：非医保，1：市医保，2：区医保，3：市统一结算

    private String socialsecurityNO;//社保号

    //2019/1/21
    private String  patientName;//患者姓名
    private String  genderCode;//患者性别  0未知的性别,1男性,2女性,9未说明的性别
    private String  sourceCode;//预约来源  1微信,2支付宝,13云pos,14自助机


    //20190219
    private String payNo;//支付流水号
    private String payAmount;//已支付金额
    private String bankReturn;//银行返回信息

    private String patientTelephone;//联系电话 2019/6/29
    private String visitCardNo;//就诊卡号    2019/6/29

}