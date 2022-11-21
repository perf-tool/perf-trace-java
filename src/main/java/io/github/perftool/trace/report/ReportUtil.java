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

    public static String spanIdPrefix() {
        return scene + System.currentTimeMillis() + "-" + IpUtil.formattedIp();
    }

    public static ITraceReporter getReporter() {
        String type = EnvUtil.getString("TRACE_REPORT_TYPE", "NOOP");
        return switch (type) {
            case "MONGO" -> new MongoTraceReporter(MongoConfig.fromEnv());
            case "REDIS" -> new RedisTraceReporter(RedisConfig.fromEnv());
            default -> new NoopReporter();
        };
    }

}
