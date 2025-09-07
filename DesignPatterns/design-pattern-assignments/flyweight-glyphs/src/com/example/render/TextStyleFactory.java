package com.example.render;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextStyleFactory {
    private static final Map<String, TextStyle> cache = new ConcurrentHashMap<>();
    // ConcurrentHashMap is a thread-safe variant of HashMap that allows concurrent access by multiple threads without the need for external synchronization.
    // It achieves thread safety with internal locking mechanisms, but is more efficient than synchronizing the whole map.
    // computeIfAbsent ensures that even if multiple threads ask for the same style at the same time, only one TextStyle is created and shared.
    public static TextStyle get(String font, int size, boolean bold) {
        String key = font + "|" + size + "|" + (bold ? "B" : "N");
        return cache.computeIfAbsent(key, k -> new TextStyle(font, size, bold));
    }
}


