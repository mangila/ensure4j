package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;

public class ExampleInReadme {

    private static final EnsureNumberOps ensureNumberOps = Ensure.numbers();

    public static void main(String[] args) {
        var order = new Object() {
            int getAmount() {
                return 100;
            }
        };
        Ensure.notNull(order, () -> new OrderException("Order cannot be null"));
        ensureNumberOps.min(1, order.getAmount(), () -> new OrderException("Order amount must be greater than zero"));
    }

    private static class OrderException extends RuntimeException {
        public OrderException(String string) {
            super(string);
        }
    }
}
