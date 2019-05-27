package com.github.losemy.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 项目中所有定义异常必须继承这个类
 * 不同层应该生成不同类型的异常
 * 不同的处理也应该返回不同的异常
 * 方便aop对不同异常做不同的处理
 * Description: utils
 * Created by lose on 19-4-7 下午9:18
 */
@AllArgsConstructor
@Getter
public class CommonException extends Throwable {

    private String code;
    private String msg;
    private Throwable e;

    public CommonException(Throwable e){
        this.e = e;
    }



}
