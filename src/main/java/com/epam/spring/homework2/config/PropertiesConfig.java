package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BeanB getBeanB() {
        return new BeanB(env.getProperty("beanB.name"), Integer.parseInt(Objects.requireNonNull(env.getProperty("beanB.value"))));
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    @DependsOn("getBeanB")
    public BeanC getBeanC() {
        return new BeanC(env.getProperty("beanC.name"), Integer.parseInt(Objects.requireNonNull(env.getProperty("beanC.value"))));
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanD getBeanD() {
        return new BeanD(env.getProperty("beanD.name"), Integer.parseInt(Objects.requireNonNull(env.getProperty("beanD.value"))));
    }
}
