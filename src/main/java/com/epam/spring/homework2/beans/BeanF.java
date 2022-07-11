package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanF extends GeneralBean {

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
