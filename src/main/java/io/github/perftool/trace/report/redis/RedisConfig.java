package io.github.perftool.trace.report.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class RedisConfig {

    private String url;

    private String password;

    private String user;

    private long timeout;

    public RedisConfig() {
    }
}
