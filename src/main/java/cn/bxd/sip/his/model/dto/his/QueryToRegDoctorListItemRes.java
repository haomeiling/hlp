package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: com.bxd.sip.reservation.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Data
public class QueryToRegDoctorListItemRes {
    /** 医生编号 */
    private String organdoctorId;
    /** 科室编号 */
    private String departmentorganId;
    /** 号源日期 */
    private String sourceDate;
    /** 早上挂号费 */
    private String consultationFee;
    /** 早上号源数
     （ -1显示未排班  0显示约满）
     */
    private int morningNum;
    /** 下午挂号费 */
    private String afternoonFee;
    /** 下午号源数
     （ -1显示未排班  0显示约满）
     */
    private int afternoonNum;
    /** 晚上挂号费 */
    private String eveningFee;
    /** 晚上号源数
     （ -1显示未排班  0显示约满）
     */
    private int eveningNum;
}