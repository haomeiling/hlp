package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.util.List;

//报告单明细详情实体对象
@Data
public class ReportDetailDto {
	private String RID; // 报告单唯一标识
	private String SID; // 申请单唯一标识
	private String EMPI; // 患者主索引
	private String PatientName; // 患者姓名
	private Long ApplyTime; // 申请时间(YYYYMMDDHHMMSS)
	private String Type; // 报告单类型
	private String ApplyDoctorName; // 申请医生姓名
	private String ApplyDeptName; // 申请科室名称
	private String ClinicalNo; // 诊疗编号
	private String Content; // 申请项目名称
	private String Purpose; // 申请检查目的
	private String Parts; // 检查部位或方式
	private String SampleType; // 标本类型
	private String SampleCode; // 标本编号
	private Long DeliverTime; // 签发时间(YYYYMMDDHHMMSS)
	private String DeliverDoctorName; // 签发医师姓名
	private String DeliverDeptName; // 签发科室名称
	private String QAName; // 复核医师姓名
	private String ReportAbstract; // 报告摘要
	private String ReportURI; // 报告文件标识
	private Integer PartCount; // 部位列表长度
	private List<ReportPartDto> PartList; // 部位列表
	private Integer ItemCount; // 项目数量
	private List<ReportItemDto> ItemList; // 项目列表
}
