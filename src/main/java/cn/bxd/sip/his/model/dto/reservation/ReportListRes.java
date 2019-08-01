package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

import java.util.List;

/**
 * @author：moqp
 * @Description：报告单返回的列表数据格式
 * @Date：2016/10/25 14:30.
 */
@Data
public class ReportListRes extends BaseResDates{
   private List<ReportListItem> data	;//	Json字符串


}
