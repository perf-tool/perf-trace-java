package io.github.perftool.trace.util;

import org.apache.commons.lang3.SystemUtils;

public class IpUtil {

    private static final String POD_IP = getPodIp();

    private static final String FORMATTED_IP = StringTool.formatIp(POD_IP);

    private static String getPodIp() {
        String ipEnv = System.getenv("POD_IP");
        if ("".equals(ipEnv)) {
            if (SystemUtils.IS_OS_LINUX) {
                return Ipv4Util.getIp("eth0");
            }
        }
        return ipEnv;
    }

    public static String podIp() {
        return POD_IP;
    }

    public static String formattedIp() {
        return FORMATTED_IP;
    }

}
