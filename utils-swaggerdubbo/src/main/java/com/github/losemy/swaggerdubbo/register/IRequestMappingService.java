package com.github.losemy.swaggerdubbo.register;

/**
 * Description: utils
 * Created by lose on 19-4-12 下午9:04
 */
public interface IRequestMappingService {

    /**
     * registerBean 是否已经存在
     * @param registerBean
     * @return
     */
    boolean hasApiRegistered(RegisterBean registerBean);

    /**
     * 注册method
     * post 默认调用方式
     * @param registerBean
     * @return
     */
    void registerApi(RegisterBean registerBean);

    /**
     * 注销method
     * @param registerBean
     * @return
     */
    void unregisterApi(RegisterBean registerBean);
}
