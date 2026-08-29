package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;
import java.util.List;

public class Examples {

  public void streamCollection(List<String> list) {
    Ensure.notEmpty(list);
    list.stream()
        .map(Ensure::notBlank)
        .map(Ensure::matchesAlphanumeric)
        .map(s -> Ensure.endsWith(s, "se"))
        .forEach(
            s -> {
              System.out.println(s + "was ensured!");
            });
  }
}
