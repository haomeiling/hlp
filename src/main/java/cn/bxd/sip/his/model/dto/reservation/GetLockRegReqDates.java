package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 取号接口
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetLockRegReqDates extends BaseReqDates {
    private String queueId;
    private String clinicDate;
    private Integer clinicDateInt;
    private String periodNo;
    private String token;
    private String source;
    private String empi;//院内主索引
    private String gmpi;//全局主索引
    private String timesTypeNo;
    private String timestypeNoName;
    private String  telephone;

    //2019/01/21
    private String  patientName;//患者姓名
    private String  genderCode;//患者性别  0未知的性别,1男性,2女性,9未说明的性别
    private String  sourceCode;//预约来源  1微信,2支付宝,13云pos,14自助机
    private String  cardNo;//患者身份证号

    //2019/6/29  lisheng
    private String  visitCardNo;//就诊卡号

}