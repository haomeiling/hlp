package cn.bxd.sip.bxd.webservice.impl.base;

import cn.bxd.sip.bxd.common.TimeUtils;
import cn.bxd.sip.bxd.dao.PixMapper;
import cn.bxd.sip.bxd.dao.UserMapper;
import cn.bxd.sip.bxd.dao.UserPersonMapper;
import cn.bxd.sip.bxd.model.entity.*;
import cn.bxd.sip.bxd.service.impl.PatientService;
import cn.bxd.sip.bxd.service.impl.SeqService;
import cn.bxd.sip.bxd.util.IDCardUtils;
import cn.bxd.sip.bxd.var.CertType;
import cn.bxd.sip.bxd.var.PatientType;
import cn.bxd.sip.bxd.var.ReservationVar;
import cn.bxd.sip.his.model.dto.platform.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2018-12-28
 * Time: 09:15
 */
@Service
@Slf4j
public abstract class UserAssistService {
    @Resource
    UserMapper userMapper;
    @Resource
    UserPersonMapper userPersonMapper;
    @Resource
    SeqService seqService;
    @Resource
    PatientService patientService;
    @Resource
    PixMapper pixMapper;

    /**
     * 用户和就诊人保存
     *
     * @param mobile
     */
    /**
     * @param mobile     手机号码
     * @param name       用户名
     * @param cardNo     身份证号
     * @param medicareNO 就诊卡号
     * @param patientNo  患者编号
     * @param address    患者住址
     * @return 用户ID
     */
    public Integer saveUserAndPerson(Integer hosId,
                                  String mobile, //电话号码
                                  String name, //姓名
                                  String cardNo, //身份证号码
                                  String medicareNO, //就诊卡号
                                  String patientNo,//患者编码
                                  String address) {//患者住址
        //查找用户是否存在
        User user = new User();
        user.setLoginMobile(mobile);
        User selectUser = userMapper.selectByCodeMobileEmail(user);

        //用户不存在，则创建用户
        if (selectUser == null) {
            //设置用户ID
            Integer userId = seqService.getUserId();
            user.setUserId(userId);
            //设置电话号码
            user.setLoginMobile(mobile);
            //设置创建时间
            user.setCreatedTime(new Date());
            //设置最近登录时间
            user.setLastLogonTime(new Date());
            //设置姓名
            user.setUserName(name);
            userMapper.insert(user);
        } else {
            user = selectUser;
        }

        //判断用户是否已存在
        UserPerson userPerson = new UserPerson();
        userPerson.setUserId(user.getUserId());
        userPerson.setPersonName(name);
        List<UserPerson> personList = userPersonMapper.selectByConditions(userPerson);

        if (personList != null && personList.size() > 0) {
            userPerson = personList.get(0);
            log.info("医院{} 电话号码{} 患者姓名{} 身份证号{} 就诊卡号{} 患者编码{} 平台用户ID{} 已存在",
                    hosId, mobile, name, cardNo, medicareNO, patientNo, userPerson.getUserId());
            return user.getUserId();
        }

        //用户不存在，创建用户
        userPerson.setCertIdno(cardNo);
        userPerson.setContactPhone(mobile);
        //设置生日
        if (cardNo != null && !cardNo.equals("")) {
            Map<String, String> birAgeSex = IDCardUtils.getBirAgeSex(cardNo);
            String birthday = birAgeSex.get("birthday");
            if(birthday!=null&&!birthday.equals("")) {
                userPerson.setBirthDate(TimeUtils.transDateStr2Date(birthday));
            }
            //设置性别
            String sex = birAgeSex.get("sexCode");
            switch (sex) {
                case "F":
                    userPerson.setGenderId(ReservationVar.Gender.WOMAN);
                    break;
                case "M":
                    userPerson.setGenderId(ReservationVar.Gender.MAN);
                    break;
                default:
                    userPerson.setGenderId(ReservationVar.Gender.UNKNOW);
            }
            //设置证件类型
            userPerson.setCertTypeId(CertType.ID_CARD.getCertTypeId());
        }
        //创建全局主索引表
        Patient patient = patientService.createPatient(userPerson);

        //先查询是否已经保存交叉主索引表
        PixKey pixKey = new PixKey();
        pixKey.setEmpiId(patient.getEmpiId());
        pixKey.setHospitalId(hosId);
        Pix selectPix = pixMapper.selectByPrimaryKey(pixKey);
        if (selectPix == null) { //保存患者交叉主索引表
            Pix pix = new Pix();
            pix.setHospitalId(hosId);
            pix.setEmpiId(patient.getEmpiId());
            pix.setClinicCardNo(medicareNO);
            pix.setHmpi(patientNo);
            pix.setPatientNo(patientNo);
            pixMapper.insert(pix);
        } else {
            selectPix.setPatientNo(patientNo);
            selectPix.setClinicCardNo(medicareNO);
            selectPix.setHmpi(patientNo);
            pixMapper.updateByPrimaryKey(selectPix);
        }

        //设置是否有就诊卡号
        boolean hasMedicareNO = medicareNO == null || medicareNO.equals("");
        userPerson.setIshaveCard(hasMedicareNO ? ReservationVar.Is.FALSE : ReservationVar.Is.TRUE);

        //默认为本人(1本人 2  他人  3 儿童)
        userPerson.setPatientType(PatientType.OWN.getTypeId());

        //保存用户表
        userPerson.setHomeAddress(address);
        userPerson.setEmpiId(Long.valueOf(patient.getEmpiId()));
        userPersonMapper.insert(userPerson);
        log.info("医院{} 电话号码{} 患者姓名{} 身份证号{} 就诊卡号{} 患者编码{} 平台用户ID{} 已成功创建",
                hosId, mobile, name, cardNo, medicareNO, patientNo, userPerson.getUserId());

        return user.getUserId();

    }
}
