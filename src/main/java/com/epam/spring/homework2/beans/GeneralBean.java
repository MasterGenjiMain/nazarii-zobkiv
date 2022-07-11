package com.epam.spring.homework2.beans;

public abstract class GeneralBean  {

    protected String name;
    protected int value;

    public GeneralBean() {
    }

    public GeneralBean(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanEntity{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public void validate() {
        System.out.println("|VALIDATOR|");
        if (name == null || value < 0) {
            System.out.println(this.getClass().getSimpleName() + " has a no valid field! Current name: " + name + " ; value: " + value);
        } else {
            System.out.println(this.getClass().getSimpleName() + " field valid! Current name: " + name + " ; value: " + value);
        }
    }
}
