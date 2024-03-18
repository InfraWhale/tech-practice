package org.ch12.domain;

import java.util.Comparator;

public class GrapeComp implements Comparator<Grape> {
    public int compare(Grape t1, Grape t2) {
        return t1.weight - t2.weight;
    }
}
