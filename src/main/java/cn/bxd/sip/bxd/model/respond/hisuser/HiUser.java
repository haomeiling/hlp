package cn.bxd.sip.bxd.model.respond.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-17
 **/
@Data
public class HiUser {
    /**
     * 患者编号
     */
    String patientNo;
    /**
     * 患者姓名
     */
    String patientName;
    /**
     * 身份证号
     */
    String patientIdcardNo;
    /**
     * 社保号
     */
    String socialsecurityNO;
    /**
     * 姓别(0女，1男， 3表示未知 )
     */
    String patientSex;
    /**
     * 出生日期
     */
    String patientBirthday;
    /**
     * 门诊余额
     */
    String balance;
    /**
     * 联系电话
     */
    String patientTelephone;
}
