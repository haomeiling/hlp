package cn.bxd.sip.bxd.model.respond.inhos;

import lombok.Data;

/**
 * Description:         1.6.10	查询出院清单
 * User: LiSheng
 * Date: 2018-09-13
 * Time: 17:23
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
 * payTime	String	否	缴费时（yyyy-MM-dd HH:mm:ss）
 * amountMoney	float	否	可记账金额
 * MmedicareMoney	float	否	医保记账自费部分
 * payMoney	float	否	纯自费金额
 */
@Data
public class LeaveHosDetail {

    String chargeDate;//记账日期（yyyy-MM-dd）
    String contentSrc;//清单下载路径
    String downPicSrc;//清单内网路径（终端试用）

    String drugTyp;//药品分类
    String projectCode;//项目编码
    String projectName;//项目名称
    String feeItemAmount;//单价
    String feeItemNum;//数量
    String feeItemUnit;//单位：如 包 、盒等
    String feeItemAllAmount;//总价
    String feeItemStandard;//	规格
    String payDate;//	扣费时间（yyyy-MM-dd）
    String payTime;//	缴费时（yyyy-MM-dd HH:mm:ss）
    String amountMoney;//可记账金额
    String medicareMoney;//	医保记账自费部分
    String payMoney;//	纯自费金额
    private String feeType;//费用类型
    private String medType;//医保类型（甲类、乙类、丙类）
    private String ncmsType;//新农合类别（国基）
    private String exeDept;//执行科室
    

}
