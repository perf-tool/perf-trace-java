package io.github.perftool.trace.util;

import java.util.concurrent.atomic.AtomicInteger;

public class InboundCounter {

    private final AtomicInteger atomicInteger;

    private final int bound;

    public InboundCounter(int bound) {
        this.atomicInteger = new AtomicInteger();
        this.bound = bound;
    }

    public int get() {
        return atomicInteger.accumulateAndGet(1, ((left, right) -> {
            int val = left + right;
            return bound < val ? 0 : val;
        }));
    }

}
