package io.github.perftool.trace.util;

import org.apache.commons.lang3.StringUtils;

public class EnvUtil {

    public static boolean getBoolean(String env, boolean defaultVal) {
        String envVal = System.getenv(env);
        if (StringUtils.isNotEmpty(envVal)) {
            return Boolean.parseBoolean(envVal);
        }
        return defaultVal;
    }

    public static int getInt(String env, int defaultVal) {
        String envVal = System.getenv(env);
        if (StringUtils.isNotEmpty(envVal)) {
            return Integer.parseInt(envVal);
        }
        return defaultVal;
    }

    public static float getFloat(String env, float defaultVal) {
        String envVal = System.getenv(env);
        if (StringUtils.isNotEmpty(envVal)) {
            return Float.parseFloat(envVal);
        }
        return defaultVal;
    }

    public static double getDouble(String env, double defaultVal) {
        String envVal = System.getenv(env);
        if (StringUtils.isNotEmpty(envVal)) {
            return Double.parseDouble(envVal);
        }
        return defaultVal;
    }

    public static String getString(String env, String defaultVal) {
        String envVal = System.getenv(env);
        if (StringUtils.isNotEmpty(envVal)) {
            return envVal;
        }
        return defaultVal;
    }

}
