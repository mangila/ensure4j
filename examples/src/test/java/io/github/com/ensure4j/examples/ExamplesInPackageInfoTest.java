package io.github.com.ensure4j.examples;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.mangila.ensure4j.EnsureException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExamplesInPackageInfoTest {

  private final ExamplesInPackageInfo examples = new ExamplesInPackageInfo();

  @Test
  @DisplayName("processOrder should succeed with valid order")
  void processOrderShouldSucceedWithValidOrder() {
    Order order = new Order(100, List.of("Item"));
    assertThatCode(() -> examples.processOrder(order)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("processOrder should throw EnsureException when order is null")
  void processOrderShouldThrowEnsureExceptionWhenOrderIsNull() {
    assertThatThrownBy(() -> examples.processOrder(null))
        .isInstanceOf(EnsureException.class)
        .hasMessage("object must not be null");
  }

  @Test
  @DisplayName("sendEmail should succeed with valid email")
  void sendEmailShouldSucceedWithValidEmail() {
    assertThatCode(() -> examples.sendEmail("user@example.com")).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("sendEmail should throw EnsureException when email is blank")
  void sendEmailShouldThrowEnsureExceptionWhenEmailIsBlank() {
    assertThatThrownBy(() -> examples.sendEmail("   "))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Email must not be blank");
  }

  @Test
  @DisplayName("sendEmail should throw EnsureException when email format is invalid")
  void sendEmailShouldThrowEnsureExceptionWhenEmailFormatIsInvalid() {
    assertThatThrownBy(() -> examples.sendEmail("not-an-email"))
        .isInstanceOf(EnsureException.class)
        .hasMessage("Invalid email format");
  }

  @Test
  @DisplayName("withdraw should succeed when amount is positive and within balance")
  void withdrawShouldSucceedWhenAmountIsValid() {
    assertThatCode(() -> examples.withdraw(50, 100)).doesNotThrowAnyException();
  }

  @Test
  @DisplayName("withdraw should throw InsufficientFundsException when amount is zero or negative")
  void withdrawShouldThrowExceptionWhenAmountIsNotPositive() {
    assertThatThrownBy(() -> examples.withdraw(0, 100))
        .isInstanceOf(InsufficientFundsException.class)
        .hasMessage("Amount must be positive");

    assertThatThrownBy(() -> examples.withdraw(-10, 100))
        .isInstanceOf(InsufficientFundsException.class)
        .hasMessage("Amount must be positive");
  }

  @Test
  @DisplayName("withdraw should throw InsufficientFundsException when amount exceeds balance")
  void withdrawShouldThrowExceptionWhenAmountExceedsBalance() {
    assertThatThrownBy(() -> examples.withdraw(150, 100))
        .isInstanceOf(InsufficientFundsException.class)
        .hasMessage("Insufficient funds");
  }
}
