package org.ch12.domain;

public class Juice {
    String name;

    public Juice(String name) {
        this.name = name + "Juice";
    }

    @Override
    public String toString() {
        return name;
    }
}
