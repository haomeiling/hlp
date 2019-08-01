package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: 患者主索引查询接口
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPatientIndexQueryReqDates extends BaseReqDates {
    private String CertTypeCodel;       //身份证件类型
    private String CertTypeName;//身份证件类型名称
    private String IDNo;//身份证件号码
    private String PatientName;//患者姓名
    private String GenderID;//患者性别代码
    private String GenderName;//患者性别名称
    private String BirthDate;//出生日期(YYYYMMDD)
    private String MCertTypeCode;//母亲身份证件代码
    private String MIDNo;//母亲身份证件号码
    private String ChildNo;//胎次
    private String GMPI;//患者区域主索引号
    private String ClinicalCardNo;//诊疗卡号
    private String PatientNo;//患者编号
}