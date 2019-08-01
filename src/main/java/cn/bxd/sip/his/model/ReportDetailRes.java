package cn.bxd.sip.his.model;

import cn.bxd.sip.his.model.dto.reservation.BaseResDates;
import cn.bxd.sip.his.model.dto.reservation.ReportDetail;
import lombok.Data;

/**
 * @author：moqp
 * @Description：报告单返回的列表数据格式
 * @Date：2016/10/25 14:30.
 */
@Data
public class ReportDetailRes extends BaseResDates {
   private ReportDetail data	;//	Json字符串


}
