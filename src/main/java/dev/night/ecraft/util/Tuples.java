package dev.night.ecraft.util;

public class Tuples {
    public record TwoItem<S, T>(S item1, T item2) {}
    public record ThreeItem<S, T, U>(S item1, T item2, U item3) {}
    public record FourItem<S, T, U, V>(S item1, T item2, U item3, V item4) {}
}
