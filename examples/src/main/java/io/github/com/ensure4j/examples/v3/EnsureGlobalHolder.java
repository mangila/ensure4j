package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;
import io.github.mangila.ensure4j.ops.EnsureStringOps;

public final class EnsureGlobalHolder {
    public static final EnsureStringOps ENSURE_STRING_OPS = Ensure.strings();
    public static final EnsureNumberOps ENSURE_NUMBER_OPS = Ensure.numbers();
}
