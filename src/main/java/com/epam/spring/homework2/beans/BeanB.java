package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanB implements BeanInterface {
    private String name;
    private int value;

    public BeanB(String name, int value) {      //idk what`s going on because autowiring works well
        this.name = name;
        this.value = value;
    }

    public void initMethod(){
        System.out.println("Init: " + this.getClass().getSimpleName());
    }

    public void initMethodFPP(){
        System.out.println("InitFPP: " + this.getClass().getSimpleName());
    }

    public void destroyMethod(){
        System.out.println("Destroy: " + this.getClass().getSimpleName());
    }

    public void validate(){
        if (name == null || value < 0) {
            System.out.println(this.getClass().getSimpleName() + "has a bad fields");
            if (name == null) {
                name = "Empty";
            }
        } else {
            System.out.println("Fields validated");
        }
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}