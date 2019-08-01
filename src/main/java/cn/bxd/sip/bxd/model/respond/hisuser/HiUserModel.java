package cn.bxd.sip.bxd.model.respond.hisuser;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : cRyann
 * @create 2018-09-10
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class HiUserModel extends BaseRespond {
    String patientNo= "";
    String patientName= "";
    String patientIdcardNo= "";
    String socialsecurityNO= "";
    Integer patientSex;
    String patientBirthday= "";
    String balance= "";
    String patientTelephone= "";
    String resultCode= "";
    String resultMsg= "";
    String personCategory= "";
    String medicareNO= "";
    String clinicNo= "";
    String extend= "";
    String address= "";
    String balanceSpecified= "";
    String bankCardNumber= "";
    String doType= "";
    String passWord= "";
    String patientSexSpecified= "";
    String payAmount= "";
    String payNo= "";
    String payType= "";
    String userNo = "";
}