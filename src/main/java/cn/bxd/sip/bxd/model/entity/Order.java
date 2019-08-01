package cn.bxd.sip.bxd.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 描述:T_RHIP_ORDER表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-07-19
 */
@Data
public class Order {
        private Long orderId;//订单流水号

        private Long originOrderId;//原订单流水号

        private Date createdTime;//订单时间

        private Integer orderDay;//预订日期

        private String orderPeriod;//预订时间段

        private Integer userId;//账户ID

        private Integer hospitalId;//机构代号

        private Short certTypeId;//身份证件类型代号

        private String certIdno;//身份证件号码

        private String patientName;//姓名

        private Short genderId;//性别代号
        //订单类型:1 预约订单、 2 缴费订单(诊间支付)、3 预交订单(预交金)、4 改签订单、5 取消订单 6 签到订单（预约转挂号） 7 挂号订单(挂号订单)
        private Short orderTypeId;//订单类型代号

        private Short deptTypeId;//科室门类代号

        private String deptCode;//科室唯一编码

        private String deptName;//科室名称

        private String doctorCode;//医生唯一编码

        private String doctorName;//医生名称

        private Short qualificationId;//职称代号

        private BigDecimal amount;//挂号费

        private Short status;//订单状态

        private String clinicNo;//诊疗主记录流水号

        private Date actualTime;//就诊时间

        private Long transId;//交易流水号

        private Integer clinicDate;//就诊日期

        private String queueId;//排队队列ID

        private Short periodNo;//时段序号

        private Integer periodStart;//时段起始时间

        private Integer periodEnd;//时段终止时间

        private String queueNo;//排队序号

        private String appId;//应用程序代号（微信公众号ID或者支付宝服务窗ID）

        private Short orderSourceId;//订单来源代号

        private Short normalRescheduled;//正常改签次数

        private Short expiredRescheduled;//过期改签次数

        private Short payFlag;//订单支付标志位

        private Short reviewFlag;//订单评价标志位

        private Date lastUpdatedTime;//最后变更时间

        private Date validatedTime;//订单确认时间

        private Date canceledTime;//订单取消时间

        private String canceledReason;//取消原因

        private Short isAdditionNo;//是否追加号源

        private String peerOrderNo="";//医院内部订单编号

        private String displayQueueNo;//显示排队号

        private Long waitingOrderID;//待完成订单流水号

        private String queueAddr;//排队地址

        private Short sentFlag;//发送标志位

        private Long initialOrderID;//原始订单流水号

        private String verificationCode;//验证码

        private String casherName;//操作员

        private String casherCode;//操作码

        private String payBody;//描述

        //整合社保缴费增加的字段20180725
        private Short payType;//现金支付类型 1微信，2支付宝 , 3现场支付, 4医保账户，5银联，6建行，7中行,8柳行,9招商

        private Long isOverAll;//描述

        private String socialNo;//社保卡号

        private String patientNo;//患者编码

        private Short medicareType;//医保类型  1,市医保  2,区医保

        private Date medicarePayTime;//医保支付时间

        private Short medicarePayState;//医保支付状态：0,未支付，1，支付成功，2失败

        private Short payPattern;//支付方式  11自费，12社保，20混合

        private Long medicareRecordId;//医保流水号

        private String cashMoney="";//现金金额

        private String medicareMoney;//个账金额

        private String overAllMoney;//统筹金额

        private String accType;//卡类型

        private String cardName;//所属卡银行
        
        private String medicalCode;//医院就医码
        
        private String clearingNO;//医院结算流水号
        
        private String dispensaryWin;//发药窗口
        
        private String guidelinesInfo;//指引信息

        //===============以下问题非数据库字段======================================
        private Date systemDate;//数据库系统时间
        private String hisDeptCode;//医院的code

        private String genderCode;//性别
        private String sourceCode;//来源编码
        
        /** 20190222 chencc**/
        private String terminalCode;//终端号


        
        
        

		public Order() {
		}
		
		public Order(Long orderId, Short status) {
			this.orderId = orderId;
			this.status = status;
		}
		
		

		public Order(Long orderId, Integer hospitalId, String certIdno, String patientName, Short orderTypeId,
				Short payType, String socialNo, String patientNo, Short medicareType, String cashMoney) {
			this.orderId = orderId;
			this.hospitalId = hospitalId;
			this.certIdno = certIdno;
			this.patientName = patientName;
			this.orderTypeId = orderTypeId;
			this.payType = payType;
			this.socialNo = socialNo;
			this.patientNo = patientNo;
			this.medicareType = medicareType;
			this.cashMoney = cashMoney;
		}

		public Order(Long orderId, String medicalCode, String clearingNO, String dispensaryWin, String guidelinesInfo) {
			this.orderId = orderId;
			this.medicalCode = medicalCode;
			this.clearingNO = clearingNO;
			this.dispensaryWin = dispensaryWin;
			this.guidelinesInfo = guidelinesInfo;
		}

    public Order(Long orderId, String queueNo,String peerOrderNo, String displayQueueNo, String queueAddr, Short sentFlag,Date validatedTime ) {
        this.orderId = orderId;
        this.queueNo = queueNo;
        this.peerOrderNo = peerOrderNo;
        this.displayQueueNo = displayQueueNo;
        this.queueAddr = queueAddr;
        this.sentFlag = sentFlag;
        this.validatedTime = validatedTime;
    }
}