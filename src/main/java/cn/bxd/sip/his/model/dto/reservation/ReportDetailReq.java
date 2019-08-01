package cn.bxd.sip.his.model.dto.reservation;


import cn.bxd.sip.his.model.dto.his.BaseData;
import lombok.Data;

/**
 * @author：moqp
 * @Description：报告单详细查询请求格式
 * @Date：2016/10/25 15:04.
 */
@Data
public class ReportDetailReq extends BaseData {

    private Integer hosId;//医院ID
    private String RID;//报告单编号
    private Integer Options;//选项  1：报告单摘要，2：部位信息，4：项目明细
    private String EMPI;//患者主索引   lisheng 2019/4/24
    private String Type;//报告类型 LIS,CT,MRI,US,DR,空表示全部  lisheng 2019/4/26
    private String reportDate;//检查时间 lisheng 2019/7/15


}
