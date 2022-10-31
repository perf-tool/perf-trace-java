package io.github.perftool.trace.redis;

import io.github.perftool.trace.module.SpanInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class RedisReportBean {

    private String traceId;

    private Map<String, SpanInfo> map;

    public RedisReportBean() {
        this.map = new HashMap<>();
    }
}
