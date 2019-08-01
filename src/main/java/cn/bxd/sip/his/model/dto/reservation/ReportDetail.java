package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.util.List;

/**
 * @author：moqp
 * @Description：报告单详细的返回信息格式
 * @Date：2016/10/25 15:09.
 */
@Data
public class ReportDetail {

    private String RID;//报告单编号，报告单唯一标示
    private String SID;//申请单编号，申请单唯一标示
    private String EMPI;//患者主索引
    private String PatientName;//患者姓名
    private Long ApplyTime;//申请时间 YYYYMMDDHHMMSS
    private String Type;//申请类型
    private String ApplyDoctorName;//申请医生
    private String ApplyDeptName;//申请科室名称
    private String ClinicalNo;//诊疗编号
    private String Content;//申请检查内容
    private String Purpose;//申请目的
    private String SampleType;//标本类型
    private String SampleCode;//标本编号
    private Long DeliverTime;//签发时间
    private String DeliverDoctorName;//签发医师姓名
    private String DeliverDeptName;//签发科室名称
    private String QAName;//复核医师姓名
    private String ReportAbstract;//报告摘要
    private String ReportURI;//报文文件标识
    private Integer PartCount;//部位列表长度
    private List<PartItem> PartList;
    private Integer ItemCount;//项目数量
    private List<Item> ItemList;//项目列表



}
