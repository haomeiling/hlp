package cn.bxd.sip.bxd.model.respond.common;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-14
 **/
@Data
public class HisDoctor {
    String account;//医生工号
    String achievement;//荣誉成就
    String ddesc;//医生简介
    String departmentorganId;//科室编号
    String doctorId;    //平台医生ID
    String job;//医生职称
    String name;//医生姓名
    String organdoctorId;//医生编号
    String papersPublished;//发表过论文
    String peopleHomepage;//个人主页
    String photoUrl;//医生头像
    String socialResponsibility;//社会职责
    String specialty;//医生专长
    String specialtyName;//专科名称
    String treatdiseases;//治疗疾病
    Integer leaveNum;//
}
