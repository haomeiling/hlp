package cn.bxd.sip.bxd.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

/**
 * author : cRyann
 *
 * @create 2018-08-30
 **/
public class JsonTools {

    public static <T> T json2Bean(String str, Class<T> clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T t = JSON.parseObject(str, clazz);
        Map map = PropertyUtils.describe(t);
        Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();
            BeanUtils.setProperty(t, entry.getKey(), entry.getValue() == null ? "?" : entry.getValue());
        }
        return t;
    }
    
    public static <T> T gJson2Bean(String str, Class<T> clazz) {
        return new Gson().fromJson(str, clazz);
    }
    
    public static String obj2Json(Object obj) {
    	return new Gson().toJson(obj);
    }

}
