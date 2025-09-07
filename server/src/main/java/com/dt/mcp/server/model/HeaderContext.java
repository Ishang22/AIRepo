package com.dt.mcp.server.model;

import java.util.Map;

public class HeaderContext {

    private static final InheritableThreadLocal<Map<String, String>> headersHolder = new InheritableThreadLocal<>();

    public static void set(Map<String, String> headers) {
        headersHolder.set(headers);
    }

    public static Map<String, String> get() {
        return headersHolder.get();
    }

    public static void clear() {
        headersHolder.remove();
    }
}
