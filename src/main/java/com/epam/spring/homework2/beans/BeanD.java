package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanD implements BeanInterface {
    private String name;
    private int value;

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
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
