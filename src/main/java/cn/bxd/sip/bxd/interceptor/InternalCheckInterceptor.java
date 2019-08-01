package cn.bxd.sip.bxd.interceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 白名单拦截
 * @Description:
 * @author: chenchuanchuan 
 * @date:   2018年11月8日 下午5:34:15
 */
@Component
public class InternalCheckInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(InternalCheckInterceptor.class);
	
	public static List<String> ips = Arrays.asList("127.0.0.1", "172.16.11.11", "");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (!isInternal(request)) {
             throw new RuntimeException("no permission int");
        }
        return true;
    }
    
    public static boolean isInternal(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        LOGGER.info("获取到的ip地址："+ remoteAddr);
        return ips.contains(remoteAddr);
    }
}
