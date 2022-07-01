package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralBean {

    protected String name;
    protected int value;

    @Autowired
    private List<BeanInterface> beans;

    public void printBeans(){
        for (BeanInterface bean: beans){
            System.out.println(bean);
        }
    }
}
