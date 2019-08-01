package cn.bxd.sip.bxd.hispay.common.cxdata;

import cn.bxd.sip.bxd.hispay.protocol.payscan.PayProvider;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;

/**
 * Doc comment here
 *
 * @author HML
 * @Date 2016/3/25
 */
public class XStreamUtils {

	public static String object2XML(Object obj)
			throws UnsupportedEncodingException {
		XStream xstream = XStreamUtils.createXstream();
		xstream.processAnnotations(obj.getClass());
		xstream.aliasSystemAttribute(null, "class"); // ȥ�� class ����
		xstream.alias("ProviderItem", PayProvider.class);//
		String xml = xstream.toXML(obj);
		xml = xml.replace("__", "_");
		return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + xml;
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

						if (!name.equals("xml") && !name.equals("HEADER")
								&& !name.equals("BODY")
								&& !name.equals("HIESBMSG")
								&& !name.equals("DSIGN")) {
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
		// first, scan self
		cdata = existsCDATA(targetClass, fieldAlias);
		if (cdata)
			return cdata;
		// if cdata is false, scan supperClass until java.lang.Object
		Class<?> superClass = targetClass.getSuperclass();
		while (!superClass.equals(Object.class)) {
			cdata = existsCDATA(superClass, fieldAlias);
			if (cdata)
				return cdata;
			superClass = superClass.getClass().getSuperclass();
		}
		return false;
	}

	;

	private static boolean existsCDATA(Class<?> clazz, String fieldAlias) {
		// scan fields
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 1. exists XStreamCDATA
			if (field.getAnnotation(XStreamCDATA.class) != null) {
				XStreamAlias xStreamAlias = field
						.getAnnotation(XStreamAlias.class);
				// 2. exists XStreamAlias
				if (null != xStreamAlias) {
					if (fieldAlias.equals(xStreamAlias.value()))// matched
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
