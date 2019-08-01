package cn.bxd.sip.bxd.model;

public enum MedicareType {
	AREA("2","13"), CITY("1","11");
	
	private String siCode;
	
	private String hisCode;
	
	private MedicareType(String siCode, String hisCode) {
		this.hisCode = hisCode;
		this.siCode = siCode;
	}

	public String getSiCode() {
		return siCode;
	}

	public String getHisCode() {
		return hisCode;
	}
	
	
}
