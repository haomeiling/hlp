package cn.bxd.sip.his.model.dto.his;

import lombok.Data;

import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-07-16
 */
@Data
public class QueryToPayRecipeInfoListItem {
    private String hiFeeNo;
    private String patientNo;
    private String patientName;
    private String organdoctorId;
    private String doctorName;
    private String organName;
    private String serialNumber;
    private String visitDate;
    private String settleType;
    private String settleAmount;
    private String patientAmount;
    private Short status;
    private String isPrint;
    private String createTime;
    private String medicareInfo;
    private String diagInfo;
    private String medicareType;
    private String reservation;
    private String hiType;
    private String pharmacist;
    private String pharmacistId;
    private String extend;
    private List<QueryToPayRecipeInfoListHiFeeItem> hiFeeItem;
}