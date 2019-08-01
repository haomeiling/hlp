package cn.bxd.sip.bxd.hispay.constant;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:15
 */
public class ResultCodeConstant {
    /** 事务处理成功 **/
    public static final String RESULTCODE_90000 = "90000";
    /** 消息格式错误，不是合法的HIESBMAG消息结构 **/
    public static final String RESULTCODE_90001 = "90001";
    /** 数字签名错误,消息已经被非法篡改 **/
    public static final String RESULTCODE_90002 = "90002";
    /** xml语法错误，通不过消息格式语法检查 **/
    public static final String RESULTCODE_90003 = "90003";
    /** 用户未登录 **/
    public static final String RESULTCODE_90004 = "90004";
    /** 目标系统不在线 **/
    public static final String RESULTCODE_90005 = "90005";
    /** 机构代码错误，不存在的机构代码 **/
    public static final String RESULTCODE_90006 = "90006";
    /** 系统代码错误 **/
    public static final String RESULTCODE_90007 = "90007";

    /** 就诊人信息不完整，拒绝申请号源 **/
    public static final String RESULTCODE_90201 = "90201";
    /** 号源因超时已被收回，无法绑定订单 **/
    public static final String RESULTCODE_90202 = "90202";
    /** 查询数据列表太长，请缩小查询范围 **/
    public static final String RESULTCODE_90203 = "90203";

    /** 平台系统处理程序出现异常 **/
    public static final String RESULTCODE_90101 = "90101";


}
