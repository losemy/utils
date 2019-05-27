package com.github.losemy.swaggerdubbo.register;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: utils
 * Created by lose on 19-4-12 下午9:33
 */
@Data
public class RegisterBean {

    /**
     * 对应facade
     */
    private Class facade;
    /**
     * 方法名称 原则上不支持 同名方法
     * 可以考虑支持nickName 参数
     */
    private String method;
    /**
     * 请求方法 默认POST
     */
    private RequestMethod requestMethod = RequestMethod.POST;

}
