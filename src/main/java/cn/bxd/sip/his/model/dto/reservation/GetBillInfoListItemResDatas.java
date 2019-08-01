package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@Data
public class GetBillInfoListItemResDatas {

    private Integer accDate;  //账务日期（YYYYMMDD）
    private String billNo;  //单据单号
    private Integer billDate;  //单据日期（YYYYMMDD）
    private String billType;  //单据类型
    private String doctorName;  //开单医生
    private String deptName;  //科室名称
    private String clinicalNo;  //诊疗编号
    private BigDecimal billAmount;  //费用总金额
    private String medicareInfo;
    private String diagInfo;
    private String medicareType;
    private List<GetBillInfoListItemDetailResDatas> data;//子项明细列表

}