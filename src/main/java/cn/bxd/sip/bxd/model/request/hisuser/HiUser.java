package cn.bxd.sip.bxd.model.request.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class HiUser {
    /**
     * 患者编号
     **/
    String patientNo;//
    /**
     * 患都姓名
     **/
    String patientName;//
    /**
     * 身份证号
     **/
    String patientIdcardNo;//
    /**
     * 社保号
     **/
    String socialsecurityNO;//
    /**
     * 姓别( 0女，1男，3表示未知)
     **/
    Integer patientSex;//
    /**
     * 出生日期
     **/
    String patientBirthday;//
    /**
     * 门诊余额
     **/
    Float balance;//
    /**
     * 家庭住址
     **/
    String address;//
    /**
     * 联系电话
     **/
    String patientTelephone;//
    /**
     * 人员类别（默认为”3”，表示未知）
     **/
    String personCategory;//
    /**
     * 银行卡号
     **/
    String bankCardNumber;//
    /**
     * 就诊卡号
     **/
    String medicareNO;//
    /**
     * Json值
     **/
    Extend extend;//

}
