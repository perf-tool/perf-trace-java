package io.github.perftool.trace.report.redis;

import io.github.perftool.trace.module.SpanInfo;
import io.github.perftool.trace.module.TraceBean;
import io.github.perftool.trace.redis.RedisReportBean;
import io.github.perftool.trace.report.ITraceReporter;
import io.github.perftool.trace.util.JacksonUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RedisTraceReporter implements ITraceReporter {

    private final RedisConfig redisConfig;

    private final StatefulRedisConnection<String, String> connection;

    public RedisTraceReporter(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        String[] url = this.redisConfig.getUrl().split(":");
        RedisURI redisUrl = RedisURI.Builder
                .redis(url[0], Integer.parseInt(url[1]))
                .withTimeout(Duration.ofSeconds(redisConfig.getTimeout()))
                .withAuthentication(redisConfig.getUser(), redisConfig.getPassword().toCharArray())
                .build();
        this.connection = RedisClient.create(redisUrl).connect();
    }

    @Override
    public void reportTrace(TraceBean traceBean) {
        String result = connection.sync().get(traceBean.getTraceId());
        if (StringUtils.isEmpty(result)) {
            RedisReportBean reportBean = new RedisReportBean();
            reportBean.setTraceId(traceBean.getTraceId());
            Map<String, SpanInfo> map =  new HashMap<>();
            map.put(traceBean.getSpanInfo().getSpanId(), traceBean.getSpanInfo());
            reportBean.setMap(map);
            connection.sync().set(traceBean.getTraceId(), JacksonUtil.toJson(reportBean));
        } else {
            RedisReportBean bean = JacksonUtil.toObject(result, RedisReportBean.class);
            if (bean == null) {
                return;
            }
            Map<String, SpanInfo> map = bean.getMap();
            map.put(traceBean.getSpanInfo().getSpanId(), traceBean.getSpanInfo());
            bean.setMap(map);
            connection.sync().set(traceBean.getTraceId(), JacksonUtil.toJson(bean));
        }
    }
}
