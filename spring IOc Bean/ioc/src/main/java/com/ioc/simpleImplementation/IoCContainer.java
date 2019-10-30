package com.ioc.simpleImplementation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IoC容器
 * 1. 实例化Bean
 * 2. 保存Bean
 * 3. 提供Bean
 *
 * 4. 每一个Bean要有唯一对应的id
 */
public class IoCContainer {

    private Map<String, Object> beans = new ConcurrentHashMap<String, Object>();

    /**
     * 获取Bean
     * @param beanId
     * @return
     */
    public Object getBean(String beanId) {
        return beans.get(beanId);
    }

    /**
     *
     * @param beanId
     * @param clazz 要创建的Class
     * @param paramBeanIds 要创建Class的构造方法依赖的Beanids
     */
    public void setBean(String beanId, Class<?> clazz, String... paramBeanIds) {
        //1. 组装构造方法所需要的参数
        Object[] paramValues = new Object[paramBeanIds.length];
        for (int i = 0; i < paramBeanIds.length; i++) {
            paramValues[i] = beans.get(paramBeanIds[i]);
        }
        //2. 调用构造方法实例化Bean
        Object bean = null;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            try {
                bean = constructor.newInstance(paramValues);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            if (bean == null) {
                throw new RuntimeException("找不到合适的构造方法去实例化Bean");
            }
        }
        //3. 将实例化的Bean放入Beans
        beans.put(beanId, bean);
    }

}
