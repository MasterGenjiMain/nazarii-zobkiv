package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanF implements BeanInterface {
    private String name;
    private int value;

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
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}