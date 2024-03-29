package org.ch12.generic.domain;

import java.util.ArrayList;

public class Box<T> {
    ArrayList<T> list = new ArrayList<T>();

    public void add(T item) {
        list.add(item);
    }

    public T get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }

    public ArrayList<T> getList() {
        return list;
    }
}
