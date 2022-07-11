package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@PropertySource("classpath:application.properties")
@Import(BasicConfig.class)
public class PropertiesConfig {
    @Autowired
    public Environment env;

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    @DependsOn("getBeanD")
    public BeanB getBeanB(@Value("${beanB.name}") final String name, @Value("${beanB.value}") final int value) {
        return new BeanB(name, value);
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    @DependsOn("getBeanB")
    public BeanC getBeanC(@Value("${beanC.name}") final String name, @Value("${beanC.value}") final int value) {
        return new BeanC(name, value);
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanD getBeanD(@Value("${beanD.name}") final String name, @Value("${beanD.value}") final int value) {
        return new BeanD(name, value);
    }
}
