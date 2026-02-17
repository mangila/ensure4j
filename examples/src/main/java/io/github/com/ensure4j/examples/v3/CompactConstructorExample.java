package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;
import io.github.mangila.ensure4j.ops.EnsureStringOps;

public class CompactConstructorExample {

    private record Order(String id, int amount) {

        private static final EnsureStringOps ENSURE_STRING_OPS = Ensure.strings();
        private static final EnsureNumberOps ENSURE_NUMBER_OPS = Ensure.numbers();

        Order {
            // static references approach
            ENSURE_STRING_OPS.notBlank(id);
            ENSURE_NUMBER_OPS.min(1, amount);
            // holder approach
            EnsureGlobalHolder.ENSURE_NUMBER_OPS.max(100, amount);
            EnsureGlobalHolder.ENSURE_STRING_OPS.notBlank(id);
            // or a raw approach
            Ensure.strings().notBlank(id);
            Ensure.numbers().min(1, amount);
        }
    }

}
