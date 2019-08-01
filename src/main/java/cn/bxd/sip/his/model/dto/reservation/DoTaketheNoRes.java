package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

/**
 * Description:
 * User: LiSheng
 * Date: 2019-01-16
 * Time: 10:29
 */
@Data
public class DoTaketheNoRes{

   private String departmentNum;//就诊科室
   private String takeNo;//取号号码（叫号码）
   private String medicalCode;//医院就医码
   private String resultCode;//状态码 00：取号成功， 01：已取号
   private String resultMsg;//返回信息

}
