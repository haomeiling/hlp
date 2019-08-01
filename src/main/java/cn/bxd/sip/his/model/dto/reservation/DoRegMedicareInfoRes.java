package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-02-15
 * Time: 10:29
 */
@Data
public class DoRegMedicareInfoRes {

   private String resultCode;//	00：表示成功， 01：表示失败
   private String resultMsg	;//	返回信息
   private String medicareInfo;//	挂号医保信息

}
