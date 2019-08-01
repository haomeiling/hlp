package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@Data
public class QueryRegBypatientNoItemRes {

    private String sourceMark;
    private String organdoctorId;
    private String organdoctorName;
    private String departmentorganId;
    private String departmentName;
    private String sourceDate;
    private Integer sourceTimeType;
    private String consultationFee;
    private String timestypeNo;
    private String timestypeNoName;
    private String visitAddress;
    private String takeAddress;

}