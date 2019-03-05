package com.study.demo.utils;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;

import com.study.demo.enums.ResultCodeEnum;
import com.study.demo.exceptions.BizException;

/**
 * 
*
* @Description: 参数校验工具类
* @ClassName: ValidateBeanUtils 
* @author zhufj
* @date 2019年3月5日 上午11:17:47 
*
 */
public class ValidateBeanUtils {

    /**
     * 注解参数校验
     * 
     * @param validator
     * @param object
     */
    public static void validateAnnotation(Object object) {

        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure().addProperty("hibernate.validator.fail_fast", "true")
            .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();

        // 是否有检验失败
        while (iterator.hasNext()) {
            // 检验失败消息
            throw new BizException(ResultCodeEnum.PARAM_ILLEGAL, iterator.next().getMessage());
        }
    }

}