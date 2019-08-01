package cn.bxd.sip.bxd.service.impl;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.dao.PatientMapper;
import cn.bxd.sip.bxd.model.entity.Patient;
import cn.bxd.sip.bxd.model.entity.UserPerson;
import cn.bxd.sip.bxd.service.IPatientService;
import cn.bxd.sip.bxd.util.IDCardUtils;
import cn.bxd.sip.bxd.var.CertType;
import cn.bxd.sip.bxd.var.ReservationVar;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 患者主索引
 *
 * @author HML
 * @Date 2016/1/27
 */
@Service
public class PatientService implements IPatientService {
    @Resource
    private PatientMapper patientDao;
    @Resource
    private SeqService seqService;

    @Override
    public Patient createPatient(UserPerson info) {
        Patient patient = new Patient(); //查询患者是否已经存在
        patient.setCertIdno(info.getCertIdno());
        patient.setPatientName(info.getPersonName());
        List<Patient> patientList = patientDao.selectByConditions(patient);

        if (null == patientList || patientList.size() == 0) {//创建患者主索引
            Date birthDateDate=info.getBirthDate();
            if(birthDateDate!=null) {
                Integer birthDate = TimeUtils.transDate2Int(birthDateDate);
                patient.setBirthDate(birthDate);
            }

            Date date = new Date();
            patient.setCreatedTime(date);

            patient.setCertIdno(info.getCertIdno());
            patient.setPatientName(info.getPersonName());
            patient.setGenderId(info.getGenderId());
            patient.setCertTypeId(info.getCertTypeId());

            long empiId = seqService.getPatientEMPIId();
            patient.setEmpiId((int) empiId);
            patient.setEmpiNo(String.valueOf(empiId));

            String cardNo=patient.getCertIdno();
            if ( cardNo!= null && !cardNo.equals("")) {
                Map<String, String> birAgeSex = IDCardUtils.getBirAgeSex(cardNo);
                String birthday = birAgeSex.get("birthday");
                patient.setBirthDate(TimeUtils.transDateStr2Int(birthday));
                //设置性别
                String sex = birAgeSex.get("sexCode");
                switch (sex) {
                    case "F":
                        patient.setGenderId(ReservationVar.Gender.WOMAN);
                        break;
                    case "M":
                        patient.setGenderId(ReservationVar.Gender.MAN);
                        break;
                    default:
                        patient.setGenderId(ReservationVar.Gender.UNKNOW);
                }
                //设置证件类型
                patient.setCertTypeId(CertType.ID_CARD.getCertTypeId());
            }

            patientDao.insert(patient);  //将患者主索引信息插入数据库
        } else {
            patient = patientList.get(0);
        }
        return patient;
    }


}
