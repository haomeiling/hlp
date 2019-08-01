package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-02-14
 * Time: 10:29
 */
@Data
public class DoregFeeInterfaceRes {

   private String resultCode;//	00：表示成功， 01：表示失败
   private String resultMsg	;//	返回信息
   private String consultationFee;//	挂号费 sjh(获取收据号时返回sjh)

}
