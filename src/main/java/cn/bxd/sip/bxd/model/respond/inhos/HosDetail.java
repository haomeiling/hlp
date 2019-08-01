package cn.bxd.sip.bxd.model.respond.inhos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:     1.6.7	住院清单明细
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 16:31
 * 图片类型
 * chargeDate	String	否	记账日期（yyyy-MM-dd）
 * contentSrc	Float	否	清单下载路径
 * downPicSrc	String	否	清单内网路径（终端试用）
 * 非图片类型
 * chargeDate	String	否	记账日期（yyyy-MM-dd）
 * drugType	String	否	药品分类
 * projectCode	String	否	项目编码
 * projectName	String	否	项目名称
 * feeItemAmount	float	否	单价
 * feeItemNum	Integer	否	数量
 * feeItemUnit	String	否	单位：如 包 、盒等
 * feeItemAllAmount	Double	否	总价
 * feeItemStandard	String	否	规格
 * payDate	String	否	扣费时间（yyyy-MM-dd）
 * amountMoney	float	否	可记账金额
 * MmedicareMoney	float	否	医保记账自费部分
 * payMoney	float	否	纯自费金额
 */
@Data
public class HosDetail {
    private String chargeDate;//记账日期（yyyy-MM-dd）
    private String contentSrc;//清单下载路径
    private String downPicSrc;//清单内网路径（终端试用）非图片类型
    private String drugType;//药品分类
    private String projectCode;//项目编码
    private String projectName;//项目名称
    private String feeItemAmount;//单价
    private Integer feeItemNum;//数量
    private String feeItemUnit;//单位：如 包 、盒等
    private String feeItemAllAmount;//总价
    private String feeItemStandard;//规格
    private String payDate;//扣费时间（yyyy-MM-dd）
    private String amountMoney;//可记账金额
    private String medicareMoney;//医保记账自费部分
    private String payMoney;//纯自费金额
    private String feeType;//费用类型
    private String medType;//医保类型（甲类、乙类、丙类）
    private String ncmsType;//新农合类别（国基）
    private String exeDept;//执行科室
    private String isDailyPrint;//一日清单打印状态
    private String isSummaryPrint;//汇总清单打印状态
    private String isOutHosPrint;//出院清单打印状态

}
