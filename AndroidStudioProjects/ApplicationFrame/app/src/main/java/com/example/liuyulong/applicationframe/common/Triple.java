package com.example.liuyulong.applicationframe.common;

public class Triple<F, S, T> {
    public final F first;
    public final S second;
    public final T third;

    public Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <A, B, C> Triple<A, B, C> create(A a, B b, C c) {
        return new Triple<>(a, b, c);
    }
}

