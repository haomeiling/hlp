package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-02-15
 * Time: 9:38
 */
@Data
public class DoRegMedicareInfo {
    private String organdoctorId;//	医生编号
    private String departmentorganId;//	科室编号
    private String patientNo;//	患者编号
    private String socialsecurityNO;//	社保号
    private String sourceDate;//	取号日期（号源日期）
    private String timestypeNo;//	时间段标识 0：表示无时间段
    private String sourceTimeType;//	1：上午，2：下午，3：晚上
    private String medicareMess;//	医保个人基本信息（医保读卡交易返回值。根据 medicareType判断返回值格式）
    private String terminalCode;//	终端号
    private String medicareType;//	医保类型0：非医保,1：市医保,2：区医保,3：市统一结算
    private String terminalType;//	终端类型,1：手机app，2：自助终端


}
