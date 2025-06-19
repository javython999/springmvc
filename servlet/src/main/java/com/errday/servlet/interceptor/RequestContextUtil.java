package com.errday.servlet.interceptor;

public class RequestContextUtil {

    private static ThreadLocal<String> requestIdHolder = new ThreadLocal<>();

    public static void setRequestId(String requestId) {
        requestIdHolder.set(requestId);
    }

    public static String getRequestId() {
        return requestIdHolder.get();
    }

    public static void clearRequestId() {
        requestIdHolder.remove();
    }
}
