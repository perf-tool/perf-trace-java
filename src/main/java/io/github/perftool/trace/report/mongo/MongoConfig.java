package io.github.perftool.trace.report.mongo;

import io.github.perftool.trace.util.EnvUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MongoConfig {

    private String host;

    private int port;

    private String username;

    private String password;

    private String databaseName;

    private String collectionName;

    public MongoConfig() {
    }

    public static MongoConfig fromEnv() {
        return MongoConfig.builder()
                .host(EnvUtil.getString("TRACE_REPORT_MONGODB_HOST", "localhost"))
                .port(EnvUtil.getInt("TRACE_REPORT_MONGODB_PORT", 27017))
                .username(EnvUtil.getString("TRACE_REPORT_MONGODB_USERNAME", ""))
                .password(EnvUtil.getString("TRACE_REPORT_MONGODB_PASSWORD", ""))
                .databaseName(EnvUtil.getString("TRACE_REPORT_MONGODB_DATABASE_NAME", "trace_db"))
                .collectionName(EnvUtil.getString("TRACE_REPORT_MONGODB_COLLECTION_NAME", "trace_collect"))
                .build();
    }
}
