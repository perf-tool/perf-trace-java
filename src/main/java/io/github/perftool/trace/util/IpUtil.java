package io.github.perftool.trace.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

@Slf4j
public class IpUtil {

    private static final String POD_IP = getPodIp();

    private static final String FORMATTED_IP = StringTool.formatIp(POD_IP);

    private static String getPodIp() {
        String ipEnv = System.getenv("POD_IP");
        if (StringUtils.isEmpty(ipEnv)) {
            log.info("ip env is not set");
            if (SystemUtils.IS_OS_LINUX) {
                log.info("try to get eth0 ip on linux platform");
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
