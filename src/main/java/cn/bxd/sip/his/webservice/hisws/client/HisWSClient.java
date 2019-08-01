package cn.bxd.sip.his.webservice.hisws.client;


import cn.bxd.sip.bxd.config.ReqInterceptor;
import cn.bxd.sip.bxd.config.ResInterceptor;
import cn.bxd.sip.his.comm.HisConvertConst;
import cn.bxd.sip.his.webservice.hisws.invoke.HISInterfaceQkb;
import cn.bxd.sip.his.webservice.hisws.invoke.HISInterfaceQkbSoap;
import cn.bxd.sip.his.webservice.hisws.invoke2.Service;
import cn.bxd.sip.his.webservice.hisws.invoke2.ServiceSoap;
import cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.HisCom;
import cn.bxd.sip.his.webservice.hisws.invoke3.hiscom.HisComSoap;
import cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.HisUser;
import cn.bxd.sip.his.webservice.hisws.invoke3.hisuser.HisUserSoap;
import cn.bxd.sip.his.webservice.hisws.invoke3.inhos.InHos;
import cn.bxd.sip.his.webservice.hisws.invoke3.inhos.InHosSoap;
import cn.bxd.sip.his.webservice.hisws.invoke3.pay.Pay;
import cn.bxd.sip.his.webservice.hisws.invoke3.pay.PaySoap;
import cn.bxd.sip.his.webservice.hisws.invoke3.reg.Reg;
import cn.bxd.sip.his.webservice.hisws.invoke3.reg.RegSoap;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.phase.Phase;

import javax.xml.ws.BindingProvider;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;

/**
 * Description: web service client
 * Package: com.bxd.sip.reservation.hisws.client
 *
 * @author Leeves
 * @version 1.0.0  2018-07-09
 */
@Slf4j
public class HisWSClient {

    private static final String CONNECTION_TIMEOUT = "javax.xml.hisws.client.connectionTimeout";

    private static final String RECEIVE_TIMEOUT = "javax.xml.hisws.client.receiveTimeout";

    //5秒
    private static long timeOut = 5 * 1000;

    /**
     * 通用版本
     * public static  final String HIS_INTERFACE_QKB_SOAP="HISInterfaceQkbSoap";
     * public static  final String SERVICE_SOAP="ServiceSoap";
     * public static  final String HIS_COM_SOAP="HisComSoap";
     * public static  final String HIS_USER_SOAP="HisUserSoap";
     * public static  final String IN_HOS_SOAP="InHosSoap";
     * public static  final String PAY_SOAP="PaySoap";
     * public static  final String REG_SOAP="RegSoap";
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象
     */
    public static Object getHisWSClientSoap(String soapName, String hisWSUrl) {
        Object object = null;
        switch (soapName) {
            case HisConvertConst.Soap.HIS_INTERFACE_QKB_SOAP:
                object = getHisWSClientSoap(hisWSUrl);
                break;

            case HisConvertConst.Soap.SERVICE_SOAP:
                object = getServiceSoap(hisWSUrl);
                break;

            case HisConvertConst.Soap.HIS_COM_SOAP:
                object = getHisComSoap(hisWSUrl);
                break;

            case HisConvertConst.Soap.HIS_USER_SOAP:
                object = getHisUserSoap(hisWSUrl);
                break;

            case HisConvertConst.Soap.IN_HOS_SOAP:
                object = getInHosSoap(hisWSUrl);
                break;

            case HisConvertConst.Soap.PAY_SOAP:
                object = getPaySoap(hisWSUrl);
                break;

            case HisConvertConst.Soap.REG_SOAP:
                object = getRegSoap(hisWSUrl);
                break;
        }

         return object;

    }


    /**
     * 动态加载类名和方法
     *
     * @param object     对象实例
     * @param methodName 方法名称
     * @param var3       可变参数
     * @return json字符
     */
    public static String invoke(Object object, String methodName, Object... var3) {
        try {
            //通过名字获取类的方法
            Class<?> aClass = object.getClass();
            Method[] declaredMethods = aClass.getDeclaredMethods();
            Method method = null;
            for (Method m : declaredMethods) {
                if (m.getName().equals(methodName)) {
                    method = m;
                    break;
                }
            }
            //兼容处理
            if (method == null) {
                return null;
            }

            //通过方法获取参数个数
            Class<?>[] parameterTypes = method.getParameterTypes();
            //设置参数长度
            int parameterTypesLength = parameterTypes.length;
            //通过参数个数封装可变参数(解决参数不一致造成的错误问题)
            Object[] args = new Object[parameterTypesLength];
            for (int i = 0; i < parameterTypesLength; i++) {
                args[i] = var3[i];
            }
            //调用
            Object invoke = method.invoke(object, args);
            //返回出参
            String result=invoke.toString();
            return result;

        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error("",e);
        }
        return null;
    }

    /**
     * 通用版本
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象 HISInterfaceQkbSoap
     */
    private static HISInterfaceQkbSoap getHisWSClientSoap(String hisWSUrl) {
        HISInterfaceQkbSoap hs;
        try {
            HISInterfaceQkb hisInterfaceQkb = new HISInterfaceQkb(new URL(hisWSUrl));
            hs = hisInterfaceQkb.getHISInterfaceQkbSoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }
        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, 3000);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, 3000);
        return hs;
    }

    /**
     * 通用版本
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象 HISInterfaceQkbSoap
     */
    private static ServiceSoap getServiceSoap(String hisWSUrl) {
        ServiceSoap hs;
        try {
            Service service = new Service(new URL(hisWSUrl));
            hs = service.getServiceSoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }

        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, timeOut);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, timeOut);
        return hs;
    }

    /**
     * 兼容旧版本服务
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象
     * @date 20181007
     */
    private static HisComSoap getHisComSoap(String hisWSUrl) {
        HisComSoap hs;
        try {
            HisCom hisCom = new HisCom(new URL(hisWSUrl));
            hs = hisCom.getHisComSoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }

        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, timeOut);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, timeOut);
        return hs;
    }

    /**
     * 兼容旧版本服务
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象
     * @date 20180919
     */
    private static HisUserSoap getHisUserSoap(String hisWSUrl) {
        HisUserSoap hs;
        try {
            HisUser hisUser = new HisUser(new URL(hisWSUrl));
            hs = hisUser.getHisUserSoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }

        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, timeOut);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, timeOut);
        return hs;
    }

    /**
     * 兼容旧版本服务
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象
     * @date 20180919
     */
    private static InHosSoap getInHosSoap(String hisWSUrl) {
        InHosSoap hs;
        try {
            InHos inHos = new InHos(new URL(hisWSUrl));
            hs = inHos.getInHosSoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }

        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, timeOut);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, timeOut);
        return hs;
    }

    /**
     * 兼容旧版本服务
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象
     * @date 20180919
     */
    private static PaySoap getPaySoap(String hisWSUrl) {
        PaySoap hs;
        try {
            Pay pay = new Pay(new URL(hisWSUrl));
            hs = pay.getPaySoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }

        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, timeOut);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, timeOut);
        return hs;
    }

    /**
     * 兼容旧版本服务
     *
     * @param hisWSUrl HIS地址
     * @return 返回对象
     * @date 20180919
     */
    private static RegSoap getRegSoap(String hisWSUrl) {
        RegSoap hs;
        try {
            Reg reg = new Reg(new URL(hisWSUrl));
            hs = reg.getRegSoap();
            Client client = ClientProxy.getClient(hs);
            client.getOutInterceptors().add(new ReqInterceptor(Phase.SEND));
            client.getInInterceptors().add(new ResInterceptor(Phase.POST_INVOKE));
        } catch (Exception e) {
            log.error("",e);
            log.info("webservice客户端初始化异常，地址:" + hisWSUrl + "  异常原因:" + e.getMessage());
            return null;
        }

        ((BindingProvider) hs).getRequestContext().put(CONNECTION_TIMEOUT, timeOut);
        ((BindingProvider) hs).getRequestContext().put(RECEIVE_TIMEOUT, timeOut);
        return hs;
    }


}