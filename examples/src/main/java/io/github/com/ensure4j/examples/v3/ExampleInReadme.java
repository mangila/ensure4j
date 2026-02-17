package io.github.com.ensure4j.examples.v3;

import io.github.mangila.ensure4j.Ensure;
import io.github.mangila.ensure4j.ops.EnsureNumberOps;

public class ExampleInReadme {

    private static final EnsureNumberOps ENSURE_NUMBER_OPS = Ensure.numbers();

    public static void main(String[] args) {
        var order = new Order(10);
        new ExampleInReadme().placeOrder(order);
    }

    private void placeOrder(Order order) {
        Ensure.notNull(order, () -> new OrderException("Order cannot be null"));
        ENSURE_NUMBER_OPS.min(1, order.getAmount(), () -> new OrderException("Order amount must be greater than zero"));
        // do business logic
    }

    private static class Order {
        private final int amount;

        public Order(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }

    private static class OrderException extends RuntimeException {
        private OrderException(String string) {
            super(string);
        }
    }
}
