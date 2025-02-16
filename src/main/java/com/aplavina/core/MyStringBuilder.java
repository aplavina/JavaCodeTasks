package com.aplavina.core;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStringBuilder {
    private StringBuilder sb = new StringBuilder();
    private final Deque<String> history = new ArrayDeque<>();

    public MyStringBuilder append(String s) {
        takeSnapshot();
        sb.append(s);
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        takeSnapshot();
        sb.delete(start, end);
        return this;
    }

    public MyStringBuilder replace(int start, int end, String str) {
        takeSnapshot();
        sb.replace(start, end, str);
        return this;
    }

    public MyStringBuilder insert(int index, char[] str, int offset,
                                  int len) {
        takeSnapshot();
        sb.insert(index, str, offset, len);
        return this;
    }

    public MyStringBuilder reverse() {
        takeSnapshot();
        sb.reverse();
        return this;
    }

    public MyStringBuilder undo() {
        if (history.isEmpty()) {
            return this;
        }
        sb = new StringBuilder(history.pop());
        return this;
    }

    private void takeSnapshot() {
        history.push(sb.toString());
    }
}
