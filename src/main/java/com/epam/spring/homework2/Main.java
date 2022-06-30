package com.epam.spring.homework2;

import com.epam.spring.homework2.config.BasicConfig;
import com.epam.spring.homework2.propertyBeans.BeanB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);
        System.out.println(context.getBean(BeanB.class));
    }
}
