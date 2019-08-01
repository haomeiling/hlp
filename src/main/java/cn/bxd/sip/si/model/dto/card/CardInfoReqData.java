package cn.bxd.sip.si.model.dto.card;

import lombok.Data;

/**
 * Description:
 * Package: cn.bxd.sip.si.model.dto.card
 *
 * @author Leeves
 * @version 1.0.0  2018-08-07
 */
@Data
public class CardInfoReqData {
    /** 医院编号 */
    private String HospitalNO;
    /** 读社保卡信息串 */
    private String CardInfo;
    /** 交易报文串 */
    private String InputData;
}