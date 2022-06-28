package com.epam.spring.homework1;

import com.epam.spring.homework1.beans.BeanA;
import com.epam.spring.homework1.config.BeansConfig;
import com.epam.spring.homework1.config.OtherConfig;
import com.epam.spring.homework1.pet.Pet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        Pet pet = context.getBean(Pet.class);
        pet.printAnimals();
    }
}
