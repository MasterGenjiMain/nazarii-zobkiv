package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPP implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanInterface) {
            ((BeanInterface) bean).validate();
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
