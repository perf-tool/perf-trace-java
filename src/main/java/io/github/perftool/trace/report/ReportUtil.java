package io.github.perftool.trace.report;

import io.github.perftool.trace.report.mongo.MongoConfig;
import io.github.perftool.trace.report.mongo.MongoTraceReporter;
import io.github.perftool.trace.report.noop.NoopReporter;
import io.github.perftool.trace.report.redis.RedisConfig;
import io.github.perftool.trace.report.redis.RedisTraceReporter;
import io.github.perftool.trace.util.EnvUtil;
import io.github.perftool.trace.util.IpUtil;

public class ReportUtil {

    private static final String scene = EnvUtil.getString("TRACE_REPORT_SCENE", "default");

    public static String traceIdPrefix() {
        return scene + System.currentTimeMillis() + "-" + IpUtil.formattedIp();
    }

    public static ITraceReporter getReporter() {
        String type = System.getenv("TRACE_REPORT_TYPE");
        return switch (type) {
            case "MONGO" -> new RedisTraceReporter(RedisConfig.fromEnv());
            case "REDIS" -> new MongoTraceReporter(MongoConfig.fromEnv());
            default -> new NoopReporter();
        };
    }

}
