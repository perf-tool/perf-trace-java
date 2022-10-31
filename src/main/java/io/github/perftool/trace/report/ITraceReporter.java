package io.github.perftool.trace.report;

import io.github.perftool.trace.module.TraceBean;

public interface ITraceReporter {

    void reportTrace(TraceBean traceBean);

}
