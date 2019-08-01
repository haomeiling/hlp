package cn.bxd.sip.bxd.model.dto.pay;


import cn.bxd.sip.bxd.var.PayConst;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: TODO: 返回信息类
 * Package: com.bxd.medicalinsurance.model.payBiz
 *
 * @author Leeves
 * @date 2018-01-14
 */

public class R<T> extends HashMap<String, Object> {

	public R() {
		put(PayConst.RETRUN_CODE, PayConst.RETRUN_SUCCESS_CODE);
		put(PayConst.RETRUN_MSG, "处理成功");
	}

	public static R sysError() {
		return sysError(PayConst.RETRUN_FAIL_CODE, "处理失败");
	}

	public static R sysError(String msg) {
		return sysError(PayConst.RETRUN_FAIL_CODE, msg);
	}

    public static R sysError(String code, String msg) {
        R r = new R();
        r.put(PayConst.RETRUN_CODE, code);
        r.put(PayConst.RETRUN_MSG, msg);
        return r;
    }

	public static R resultError() {
		return resultError(PayConst.RETRUN_FAIL_CODE, "处理失败");
	}

	public static R resultError(String msg) {
		return resultError(PayConst.RETRUN_FAIL_CODE, msg);
	}
	
	public static R resultErr(String msg) {
		R r = new R();
		r.put(PayConst.RETRUN_CODE, PayConst.RETRUN_FAIL_CODE);
        r.put(PayConst.RESULT_CODE, PayConst.RESULT_FAIL_CODE);
		r.put(PayConst.RETRUN_MSG, msg);
		r.put(PayConst.RESULT_CODE, msg);
		return r;
	}

	public static R resultError(String code, String msg) {
		R r = new R();
		r.put(PayConst.RETRUN_CODE, PayConst.RETRUN_SUCCESS_CODE);
        r.put(PayConst.RESULT_CODE, code);
		r.put(PayConst.RETRUN_MSG, msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
        r.put(PayConst.RESULT_CODE, PayConst.RESULT_SUCCESS_CODE);
        r.put(PayConst.RETRUN_CODE, PayConst.RETRUN_SUCCESS_CODE);
		r.put(PayConst.RETRUN_MSG, msg);
		return r;
	}

    public static <T> R<T> ok(T t){
        R<T> r = new R<>();
        r.put(PayConst.RESULT_CODE, PayConst.RESULT_SUCCESS_CODE);
        r.put(PayConst.RETRUN_MSG,t);
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
