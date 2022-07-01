package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA implements InitializingBean, DisposableBean, BeanInterface {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() method:" + this.getClass().getSimpleName() + " ... Destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        name = "beanAName";
        value = 0;
        System.out.println("afterPropertiesSet() name: " + name + " value: " + value);
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
}
