package io.github.mangila.ensure4j.ops;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.mangila.ensure4j.ArchTestUtils;
import org.junit.jupiter.api.Test;

class EnsureOpsArchitectureTest {

  @Test
  void shouldCountOpsEnums() {
    final int expectedOpsCount = 9;
    final var ensureOpsClasses = ArchTestUtils.ENSURE_OPS_CLASSES.stream().toList();
    assertThat(ensureOpsClasses)
        .as(
            "number of EnsureOps enum classes: %d - %s"
                .formatted(ensureOpsClasses.size(), ensureOpsClasses))
        .hasSize(expectedOpsCount);
  }
}
