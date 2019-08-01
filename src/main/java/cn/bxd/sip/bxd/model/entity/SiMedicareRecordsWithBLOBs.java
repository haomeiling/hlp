package cn.bxd.sip.bxd.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SiMedicareRecordsWithBLOBs extends SiMedicareRecords {
    /**
     * 为发送请求的入参串(在接口设计方案中有详细描述)
     */
    private String inputstr;

    /**
     * 医保返回数据
     */

    private String medicareinfo;

    /**
     * 退款参数（对参数用<<|>>）分隔
     */
    private String refundparam;
}
