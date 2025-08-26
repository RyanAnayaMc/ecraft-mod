package dev.night.ecraft.util;

import java.util.ArrayList;

// This amazing class lets me be extra lazy creating items and adding them to my item group
public class ItemList<T> extends ArrayList<T> {
    public T put(T item) {
        this.add(item);
        return item;
    }
}
