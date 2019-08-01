package cn.bxd.sip.his.model.dto.reservation;

import cn.bxd.sip.his.model.dto.his.BaseData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author：haomeiling
 * @Description：诊疗记录
 * @Date：2018/09/23 9:57.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetClinicRecordReqData extends BaseData {

    private Integer hosId;//医院ID(医疗机构ID)
    private String EMPI = "";//患者主索引，空表示所有患者
    private String ClinicalNo;//诊疗编号(诊疗记录标志)
    private Integer StartDate = 0;//开始时间 20160817
    private Integer EndDate = 0;//结束时间 20160827
    private String Type;//报告类型 LIS,CT,MRI,US,DR,空表示全部

}
