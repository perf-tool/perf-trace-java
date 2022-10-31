package io.github.perftool.trace.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TraceBean {

    private String traceId;

    private SpanInfo spanInfo;

    public TraceBean() {
    }
}
