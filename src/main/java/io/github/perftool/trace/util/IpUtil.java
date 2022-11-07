package io.github.perftool.trace.util;

public class IpUtil {

    private static final String POD_IP = System.getenv("POD_IP");

    private static final String FORMATTED_IP = StringTool.formatIp(POD_IP);

    public static String podIp() {
        return POD_IP;
    }

    public static String formattedIp() {
        return FORMATTED_IP;
    }

}
