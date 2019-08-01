package cn.bxd.sip.bxd.webservice.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.stereotype.Component;

import javax.jws.WebParam;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : cRyann
 * @create 2018-09-20
 **/
@Component
@Slf4j
public class WSTools {

    /**
     * @Param object 实例化的类，必须有接口，并且接口包含需要执行的方法
     * @Param 方法名称
     * @Param Map<String                                                                                                                               ,                                                                                                                                                                                                                                                               Object> <参数名称,参数值>
     * @Author cRyann
     * @Create 2018/9/22
     * @Description
     */
    public static Object invoke(Object object, String methodName, Map<String, Object> params)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clazz = object.getClass().getInterfaces()[0];
        Method methods[] = clazz.getMethods();
        Method method = null;
        for (Method m : methods) {
            if (m.getName().equals(methodName) && m.getTypeParameters().length <= params.size()) {
                method = m;
            }
        }

        if (method == null) {
            log.info("[{}]里，方法[{}]不存在！！！", clazz.getName(), methodName);
            return null;
        } else {
            //获取方法参数名称
            List<String> paramNames = new ArrayList<>();
            Annotation parameterAnnotations[][] = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (WebParam.class.equals(annotation.annotationType())) {
                        paramNames.add(((WebParam) annotation).name());
                    }
                }
            }

            // 获取参数、调整参数顺序
            Object inParams[] = new Object[paramNames.size()];
            for (int i = 0; i < paramNames.size(); i++) {
                // 如果params 不存在Key 则返回null
                inParams[i] = params.get(paramNames.get(i));
            }
            return MethodUtils.invokeMethod(object, methodName, inParams);
        }
    }


}
