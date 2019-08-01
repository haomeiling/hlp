package cn.bxd.sip.bxd.hispay.exception;

/**
 * Description:@Description：通用系统应答（929912）
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:43
 */
public class SystemException extends Exception{
    /**
     * 结果代码
     */
    private String resultCode;

    private static final long serialVersionUID = 1L;

    public SystemException() {

    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, String resultCode) {
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
