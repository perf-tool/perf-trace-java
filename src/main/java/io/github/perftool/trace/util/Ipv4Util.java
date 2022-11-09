package io.github.perftool.trace.util;

import io.github.perftool.trace.module.network.IfCfg;
import io.github.perftool.trace.module.network.VirtualIfCfg;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class Ipv4Util {

    public static String getIp(@NotNull String eth) {
        String[] split = eth.split(":");
        if (split.length == 0) {
            throw new IllegalArgumentException("Not correct eth format");
        }
        List<IfCfg> ifCfgs = getIfCfgs();
        Optional<IfCfg> ifCfgOptional = ifCfgs.stream().filter(ifCfg -> ifCfg.getName().equals(split[0])).findAny();
        if (ifCfgOptional.isEmpty()) {
            return null;
        }
        IfCfg ifCfg = ifCfgOptional.get();
        if (split.length == 1) {
            return ifCfg.getIp();
        }
        return ifCfg.getVirtualIfCfgs().stream().filter(virtualIfCfg -> virtualIfCfg.getName().equals(split[1]))
                .findAny().map(VirtualIfCfg::getIp).orElse(null);
    }

    public static List<IfCfg> getIfCfgs() {
        List<IfCfg> ifCfgs = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netInt : Collections.list(nets)) {
                IfCfg ifCfg = new IfCfg();
                List<VirtualIfCfg> virtualIfCfgs = new ArrayList<>();
                Enumeration<NetworkInterface> interfaces = netInt.getSubInterfaces();

                Set<String> virtualIpSet = new HashSet<>();

                for (NetworkInterface aux : Collections.list(interfaces)) {
                    Enumeration<InetAddress> inetAddresses = aux.getInetAddresses();
                    InetAddress inetAddress = inetAddresses.nextElement();
                    String hostAddress = inetAddress.getHostAddress();
                    virtualIpSet.add(hostAddress);
                    virtualIfCfgs.add(new VirtualIfCfg(netInt.getName(), aux.getName().split(":")[1], hostAddress));
                }
                for (InetAddress inetAddress : Collections.list(netInt.getInetAddresses())) {
                    if (!virtualIpSet.contains(inetAddress.getHostAddress())) {
                        ifCfg.setName(netInt.getName());
                        ifCfg.setIp(inetAddress.getHostAddress());
                        break;
                    }
                }
                ifCfg.setVirtualIfCfgs(virtualIfCfgs);
                ifCfgs.add(ifCfg);
            }
        } catch (Exception e) {
            log.error("get IP exception, exception is ", e);
        }
        return ifCfgs;
    }

}
