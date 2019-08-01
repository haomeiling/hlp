package cn.bxd.sip.bxd.model.entity;

import lombok.Data;
import org.apache.cxf.endpoint.Client;

import java.util.Date;
import java.util.Map;

/**
 * T_RHIP_CONNECT_PARM
 *
 * @author: Administrator
 * @Date: 2018-07-23
 */
@Data
public class ConnectParm {
    
    private Integer hospitalId;

    
    private Short connectType;

    
    private Object severName;

    
    private Object wsUrl;

    
    private Object queueName;

    
    private Object restUrl;

    

    private Object qmanagerName;

    

    private Object channelName;

    

    private Date lastLoginTime;

    
    private Date lastLogoutTime;

    
    private Long sessionSeqid;

    
    private Short onlinePay;

    
    private Object loginPassword;

    
    private Date onlinePayStartTime;

    
    private Date testPassedTime;

    
    private Short useSimpleQueueing;

    
    private Short useStandardQueueing;

    
    private Short unifiedDeptReg;

    
    private Short unifiedDoctorReg;

    
    private Object deptRegUrl;

    
    private Object doctorRegUrl;

    
    private Object hospitalRegUrl;

    
    private Object transServerUrl;

    
    private Short paidPriortoRegisitration;

    
    private Short leadingDays;

    
    private Short leadingMinutes;

    
    private Object reservationSourceUrl;

    
    private Object websiteHomeUrl;

    
    private Short privateSmsGateway;

    
    private Short checkClinicalNo;

    
    private Object reservationRedirectUrl;

    private String palmTerminalCode;//终端编号      lisheng 2019/4/11

    private long payNum;
    private String userName;
    private String checkCode;
    private String wsUrl2;
    private String wsUrl3;
    private String wsUrl4;
    private String wsUrl5;
    private String socialSecurityCode;
    private int wsFlag;

    //===================以下非数据库字段==================================
    private String subMchId;//子商户ID
    private String subProviderAppId;//子应用ID

    //动态配置客户端
    private String hisComSoap;
    private String hisUserSoap;
    private String regSoap;
    private String paySoap;
    private String inHosSoap;

    /**
     * wsClient
     */
    private Map<String, Object> hosWsClientMap;

    /**
     * Client
     */
    private Map<String, Client> hosWsCXFClientMap;
}