package com.github.losemy.swaggerdubbo.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: utils
 * Created by lose on 19-4-12 下午10:19
 */
@Configuration
public class RegisterConfig {


    /**
     * 初始化 获取所有的bean
     */
    @Bean
    public DubboService dubboService(){

        return DubboService.getInstance();
    }



}
