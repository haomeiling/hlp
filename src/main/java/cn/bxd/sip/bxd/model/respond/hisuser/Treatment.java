package cn.bxd.sip.bxd.model.respond.hisuser;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class Treatment {
    String diseaseName;
    String medicalOrg;
    String treatmentTime;
    String overalQuota;
    String overalPaymented;
    String availableBalance;

}
