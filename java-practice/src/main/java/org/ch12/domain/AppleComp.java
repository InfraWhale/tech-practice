package org.ch12.domain;

import java.util.Comparator;

public class AppleComp implements Comparator<Apple> {
    public int compare(Apple t1, Apple t2) {
        return t1.weight - t2.weight;
    }
}
