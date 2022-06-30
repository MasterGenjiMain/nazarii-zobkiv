package com.epam.spring.homework2.propertyBeans;

import org.springframework.stereotype.Component;

@Component
public class BeanD {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
