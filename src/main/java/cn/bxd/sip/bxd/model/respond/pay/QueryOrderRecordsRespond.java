package cn.bxd.sip.bxd.model.respond.pay;

import cn.bxd.sip.bxd.model.respond.BaseRespond;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author : lishang
 * @create 2019/4/12
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrderRecordsRespond extends BaseRespond {
   private List<TransRecord> transRecord;

}
