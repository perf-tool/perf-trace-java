package io.github.perftool.trace.report;

import io.github.perftool.trace.report.mongo.MongoConfig;
import io.github.perftool.trace.report.redis.RedisConfig;
import io.github.perftool.trace.util.EnvUtil;

public class ReportUtil {

    public static MongoConfig getMongoConfig() {
        return MongoConfig.builder()
                .host(EnvUtil.getString("TRACE_REPORT_MONGODB_HOST", "localhost"))
                .port(EnvUtil.getInt("TRACE_REPORT_MONGODB_PORT", 27017))
                .username(EnvUtil.getString("TRACE_REPORT_MONGODB_USERNAME", ""))
                .password(EnvUtil.getString("TRACE_REPORT_MONGODB_PASSWORD", ""))
                .databaseName(EnvUtil.getString("TRACE_REPORT_MONGODB_DATABASE_NAME", "trace_db"))
                .collectionName(EnvUtil.getString("TRACE_REPORT_MONGODB_COLLECTION_NAME", "trace_collect"))
                .build();

    }

    public static RedisConfig getRedisConfig() {
        return RedisConfig.builder()
                .url(EnvUtil.getString("TRACE_REPORT_REDIS_CLUSTER_NODES_URL", "localhost:6379"))
                .password(EnvUtil.getString("TRACE_REPORT_REDIS_PASSWORD", ""))
                .user(EnvUtil.getString("TRACE_REPORT_REDIS_USER", ""))
                .timeout(EnvUtil.getInt("TRACE_REPORT_REDIS_TIMEOUT_SECONDS", 15))
                .build();
    }

}
