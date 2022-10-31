package io.github.perftool.trace.util;

public class StringTool {

    public static String fixedLen(String val, int digits) {
        return StringTool.fixedLen(val, digits, '0');
    }

    public static String fixedLen(String val, int digits, char filler) {
        return String.valueOf(filler).repeat(Math.max(0, digits - val.length())) + val;
    }

}
