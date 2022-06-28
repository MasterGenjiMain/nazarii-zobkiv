package com.epam.spring.homework1.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.epam.spring.homework1.beans")
@Import(OtherConfig.class)
public class BeansConfig {

//    @Bean
//    public BeanA beanAService(){
//        return new BeanA();
//    }
//
//    @Bean
//    public BeanB beanBService(){
//        return new BeanB();
//    }
//
//    @Bean
//    public BeanC beanCService(){
//        return new BeanC();
//    }

}
