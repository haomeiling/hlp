package cn.bxd.sip.bxd.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 输出封装对象
 * @Version 1.0
 * @Author haomeiling
 * @Date 2018-03-02
 */
@XStreamAlias( "SIMSG" )
public class HeaderBody {
	
	@XStreamAlias( "HEADER" )
	private Header HEADER;
	@XStreamAlias( "BODY" )
	private Object BODY;
	@XStreamAlias( "DSIGN" )
	private String DSIGN;
	
	public Header getHEADER( ){
		return HEADER;
	}
	
	public void setHEADER( Header HEADER ){
		this.HEADER = HEADER;
	}
	
	public String getDSIGN( ){
		return DSIGN;
	}
	
	public void setDSIGN( String dSIGN ){
		DSIGN = dSIGN;
	}
	
	public Object getBODY( ){
		return BODY;
	}
	
	public void setBODY( Object BODY ){
		this.BODY = BODY;
	}
}
