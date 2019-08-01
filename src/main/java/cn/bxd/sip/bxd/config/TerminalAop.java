package cn.bxd.sip.bxd.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : cRyann
 * @create 2018-08-31
 **/
@Aspect
@Component
@Slf4j
public class TerminalAop {

    @Pointcut("execution(public * cn.bxd.sip.bxd.webservice.*.*(..))")
    public void terminal() {
    }

    @Before("terminal()")
    public void doBefore(JoinPoint pjp) {
        try {
            log.debug("--->{}:{}", pjp.getSignature().getName(), Arrays.toString(pjp.getArgs()));
        } catch (Throwable e) {
            log.error("",e);
        }
    }

    @AfterReturning(value = "terminal()", returning = "result")
    public void doAfter(JoinPoint pjp, Object result) {
        try {
            log.debug("<---{}:{}", pjp.getSignature().getName(), JSON.toJSONString(result));
        } catch (Throwable e) {
            log.error("",e);
        }
    }
}
