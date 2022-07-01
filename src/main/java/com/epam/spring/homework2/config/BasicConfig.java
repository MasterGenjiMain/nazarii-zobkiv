package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BasicConfig {

    @Bean
    public BeanA getBeanA() {
        return new BeanA();
    }

    @Bean
    public BeanE getBeanE() {
        return new BeanE();
    }

    @Bean
    @Lazy
    public BeanF getBeanF() {
        return new BeanF();
    }

    @Bean
    public GeneralBean getGeneralBean(){
        return new GeneralBean();
    }

    @Bean
    public BeanFPP getBeanFPP(){
        return new BeanFPP();
    }

    @Bean
    public BeanPP getBeanPP() {
        return new BeanPP();
    }
}