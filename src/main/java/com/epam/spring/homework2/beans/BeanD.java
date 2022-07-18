package com.epam.spring.homework2.beans;

public class BeanD extends GeneralBean {

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;

        System.out.println("Created with: " + name + " value: " + value);
    }

    public void initMethod(){
        System.out.println("Init: " + this.getClass().getSimpleName());
    }

    public void destroyMethod(){
        System.out.println("Destroy: " + this.getClass().getSimpleName());
    }
}
