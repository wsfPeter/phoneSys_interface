package com.net.toooen.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @ClassName: BaseController
 * @Description: 前台基础控制器
 * @author zhujiang
 * @date 2015年1月2日 上午10:37:45
 * 
 */
public class BaseController {

    @Resource(name = "validator")
    private Validator validator;

    /**
     * @Title: isValid
     * @Description: 数据验证
     * @param target
     *            验证对象
     * @param groups
     *            验证组
     * @return 验证结果
     * @throws
     */
    protected boolean isValid(Object target, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            ConstraintViolation<Object> obj = constraintViolations.iterator().next();
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            String msg = obj.getMessage();
            requestAttributes.setAttribute("content", obj.getPropertyPath() + msg,
                    RequestAttributes.SCOPE_REQUEST);

            return false;
        }
    }

    /**
     * @Title: isValid
     * @Description: 数据验证
     * @param type
     *            类型
     * @param property
     *            属性
     * @param value
     *            值
     * @param groups
     *            验证组
     * @return 验证结果
     * @throws
     */
    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            requestAttributes.setAttribute("content", "参数错误", RequestAttributes.SCOPE_REQUEST);

            return false;
        }
    }

}