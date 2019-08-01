package cn.bxd.sip.bxd.hispay.factory.impl;

import cn.bxd.sip.bxd.hispay.common.MapUtils;
import cn.bxd.sip.bxd.hispay.exception.BusinessException;
import cn.bxd.sip.bxd.hispay.export.IExportService;
import cn.bxd.sip.bxd.hispay.protocol.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Map;

/**
 * Description:
 * User: HaoMeiLing
 * Date: 2019-04-18
 * Time: 16:11
 */
@Slf4j
public abstract class BaseSynOutOperation extends BaseXmlParse {
    protected static final String SUCEESS = "SUCCESS";
    protected static final String FAIL = "FAIL";
    protected static final String WAITING = "WAITING";

    @Autowired
    protected IExportService exportService;// 对外请求出口服务

    /**
     * 操作完成后返回map
     */
    protected Map<String, Object> outMap = null;

    @Override
    public String operateRequest(String inMsgStr) throws BusinessException, Exception {
        if (log.isDebugEnabled())
            log.debug("his调用业务处理开始");

        String outMsgStr = "";

        // 解析xml
        parse(inMsgStr);

        // 向数据平台进行请求
        String resStr = toPlatForm(map);

        // TODO: 判断是否产生错误，进行错误处理

        // //生成xml
        // outMsgStr = GenerateXmlUtil.generateXml(outMap);

        if (log.isDebugEnabled())
            log.debug("his调用业务处理结束");

        return resStr;
    }

    /**
     * 真正业务操作
     *
     * @param inMsgMap
     * @return
     * @throws SQLException
     */
    protected abstract String toPlatForm(Map<String, Object> inMsgMap) throws BusinessException, Exception;


    //将map转换为javaBean对象
    public Header getHeader(Map<String, Object> inMsgMap) throws BusinessException {
        try {
            // 构造消息头的Map对象
            Map<String, Object> headMap = (Map<String, Object>) ((Map) inMsgMap.get("HIESBMSG")).get("HEADER");
            return MapUtils.map2Java(new Header(), headMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("数据转换异常");
        }
    }

    public <T> T getBody(T t, Map<String, Object> inMsgMap) throws BusinessException {
        try {
            // 获取请求消息的消息体
            Map<String, Object> reqBodyMap = (Map<String, Object>) ((Map) inMsgMap.get("HIESBMSG")).get("BODY");
            return MapUtils.map2Java(t, reqBodyMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("数据转换异常");
        }
    }
}
