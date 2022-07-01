package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB implements BeanInterface {
    private final String name;
    private final int value;

    public BeanB(String name, int value) {      //idk what`s going on because autowiring works well
        this.name = name;
        this.value = value;
    }

    public void initMethod(){
        System.out.println("Init: " + this.getClass().getSimpleName());
    }

    public void destroyMethod(){
        System.out.println("Destroy: " + this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}