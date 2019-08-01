package cn.bxd.sip.bxd.model.entity;

import lombok.Data;

/**
 * 
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年11月29日 上午10:00:16
 */
@Data
public class HospitalDept extends HospitalDeptKey {
    private Object deptName;//科室名称
    private Short displayOrder;//显示顺序
    private Short authorizedBeds;//编制床位
    private Short openedBeds;//开放床位
    private Object deptAddr;//科室地址
    private Object contactPhone;//联系电话
    private Object deptHead;//科室负责人
    private Short deptTypeId;//科室门类代号
    private Short isPromotionDept;//是否推荐科室
    private Object introductionUrl;//科室简介URL
    private Object deptCode;//科室唯一编码
    private Short disable;//是否禁用
    private Object registrationUrl;
    private Short outpatientScheduled;
}