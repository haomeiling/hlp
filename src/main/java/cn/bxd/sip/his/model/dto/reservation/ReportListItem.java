package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * @author：moqp
 * @Description：报告单列表项的定义
 * @Date：2016/10/25 14:32.
 */
@Data
public class ReportListItem {
    private String RID;//报告单编号，报告单唯一标示
    private String SID;//申请单编号，申请单唯一标示
    private Long ApplyTime;//申请时间 YYYYMMDDHHMMSS
    private String Type;//报告单类型
    private String Content;//申请检查内容，申请项目名称
    private String Purpose;//申请目的，申请检查目的
    private String Parts;//检查部位，检查部位或方式
    private String SampleType;//标本类型
    private String SampleCode;//标本编号
    private String ClinicalNo;//诊疗编号
    private String EMPI;//患者主索引
    private String PatientName;//患者姓名
    private Long DeliverTime;//签发时间
    private String DoctorName;//签发医师姓名
    private String DeptName;//签发科室名称
    private Long QATime;//复核时间
    private String QAName;//复合医师姓名
    private String Status;//报告单状态(0:未报告，1:待审查，2:已签发，3:已作废)
}
