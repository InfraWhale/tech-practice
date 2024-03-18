package org.ch12.domain;

//public class Fruit {
public class Fruit implements Eatable {
    String name;
    int weight;

    public Fruit() {
    }

    public Fruit(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public String toString() {
        if(name == null) {
            return this.getClass().getSimpleName();
        } else {
            return name + "(" + weight + ")";
        }

    }
}
