package io.github.perftool.trace.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpanInfo {

    private String spanId;

    public SpanInfo() {
    }
}
