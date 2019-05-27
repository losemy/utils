package com.github.losemy.utils.validator;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Description: utils
 * Created by lose on 19-4-7 下午9:14
 */
public class ValidatorUtil {

    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 失败返回异常
     *
     * @param obj
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
//            PCBizException e = new PCBizException(BizExceptionEnum.REQUEST_PARAM_ERROR.getIndex(), String.format(constraintViolations.iterator().next().getMessage()));
//            e.setInfo(constraintViolations.iterator().next().getPropertyPath()); // 字段校验未通过的字段名称

//            throw e;
        }
    }


}
