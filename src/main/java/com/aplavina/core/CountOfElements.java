package com.aplavina.core;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CountOfElements {
    private static <T> Map<T, Long> countElements(T[] elements) {
        return Arrays.stream(elements).collect(Collectors.groupingBy(element -> element, Collectors.counting()));
    }

    public static void main(String[] args) {
        String[] elements = {"abc", "abc", "bca", "abc", "hct"};
        Map<String, Long> count = countElements(elements);
        StringBuilder sb = new StringBuilder();
        count.forEach((key, value) -> {
            sb.append(key);
            sb.append(" -> ");
            sb.append(value);
            sb.append("\n");
        });
        System.out.println(sb);
//        bca -> 1
//        abc -> 3
//        hct -> 1
    }
}
