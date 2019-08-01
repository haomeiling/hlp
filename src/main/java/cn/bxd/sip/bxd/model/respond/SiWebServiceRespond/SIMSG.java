/**
 * Copyright 2018 bejson.com
 */
package cn.bxd.sip.bxd.model.respond.SiWebServiceRespond;

import com.alibaba.fastjson.JSONObject;

/**
 * Auto-generated: 2018-09-10 15:42:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SIMSG {

    private String DSIGN;
    private HEADER HEADER;
    private JSONObject BODY;

    public String getDSIGN() {
        return DSIGN;
    }

    public void setDSIGN(String DSIGN) {
        this.DSIGN = DSIGN;
    }

    public HEADER getHEADER() {
        return HEADER;
    }

    public void setHEADER(HEADER HEADER) {
        this.HEADER = HEADER;
    }

    public JSONObject getBODY() {
        return BODY;
    }

    public void setBODY(JSONObject BODY) {
        this.BODY = BODY;
    }

}