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
public class CardInfoResData {
    /** 状态码 */
    private String AppCode;
    /** 返回报文XML内容 */
    private String OutputData;
}