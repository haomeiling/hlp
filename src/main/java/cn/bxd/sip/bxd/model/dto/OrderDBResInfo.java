package cn.bxd.sip.bxd.model.dto;

/**
 * @author hml
 * @Date 2015/12/30
 */
public class OrderDBResInfo {
    private String msg = "";//返回信息
    private String code = "";//状态 处理结果 SUCCESS OR FAIL

    public OrderDBResInfo() {
    }

    public OrderDBResInfo(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
