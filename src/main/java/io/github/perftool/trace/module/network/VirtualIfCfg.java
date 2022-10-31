package io.github.perftool.trace.module.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirtualIfCfg {

    private String name;

    private String virtualName;

    private String ip;

}
