package com.github.losemy.utils.bean.modelmapper;

import java.io.Serializable;

/**
 * Description: utils
 * Created by lose on 19-4-5 下午11:31
 */
public class Convert implements Serializable {

    /**
     * 获取自动转换后的JavaBean对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T convert(Class<T> clazz) {
        return BeanConverter.convert(clazz, this);
    }
}