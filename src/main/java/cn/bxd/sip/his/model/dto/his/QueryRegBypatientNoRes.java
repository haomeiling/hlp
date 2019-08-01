package cn.bxd.sip.his.model.dto.his;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description:  查询未取号的预约挂号
 * Package: cn.bxd.sip.his.model.dto.his
 *
 * @author Leeves
 * @version 1.0.0  2018-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryRegBypatientNoRes extends BaseRes  {

    private List<QueryRegBypatientNoItemRes> hiRegisterRecordList;

}