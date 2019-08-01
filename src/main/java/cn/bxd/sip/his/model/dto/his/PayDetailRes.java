package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description: 住院清单明细
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayDetailRes extends BaseRes{
    private List<PayDetailItemRes> payList;
}