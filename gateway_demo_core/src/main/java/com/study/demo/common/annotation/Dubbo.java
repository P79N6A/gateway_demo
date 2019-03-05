package com.study.demo.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.study.demo.enums.InterfaceTypeEnum;

/**
 * 
* @Company 连连银通电子支付有限公司
*
* @Description: Dubbo注解
* @ClassName: Dubbo 
* @author zhufj
* @date 2018年11月8日 下午5:42:54 
*
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dubbo {

    // 操作名称
    public Class<?> clazz();

    // 操作名称
    public InterfaceTypeEnum operCode() default InterfaceTypeEnum.UNKNOW;

}
