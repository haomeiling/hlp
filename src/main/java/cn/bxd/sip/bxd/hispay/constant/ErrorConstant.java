package cn.bxd.sip.bxd.hispay.constant;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 15:28
 */
public class ErrorConstant {
    /** 调用、查询出错 */
    /** 数据查询出错 */
    public static String ERROR_SQL_QUERY = "-4000：查询数据库出错";
    /** 存储过程调用出错 */
    public static String ERROR_PROCEDURE_CALL = "-4001：存储过程调用出错";

    /** WebService调用失败 */
    public static String ERROR_WEBSERVICE_CALL = "-4002：WebService调用失败";
    /** restful调用失败 */
    public static String ERROR_RESTFUL_CALL = "-4003：restful调用失败";
    /** JMS调用失败 */
    public static String ERROR_JMS_CALL = "-4004：JMS调用失败";

    /** 解析、生成出错 */
    /** -4100：xml生成失败 */
    public static String ERROR_GENERATE_XML = "-4100：xml生成失败";
    /** -4101：RSA加签失败 */
    public static String ERROR_ADD_SIGN = "-4101：RSA加签失败";
    /** -4102：RSA验签失败 */
    public static String ERROR_VERIFY_SIGN = "-4102：RSA验签失败";
}
