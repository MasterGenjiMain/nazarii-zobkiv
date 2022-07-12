package com.epam.spring.homework2.beans;

public class BeanB extends GeneralBean {

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void initMethod() {
        System.out.println("Init: " + this.getClass().getSimpleName());
    }

    public void initMethodFPP() {
        System.out.println("InitFPP: " + this.getClass().getSimpleName());
    }

    public void destroyMethod() {
        System.out.println("Destroy: " + this.getClass().getSimpleName());
    }
}
