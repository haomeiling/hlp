package cn.bxd.sip.bxd.model.respond.pay;

import lombok.Data;

/**
 * @author : cRyann
 * @create 2018-09-12
 **/
@Data
public class HiFeeItem {
    String feeItemName;
    Double feeItemAmount;
    String feeItemNum;
    String feeItemUnit;
    Double feeItemAllAmount;
    String feeItemStandard;
    String cateId;
    String cateName;
    String exDepartment;
    String exDepartmentID;
    String exDepartmentAdd;
    String feeType;
    String exeDept; 
}
