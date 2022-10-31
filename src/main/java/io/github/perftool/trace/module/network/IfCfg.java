package io.github.perftool.trace.module.network;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class IfCfg {

    private String name;

    private String ip;

    private List<VirtualIfCfg> virtualIfCfgs;

}
