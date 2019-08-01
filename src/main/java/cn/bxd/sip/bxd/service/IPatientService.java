package cn.bxd.sip.bxd.service;

import cn.bxd.sip.bxd.model.entity.Patient;
import cn.bxd.sip.bxd.model.entity.UserPerson;

/**
 * Doc comment here
 *
 * @author HML
 * @Date 2016/1/27
 */
public interface IPatientService {
    /**
     * 创建就诊人的主索引
     *
     * @param info
     * @return
     */
    Patient createPatient(UserPerson info);
}
