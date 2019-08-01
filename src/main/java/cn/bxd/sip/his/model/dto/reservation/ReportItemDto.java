package cn.bxd.sip.his.model.dto.reservation;

import lombok.Data;

//报告单项目实体对象
@Data
public class ReportItemDto {

	private Integer accDate; // 账务日期（YYYYMMDD）
	private String ItemCode; // 项目编码
	private String ItemName; // 项目名称
	private String Unit; // 计量单位
	private String RefValue; // 参考值
	private String Value; // 结果值


}

