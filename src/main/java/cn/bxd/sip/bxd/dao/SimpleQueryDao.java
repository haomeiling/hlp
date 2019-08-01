package cn.bxd.sip.bxd.dao;

import cn.bxd.sip.bxd.model.entity.Order;
import cn.bxd.sip.bxd.model.entity.RealUser;
import cn.bxd.sip.bxd.model.respond.common.HisDepartment;
import cn.bxd.sip.bxd.model.respond.common.HisDoctor;
import cn.bxd.sip.bxd.model.respond.common.Hospital;
import cn.bxd.sip.bxd.model.respond.reg.DoRegRecordInfo;
import cn.bxd.sip.bxd.model.respond.reg.PoState;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * author : cRyann
 *
 * @create 2018-09-03
 **/

@Mapper
public interface SimpleQueryDao {
    /**
     * @Param phone 电话号码
     * @Author cRyann
     * @Create 2018/9/12
     * @Description
     */
    @Select("SELECT t.open_id FROM t_rhip_user t WHERE t.login_mobile = #{phone}")
    String getUserCardNoByPhone(@Param(value = "phone") String phone);

    /**
     * @Param orderId 订单ID
     * @Author cRyann
     * @Create 2018/9/12
     * @Description
     */
    @Results(id = "getOrderByOrderId", value = {
            @Result(property = "orderId", column = "order_id", id = true),
            @Result(property = "payPattern", column = "pay_pattern"),
            @Result(property = "medicarePayState", column = "medicare_pay_state"),
            @Result(property = "payFlag", column = "pay_flag"),
            @Result(property = "payType", column = "pay_type"),
            @Result(property = "appId", column = "app_id"),
            @Result(property = "patientName", column = "patient_name")
    })
    @Select("SELECT * FROM T_RHIP_ORDER t WHERE t.ORDER_ID  = #{orderId}")
    Order getOrderByOrderId(@Param(value = "orderId") Long orderId);

    /**
     * @param hospitalId 医院ID
     * @author cRyann
     * @Description 获取平台部门ID  与医院部门ID 的关系列表
     **/
    @Results(id = "getDept", value = {
            @Result(property = "departmentId", column = "dept_no"),
            @Result(property = "organId", column = "dept_code")
    })
    @Select("SELECT * FROM T_RHIP_HOSPITAL_DEPT  t WHERE  t.hospital_id = #{hospitalId}")
    List<HisDepartment> getDept(@Param(value = "hospitalId") Long hospitalId);

    /**
     * @param hospitalId 医院ID
     * @param deptNo     平台部门ID
     * @author cRyann
     * @Description 获取医院部门ID
     **/
    @Select("SELECT t.dept_code FROM T_RHIP_HOSPITAL_DEPT  t WHERE  t.hospital_id = #{hospitalId} AND t.dept_no = #{deptNo}")
    String getDeptByDeptCode(@Param(value = "hospitalId") Long hospitalId, @Param(value = "deptNo") Long deptNo);

    /**
     * @Param hospitalId   医院ID
     * @Param deptCode     医院部门ID
     * @Author cRyann
     * @Create 2018/9/19
     * @Description 获取平台部门ID
     */
    @Select("SELECT t.dept_no FROM T_RHIP_HOSPITAL_DEPT  t WHERE  t.hospital_id = #{hospitalId} AND t.dept_Code = #{deptCode}")
    String getDeptByDeptNo(@Param(value = "hospitalId") Long hospitalId, @Param(value = "deptCode") Long deptCode);

    /**
     * @param hospitalId 医院ID
     * @author cRyann
     * @Description 获取 平台医生ID  与 医院医生ID 的关系列表
     **/
    @Results(id = "getDoctors", value = {
            @Result(property = "doctorId", column = "mei_no"),
            @Result(property = "account", column = "emei_no"),
            @Result(property = "specialty", column = "speciality")
    })
    @Select("SELECT * FROM T_RHIP_HOSPITAL_EMP  t WHERE  t.hospital_id = #{hospitalId}")
    List<HisDoctor> getDoctors(@Param(value = "hospitalId") Long hospitalId);


    /**
     * @param hospitalId  医院ID
     * @param patientNo   病人ID
     * @param orderTypeId 订单类型
     * @author cRyann
     * @Description 返回预约挂号记录
     **/
    @Results(id = "getDoRegRecordInfo", value = {
            @Result(property = "sourceMark", column = "order_source_id"),
            @Result(property = "organdoctorId", column = "doctor_code"),
            @Result(property = "organdoctorName", column = "doctor_name"),
            @Result(property = "departmentorganId", column = "dept_code"),
            @Result(property = "departmentName", column = "dept_name"),
            @Result(property = "visitAddress", column = "queue_addr"),
            @Result(property = "takeAddress", column = "queue_addr"),
            @Result(property = "sourceDate", column = "created_time", jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "timestypeNo", column = "order_period"),
            @Result(property = "consultationFee", column = "cash_money") // TODO: 参数待确认
    })
    @Select("SELECT * FROM t_rhip_order  t WHERE  t.hospital_id = #{hospitalId} " +
            "   AND t.patient_no = #{patientNo} " +
            "   AND t.order_type_id = #{orderTypeId} " +
            "   order by created_time ")
    List<DoRegRecordInfo> getDoRegRecordInfo(@Param(value = "hospitalId") Long hospitalId,
                                             @Param(value = "patientNo") String patientNo,
                                             @Param(value = "orderTypeId") Integer orderTypeId);

    @Results(id = "getDoRegAllRecordInfo", value = {
//            @Result(property = "state", column = "dept_code"),//挂号状态(0未使用，1已使用，2过期)
            @Result(property = "departmentNum", column = "dept_code"),
            @Result(property = "visitAddress", column = "queue_addr"),
            @Result(property = "takeAddress", column = "queue_addr"),
            @Result(property = "reservation", column = "order_source_id"),
            @Result(property = "sourceDateDb", column = "created_time", jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "sourceTimeType", column = "period_no"),         //1,上午，2，下午 3，晚上
            @Result(property = "timestypeNo", column = "order_period"),            //时间段标识
            @Result(property = "timestypeNoName", column = "order_period"),           //时间段显示名称
            @Result(property = "patientNo", column = "patient_no"),
            @Result(property = "patientName", column = "PATIENT_NAME"),           
            @Result(property = "departmentName", column = "DEPT_NAME"),           
            @Result(property = "doctorName", column = "DOCTOR_NAME"),           
            @Result(property = "regType", column = "ORDER_TYPE_ID")         
    })
    @Select("SELECT t.* FROM t_rhip_order  t WHERE  t.hospital_id = #{hospitalId} " +
            "   AND t.PATIENT_NO = #{certIdNo} " +
            "   AND t.order_type_id = #{orderTypeId} " +
            "   AND t.created_time  between to_date(#{startDate}||' 00:00:00','yyyy-mm-dd hh24:mi:ss') and to_date(#{endDate}||' 23:59:59','yyyy-mm-dd hh24:mi:ss')"+
            "   order by created_time ")
    List<PoState> getDoRegAllRecordInfo(@Param(value = "hospitalId") Long hospitalId,
                                        @Param(value = "certIdNo") String certIdNo,
                                        @Param(value = "orderTypeId") Integer orderTypeId,
                                        @Param(value = "startDate") String startDate,
                                        @Param(value = "endDate") String endDate);


    @Results(id = "getRealUserInfo", value = {
            @Result(property = "UserNo", column = "user_no"),
            @Result(property = "Name", column = "patient_name"),
            @Result(property = "IDNumber", column = "card_id"),
            @Result(property = "SocialNo", column = "visitcard_num"),
            @Result(property = "MobileNo", column = "mobile_no"),
            @Result(property = "RealTime", column = "real_time"),
            @Result(property = "CardInfo", column = "cardInfo"),
            @Result(property = "Area", column = "overall_area")
    })
    @Select("SELECT * FROM T_SI_REAL_USER t WHERE t.VISITCARD_NUM = #{visitcardNum} and t.mobile_no = #{mobileNo} AND ROWNUM = 1")
    RealUser getRealUser(@Param(value = "visitcardNum") String visitcardNum,
                         @Param(value = "mobileNo") String mobileNo);


    @Select("SELECT t.MOBILE_NO FROM T_SI_USER  t WHERE t.MOBILE_NO = #{mobileNo}")
    String isRegistered(@Param(value = "mobileNo") String mobileNo);

    @Results(id = "getHospitals", value = {
            @Result(property = "id", column = "hospital_id"),
            @Result(property = "name", column = "hospital_name"),
            @Result(property = "address", column = "hospital_address"),
            @Result(property = "phone", column = "contact_person_phone"),
            @Result(property = "photourl", column = "introduction_url"),
    })
    @Select("SELECT * FROM T_RHIP_HOSPITAL t ")
    List<Hospital> getHospitals();
}
