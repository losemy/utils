package com.github.losemy.swaggerdubbo.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Description: utils
 * Created by lose on 19-4-12 下午9:05
 */
@Service
@Slf4j
public class RequestMappingService implements IRequestMappingService{

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    private BaseController baseController;

    @Autowired
    private DubboSwaggerConfig dubboSwaggerConfig;

    @Override
    public boolean hasApiRegistered(RegisterBean registerBean){

        String requestMapping = getRequestMapping(registerBean);

        RequestMappingHandlerMapping requestMappingHandlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            for(String pattern :info.getPatternsCondition().getPatterns()){
                // 匹配url
                if(pattern.equalsIgnoreCase(requestMapping)){
                    // 匹配requestMethod
                    if(info.getMethodsCondition().getMethods().contains(RequestMethod.POST)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void registerApi(RegisterBean registerBean){

        RequestMappingHandlerMapping requestMappingHandlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);

        RequestMappingInfo mappingInfo = getMappingInfo(registerBean);

        Method targetMethod = ReflectionUtils.findMethod(registerBean.getClass(), registerBean.getMethod());

        // 注册映射处理
        requestMappingHandlerMapping.registerMapping(mappingInfo, baseController, targetMethod);

    }


    @Override
    public void unregisterApi(RegisterBean registerBean){

        try {
            RequestMappingHandlerMapping requestMappingHandlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);

            RequestMappingInfo mappingInfo = getMappingInfo(registerBean);
            // 注销
            requestMappingHandlerMapping.unregisterMapping(mappingInfo);
        }catch (Exception e){
            log.error("unregisterApi",e);
        }

    }

    private String getRequestMapping(RegisterBean registerBean) {
        StringBuilder sb = new StringBuilder();
        sb.append(dubboSwaggerConfig.getAppName()).append("/");
        sb.append(registerBean.getFacade().getSimpleName()).append("/");
        sb.append(registerBean.getMethod());
        return sb.toString();
    }

    private RequestMappingInfo getMappingInfo(RegisterBean registerBean){
        String requestMapping = getRequestMapping(registerBean);

        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(requestMapping);
        RequestMethodsRequestCondition requestMethodsRequestCondition = new RequestMethodsRequestCondition(RequestMethod.POST);

        RequestMappingInfo mappingInfo = new RequestMappingInfo(patternsRequestCondition, requestMethodsRequestCondition, null, null, null, null, null);

        return mappingInfo;
    }


}

