package cn.bxd.sip.bxd.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:反射工具类
 * User: HaoMeiLing
 * Date: 2018-10-12
 * Time: 09:30
 */
@Slf4j
public class ReflectUtils {
    /**
     * 对象属性值拷贝
     *
     * @param sourceObject 原对象
     * @param targetObject 目标对象
     */
    public static void copyFieldValue(Object sourceObject, Object targetObject) {
        try {
            //获取原对象以及原对象父类的所有属性
            List<Field> sourceObjectDeclaredFields = getDeclaredFields(sourceObject);
            for (Field field : sourceObjectDeclaredFields) {
                //获取目标属性字段
                Field targetField =getDeclaredField(targetObject,field.getName());
                if(targetField==null) continue;
                targetField.setAccessible(true);
                field.setAccessible(true);
                targetField.set(targetObject, field.get(sourceObject));
            }
        } catch (Exception e) {
            log.error("",e);
        }
    }



    /**
     * 循环向上转型, 获     * @param object : 子类对象
     *
     * @param methodName     : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @return 父类中的方法对象
     */

    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }

        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected, default)
     *
     * @param object         : 子类对象
     * @param methodName     : 父类中的方法名
     * @param parameterTypes : 父类中的方法参数类型
     * @param parameters     : 父类中的方法参数
     * @return 父类中方法的执行结果
     */

    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes,
                                      Object[] parameters) {
        //根据 对象、方法名和对应的方法参数 通过取 Method 对象
        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        //抑制Java对方法进行检查,主要是针对私有方法而言
        method.setAccessible(true);

        try {
            if (null != method) {

                //调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        } catch (IllegalArgumentException e) {
            log.error("",e);
        } catch (IllegalAccessException e) {
            log.error("",e);
        } catch (InvocationTargetException e) {
            log.error("",e);
        }

        return null;
    }

    /**
     * 通过反射获取objec 以及父类的方法和属性
     * @param object  对象
     * @return  属性列表
     */
    public static List<Field> getDeclaredFields(Object object) {
        List<Field> fieldList = new ArrayList<>();
        Class tempClass = object.getClass();
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        for (Field f : fieldList) {

            log.debug("getAllFields", "getFields---" + f.getName());
        }
        return fieldList;
    }

    /**
     * 循环向上转型, 获     * @param object : 子类对象
     *
     * @param fieldName : 父类中     * @return 父类中
     */

    public static Field getDeclaredField(Object object, String fieldName) {
        Field field;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里甚么都不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会进入
            }
        }

        return null;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也     * @param object : 子类对象
     *
     * @param fieldName : 父类中     * @param value : 将要设置的值
     */

    public static void setFieldValue(Object object, String fieldName, Object value) {

        //根据 对象和属性名通过取 Field对象
        Field field = getDeclaredField(object, fieldName);

        //抑制Java对其的检查
        field.setAccessible(true);

        try {
            //将 object 中 field 所代表的值 设置为 value
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            log.error("",e);
        } catch (IllegalAccessException e) {
            log.error("",e);
        }

    }

    /**
     * 直接读的属性值, 忽略 private/protected 修饰符, 也     * @param object : 子类对象
     *
     * @param fieldName : 父类中     * @return : 父类中
     */

    public static Object getFieldValue(Object object, String fieldName) {

        //根据 对象和属性名通过取 Field对象
        Field field = getDeclaredField(object, fieldName);

        //抑制Java对其的检查
        field.setAccessible(true);

        try {
            //获的属性值
            return field.get(object);

        } catch (Exception e) {
            log.error("",e);
        }

        return null;
    }

}
