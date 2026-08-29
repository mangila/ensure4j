/*
 * MIT License
 *
 * Copyright (c) 2025 Erik Olsson (olsson.erik1993@gmail.cm)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.mangila.ensure4j;

import static io.github.mangila.ensure4j.ArchTestUtils.getMethodCount;
import static io.github.mangila.ensure4j.ArchTestUtils.getMethodNames;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class EnsureArchitectureTest {

  @Test
  void shouldCountOpsClasses() {
    final int expectedOpsCount = 10;
    final List<JavaClass> ensureOpsClasses =
        ArchTestUtils.ENSURE_PACKAGE.stream()
            .filter(javaClass -> javaClass.getSimpleName().endsWith("Ops"))
            .toList();
    assertThat(ensureOpsClasses)
        .as(
            "number of EnsureOps classes: %d - %s"
                .formatted(ensureOpsClasses.size(), ensureOpsClasses))
        .hasSize(expectedOpsCount);
  }

  @Test
  void shouldThrowIfCallingConstructor() throws NoSuchMethodException {
    Constructor<?> constructor = Ensure.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertThatThrownBy(constructor::newInstance)
        .isInstanceOf(InvocationTargetException.class)
        .hasCauseInstanceOf(AssertionError.class)
        .hasRootCauseMessage("No Ensure4j for you!");
  }

  @Test
  void shouldThrowIfSupplierIsNull() {
    Supplier<RuntimeException> supplier = null;
    assertThatThrownBy(
            () -> {
              Ensure.isTrue(false, supplier);
            })
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was null");
  }

  @Test
  void shouldThrowIfSupplierIsNullWithCustomMessage() {
    Supplier<RuntimeException> supplier = () -> null;
    assertThatThrownBy(
            () -> {
              Ensure.isTrue(false, supplier);
            })
        .isInstanceOf(EnsureException.class)
        .hasMessage("supplier was given a null value");
  }

  @Test
  void shouldVerifyEnsure() {
    ArchRuleDefinition.theClass(Ensure.class)
        .should()
        .bePublic()
        .andShould()
        .haveOnlyPrivateConstructors()
        .andShould(
            new ArchCondition<>("verify Ensure methods:") {
              @Override
              public void check(JavaClass item, ConditionEvents events) {
                final long count = getMethodCount(item);
                final long expectedMethodCount = 111;
                assertThat(count)
                    .as(
                        "Expected methods: %s - %s"
                            .formatted(expectedMethodCount, getMethodNames(item)))
                    .isEqualTo(expectedMethodCount);
              }
            })
        .check(ArchTestUtils.ENSURE_PACKAGE);
  }
}
