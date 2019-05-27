package com.github.losemy.swaggerdubbo.register;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Description: utils
 * Created by lose on 19-4-12 下午9:56
 */
@Configuration
@Data
public class DubboSwaggerConfig {

    /**
     * appName信息
     */
    @Value("{app.name}")
    private String appName;

    /**
     * 是否注册 默认不注册
     */
    @Value("${swagger.dubbo.register:false}")
    private boolean register;

}
