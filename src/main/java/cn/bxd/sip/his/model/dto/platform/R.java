package cn.bxd.sip.his.model.dto.platform;


import cn.bxd.sip.his.comm.HisConvertConst;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:  返回信息类
 * Package: com.bxd.medicalinsurance.model.payBiz
 *
 * @author Leeves
 * @version 1.0.0  2018-08-18
 */

public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put(HisConvertConst.RETRUN_CODE, HisConvertConst.RETRUN_SUCCESS_CODE);
        put(HisConvertConst.RESULT_CODE, HisConvertConst.RESULT_SUCCESS_CODE);
		put(HisConvertConst.RETRUN_MSG, "处理成功");
	}

	public static R sysError() {
		return sysError(HisConvertConst.RETRUN_FAIL_CODE, "处理失败");
	}

	public static R sysError(String msg) {
		return sysError(HisConvertConst.RETRUN_FAIL_CODE, msg);
	}

    public static R sysError(String code, String msg) {
        R r = new R();
        r.put(HisConvertConst.RETRUN_CODE, code);
        r.put(HisConvertConst.RETRUN_MSG, msg);
        return r;
    }

	public static R resultError() {
		return resultError(HisConvertConst.RETRUN_FAIL_CODE, "处理失败");
	}

	public static R resultError(String msg) {
		return resultError(HisConvertConst.RETRUN_FAIL_CODE, msg);
	}

	public static R resultError(String code, String msg) {
		R r = new R();
		r.put(HisConvertConst.RETRUN_CODE, HisConvertConst.RETRUN_SUCCESS_CODE);
        r.put(HisConvertConst.RESULT_CODE, code);
		r.put(HisConvertConst.RETRUN_MSG, msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
        r.put(HisConvertConst.RESULT_CODE, HisConvertConst.RESULT_SUCCESS_CODE);
		r.put(HisConvertConst.RETRUN_MSG, msg);
		return r;
	}

    public static <T> R<T> ok(T t){
        R<T> r = new R<>();
        r.put(HisConvertConst.RESULT_CODE, HisConvertConst.RESULT_SUCCESS_CODE);
        r.put(HisConvertConst.RETRUN_MSG,t);
        return r;
    }

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	@Override
	public R put(String key, Object value){
		super.put(key,value);
		return this;
	}

	public static R ok() {
		return new R();
	}


    public static void main(String[] args) {
        R ok = R.ok("123");
        System.out.println(ok);
/*        R ok = R.ok("123");
        String r = ok.get("returnMsg");
        System.out.println(r);*/
    }
}
