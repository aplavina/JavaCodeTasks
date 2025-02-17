package com.aplavina.core;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CollectionFilter {
    public static <T> T[] filter(T[] arr, Filter<T> filter) {
        T[] res = Arrays.copyOf(arr, arr.length);
        IntStream.range(0, arr.length).forEach(index -> res[index] = filter.apply(arr[index]));
        return res;
    }

    interface Filter<T> {
        T apply(T o);
    }

    public static void main(String[] args) {
        String[] strings = {"Hello", "Hey"};
        String[] newStrings = filter(strings, s -> s + " World!");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(newStrings).forEach(s -> {
            sb.append(s);
            sb.append("\n");
        });
        System.out.println(sb); // Hello World!\nHey World!
    }
}
