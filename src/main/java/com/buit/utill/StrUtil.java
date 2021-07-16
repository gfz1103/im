package com.buit.utill;

public class StrUtil {
    public static String null2Str(Object obj) {
        String str = obj == null ? "" : String.valueOf(obj);
        return str;
    }

    public static String nullAndEmpty2Zero(Object obj) {
        String str = null2Str(obj);
        return "".equals(str) ? "0" : str;
    }

    /**
     * 字符串是否为空对象或空格
     */
    public static Boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static Boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
