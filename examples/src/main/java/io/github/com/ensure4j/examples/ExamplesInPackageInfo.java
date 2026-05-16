package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

public class ExamplesInPackageInfo {

    private static final String EMAIL_REGEX = "the email regex";

    public void processOrder(Order order) {
        Ensure.notNull(order);
        Ensure.positive(order.getAmount());
        // ...
    }

    public void sendEmail(String email) {
        Ensure.notBlank(email, "Email must not be blank");
        Ensure.matches(email, EMAIL_REGEX, "Invalid email format");
        // ...
    }

    public void withdraw(int amount, int balance) {
        Ensure.positive(amount, () -> new InsufficientFundsException("Amount must be positive"));
        Ensure.max(amount, balance, () -> new InsufficientFundsException("Insufficient funds"));
        // ...
    }

    private static class Order {
        private final int amount;

        Order(int amount) {
            this.amount = amount;
        }

        int getAmount() {
            return amount;
        }
    }

    private static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}
