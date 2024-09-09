package dev.night.ecraft.util;

import java.util.ArrayList;

public class ItemList<T> extends ArrayList<T> {
    public T put(T item) {
        this.add(item);
        return item;
    }
}
