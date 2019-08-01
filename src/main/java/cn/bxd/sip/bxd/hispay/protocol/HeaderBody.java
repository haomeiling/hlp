package cn.bxd.sip.bxd.hispay.protocol;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @Description TODO
 * @Package com.efc.reservation.model.extend.clinic_no
 * @Anthor Leeves
 * @Data 2017/10/17
 */
@XStreamAlias("HIESBMSG")
public class HeaderBody {

	private Header HEADER;

	private Object BODY;

	private String DSIGN;

	public Header getHEADER() {
		return HEADER;
	}

	public void setHEADER(Header HEADER) {
		this.HEADER = HEADER;
	}

	public Object getBODY() {
		return BODY;
	}

	public void setBODY(Object bODY) {
		BODY = bODY;
	}

	public String getDSIGN() {
		return DSIGN;
	}

	public void setDSIGN(String dSIGN) {
		DSIGN = dSIGN;
	}
}
