package cn.bxd.sip.bxd.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 描述:t_si_medicare_records表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-07-26
 */
@Data
public class SiMedicareRecords {
    /**
     *
     */

    private Integer recordid;

    /**
     *
     */

    private Integer hospitalId;

    /**
     *
     */

    private String feeids;

    /**
     * 为社保卡信息（如读卡交易入参）
     */

    private String cardinfo;

    /**
     * 为[LICENSE] 下的这是个串 读出来拼成一个参数传到中心进行校验
     */

    private String license;

    /**
     * 生成时间
     */

    private Date createtime;

    /**
     * 交易状态（0未成功 ,1成功）
     */

    private Byte postate;

    /**
     * 本次医疗费用
     */

    private String totalmoney;

    /**
     * 统筹金额
     */

    private String overmoney;

    /**
     * 账户支出
     */

    private String paymoney;

    /**
     * 现金支出
     */

    private String cashmoney;

    /**
     * 患者编号
     */

    private String patientno;

    /**
     * 医保卡号
     */

    private String socialsecurityno;

    /**
     * 业务编号
     */

    private String businesscode;

    /**
     * 操作员编号
     */

    private String operatorcode;

    /**
     * 医院返回数据
     */

    private String hospitalinfo;

    /**
     * 回退返回
     */

    private String returninfo;

    /**
     * 1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、建行，7、中行
     */

    private String popaytype;

    /**
     * 是否进行统筹（0否，1是）
     */

    private String isoverall;

    /**
     * 订单号
     */

    private String pono;

    /**
     * 0自己，1第三方
     */

    private Integer ordertype;

    /**
     *
     */

    private Byte medicaretype;
    
    private String siFeeIds;// 社保_就诊流水号
	private String siPoNo;// 社保_单据号

    private String medicareInfo;
}
