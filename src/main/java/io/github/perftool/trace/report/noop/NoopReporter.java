package io.github.perftool.trace.report.noop;

import io.github.perftool.trace.module.TraceBean;
import io.github.perftool.trace.report.ITraceReporter;

public class NoopReporter implements ITraceReporter {
    @Override
    public void reportTrace(TraceBean traceBean) {
    }
}
