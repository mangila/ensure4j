package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

/**
 * Service layer pre-condition before critical business logic.
 */
public class ServiceLayer {

    static class Order {
        private int amount;

        public int getAmount() {
            return amount;
        }
    }

    public void placeOrder(Order order) {
        Ensure.notNull(order, "Order cannot be null");
        Ensure.min(1, order.getAmount(), "Order amount must be greater than zero");
        // do business logic
    }

    public static void main(String[] args) {
        var service = new ServiceLayer();
        var order = new Order();
        order.amount = 0;
        service.placeOrder(order);
    }

}
