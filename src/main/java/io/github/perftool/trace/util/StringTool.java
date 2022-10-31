package io.github.perftool.trace.util;

public class StringTool {

    public static String formatIp(String ip) {
        StringBuilder builder = new StringBuilder();
        String[] array = ip.split("\\.");
        for (String segment : array) {
            builder.append(StringTool.fixedLen(segment, 3));
        }
        return builder.toString();
    }

    public static String fixedLen(String val, int digits) {
        return StringTool.fixedLen(val, digits, '0');
    }

    public static String fixedLen(String val, int digits, char filler) {
        return String.valueOf(filler).repeat(Math.max(0, digits - val.length())) + val;
    }

}
