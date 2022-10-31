package io.github.perftool.trace.report;

import io.github.perftool.trace.report.mongo.MongoConfig;
import io.github.perftool.trace.report.mongo.MongoTraceReporter;
import io.github.perftool.trace.report.noop.NoopReporter;
import io.github.perftool.trace.report.redis.RedisConfig;
import io.github.perftool.trace.report.redis.RedisTraceReporter;

public class ReportUtil {

    public ITraceReporter getReporter() {
        String type = System.getenv("TRACE_REPORT_TYPE");
        return switch (type) {
            case "MONGO" -> new RedisTraceReporter(RedisConfig.fromEnv());
            case "REDIS" -> new MongoTraceReporter(MongoConfig.fromEnv());
            default -> new NoopReporter();
        };
    }

}
