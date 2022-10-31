package io.github.perftool.trace.report.redis;

import io.github.perftool.trace.util.EnvUtil;
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

    public static RedisConfig fromEnv() {
        return RedisConfig.builder()
                .url(EnvUtil.getString("TRACE_REPORT_REDIS_CLUSTER_NODES_URL", "localhost:6379"))
                .password(EnvUtil.getString("TRACE_REPORT_REDIS_PASSWORD", ""))
                .user(EnvUtil.getString("TRACE_REPORT_REDIS_USER", ""))
                .timeout(EnvUtil.getInt("TRACE_REPORT_REDIS_TIMEOUT_SECONDS", 15))
                .build();
    }
}
