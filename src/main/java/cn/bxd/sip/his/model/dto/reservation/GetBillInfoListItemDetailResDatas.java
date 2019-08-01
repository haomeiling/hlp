package cn.bxd.sip.his.model.dto.reservation;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.his.model.dto.reservation
 *
 * @author Leeves
 * @version 1.0.0  2018-07-18
 */
@Data
public class GetBillInfoListItemDetailResDatas {

    private Integer lineNo;  //行号
    private String feeCode;  //收费项目编码
    private String name;   //明细名称
    private String spec;   //规格
    private String unit;   //计量单位
    @JSONField
    private Double total;  //数量
    private Double price;  //单价
    private Double amount;  //明细金额
    private String manufacturer;  //生产企业
}