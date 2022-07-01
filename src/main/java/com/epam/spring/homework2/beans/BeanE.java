package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE implements BeanInterface {
    private String name;
    private int value;

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("Post construct functions of bean: " + this.getClass().getSimpleName());
    }

    @PreDestroy
    public void preDestroyMethod(){
        System.out.println("Pre destroy functions of bean: " + this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}