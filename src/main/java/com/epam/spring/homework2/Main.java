package com.epam.spring.homework2;

import com.epam.spring.homework2.config.PropertiesConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertiesConfig.class);

        System.out.println("----------------------------");
        Arrays.stream(context.getBeanDefinitionNames()).filter(s -> s.contains("getBean")).forEach(System.out::println);

        System.out.println("----------------------------");
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(context.getBeanDefinition(beanName));
        }

        context.close();

    }
}
