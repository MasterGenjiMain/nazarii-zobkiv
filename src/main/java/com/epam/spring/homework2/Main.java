package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.GeneralBean;
import com.epam.spring.homework2.config.PropertiesConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertiesConfig.class);

        System.out.println("----------------------------");
        Arrays.stream(context.getBeanDefinitionNames()).filter(s -> s.contains("get")).forEach(System.out::println);

    }
}