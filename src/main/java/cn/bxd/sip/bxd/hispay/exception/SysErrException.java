package cn.bxd.sip.bxd.hispay.exception;

/**
 * Description: 系统异常应答
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:14
 */
public class SysErrException extends Exception{
    /**
     * 该返回哪种结果代码
     */
    private String resultCode;

    private static final long serialVersionUID = 1L;

    public SysErrException() {

    }

    public SysErrException(String message) {
        super(message);
    }

    public SysErrException(String message, String resultCode) {
        super(message);
        this.setResultCode(resultCode);
    }

    /**
     * 异常描述输出
     */
    public String toString() {
        String msg = getMessage();
        if (msg == null && getCause() != null) {
            msg = getCause().getMessage();
        }
        return msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
