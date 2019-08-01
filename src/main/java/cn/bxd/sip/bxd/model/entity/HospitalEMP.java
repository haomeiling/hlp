package cn.bxd.sip.bxd.model.entity;

import lombok.Data;

/**
 * Description:机构人员信息表
 *
 * @author laingshs
 *         <p/>
 *         2015年7月10日 上午10:07:57
 */
@Data
public class HospitalEMP extends HospitalEMPKey {
    private Object meiNo;//人员唯一编码
    private Object empName;//姓名
    private Short genderId;//性别代号
    private Short professionId;//工种代号
    private Short firstWorkYear;//首次参加工作年份
    private Integer hiredDate;//聘用日期
    private Object careerCertNo;//执业资格证号
    private Integer countryId;//国家地区代号
    private Short qualificationId;//职称代号
    private Short certIdno;//身份证件号码
    private Short certTypeId;//身份证件类型代号
    private Short careerId;//职业代号
    private Short degreeId;//学位代号
    private Short isPromotionDoctor;//是否推荐专家
    private Object socialTitles;//社会兼职
    private Object speciality = "";//专业特长
    private String majorIn;//主攻方向
    private Object introductionUrl;//专家简介URL
    private Object empLogoUrl;//头像logo
    private Short eduBackgroundId;//学历代号
    private Integer birthDate;//出生日期
    private Integer deptNo;//机构科室代号
    private String emeiNo;//医院内部医生主索引
    private String registrationUrl;//接入地址
    private Short disable;//禁用标识
}