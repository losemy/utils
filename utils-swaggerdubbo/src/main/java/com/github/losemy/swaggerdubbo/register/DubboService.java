package com.github.losemy.swaggerdubbo.register;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: utils
 * Created by lose on 19-4-12 下午10:15
 */
@Slf4j
public class DubboService {

    private static Collection<ServiceBean> services;

    private static Map<Class<?>, Object> interfaceMapRef = new ConcurrentHashMap<Class<?>, Object>();

    private static DubboService instance;

    public synchronized static DubboService getInstance() {
        if (null != instance) {
            return instance;
        }
        instance = new DubboService();
        services = new HashSet<ServiceBean>();
        try {
            Field field = SpringExtensionFactory.class.getDeclaredField("contexts");
            field.setAccessible(true);
            Set<ApplicationContext> contexts = (Set<ApplicationContext>)field.get(new SpringExtensionFactory());
            for (ApplicationContext context : contexts){
                services.addAll(context.getBeansOfType(ServiceBean.class).values());
            }
        } catch (Exception e) {
            log.error("Get All Dubbo Service Error", e);
            return instance;
        }
        for (ServiceBean<?> bean : services) {
            interfaceMapRef.putIfAbsent(bean.getInterfaceClass(), bean.getRef());
        }

        return instance;
    }


    public Map<Class<?>, Object> getInterfaceMapRef() {
        return interfaceMapRef;
    }

    public Map.Entry<Class<?>, Object> getRef(String interfaceClass) {
        Set<Map.Entry<Class<?>, Object>> entrySet = interfaceMapRef.entrySet();
        for (Map.Entry<Class<?>, Object> entry : entrySet) {
            if (entry.getKey().getName().equals(interfaceClass)) { return entry; }
        }
        return null;
    }


}
