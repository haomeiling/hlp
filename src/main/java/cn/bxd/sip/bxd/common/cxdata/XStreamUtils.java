package cn.bxd.sip.bxd.common.cxdata;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.lang.reflect.Field;

/**
 * Doc comment here
 *
 * @author HML
 * @Date 2016/3/25
 */
public class XStreamUtils {

    /**
     * Item不通用
     * 普通javabean转换
     *
     * @param itemClass 入参对象
     * @param obj       入参对象
     * @return 出参
     */
    public static String object2XML(Class itemClass, Object obj) {
        XStream xstream = XStreamUtils.createXstream();
        xstream.processAnnotations(obj.getClass());
        if (itemClass != null) {
            xstream.alias("Item", itemClass);
        }
        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性  
        String xml = xstream.toXML(obj);
        xml = xml.replace("__", "_");
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + xml;
    }


    /**
     * XML转对象
     *
     * @param clazz 对象类
     * @param xml   xml字符串
     * @param <T>   T
     * @return
     */
    public static <T> T parseFromXml(Class<T> clazz, String xml) {
        //创建解析XML对象
        XStream xStream = new XStream(new DomDriver());
        //处理注解
        xStream.processAnnotations(clazz);
        @SuppressWarnings("unchecked")
        //将XML字符串转为bean对象
                T t = (T) xStream.fromXML(xml);
        return t;
    }


    public static XStream createXstream() {
        return new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    boolean cdata = false;
                    Class<?> targetClass = null;

                    @Override
                    public void startNode(String name,
                                          @SuppressWarnings("rawtypes") Class clazz) {
                        super.startNode(name, clazz);

                        if (!name.equals("xml") &&
                                !name.equals("HEADER") &&
                                !name.equals("BODY") &&
                                !name.equals("SIMSG") &&
                                !name.equals("DSIGN")) {
                            cdata = needCDATA(targetClass, name);
                        } else {
                            targetClass = clazz;
                        }
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[" + text + "]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
    }


    private static boolean needCDATA(Class<?> targetClass, String fieldAlias) {
        boolean cdata = false;
        //first, scan self
        cdata = existsCDATA(targetClass, fieldAlias);
        if (cdata) return cdata;
        //if cdata is false, scan supperClass until java.lang.Object
        Class<?> superClass = targetClass.getSuperclass();
        while (!superClass.equals(Object.class)) {
            cdata = existsCDATA(superClass, fieldAlias);
            if (cdata) return cdata;
            superClass = superClass.getClass().getSuperclass();
        }
        return false;
    }

    ;

    private static boolean existsCDATA(Class<?> clazz, String fieldAlias) {
        //scan fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //1. exists XStreamCDATA
            if (field.getAnnotation(XStreamCDATA.class) != null) {
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
                //2. exists XStreamAlias
                if (null != xStreamAlias) {
                    if (fieldAlias.equals(xStreamAlias.value()))//matched
                        return true;
                } else {// not exists XStreamAlias
                    if (fieldAlias.equals(field.getName()))
                        return true;
                }
            }
        }
        return false;
    }

}
