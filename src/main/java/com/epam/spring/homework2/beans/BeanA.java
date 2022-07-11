package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA extends GeneralBean implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() method:" + this.getClass().getSimpleName() + " ... Destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        name = "beanAName";
        value = -10;
        System.out.println("afterPropertiesSet() name: " + name + " value: " + value);
    }

}
