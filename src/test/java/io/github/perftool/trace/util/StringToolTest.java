package io.github.perftool.trace.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringToolTest {

    @Test
    void testFixedLenCase1() {
        String ret = StringTool.fixedLen("22", 3);
        Assertions.assertEquals("022", ret);
    }

}
