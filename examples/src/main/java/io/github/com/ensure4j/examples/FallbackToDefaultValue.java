package io.github.com.ensure4j.examples;

import io.github.mangila.ensure4j.Ensure;

import java.util.ArrayList;

/**
 * Some null values in a list transform to a default value.
 */
public class FallbackToDefaultValue {

    record VehicleDetails(String model, String color) {
        static final VehicleDetails DEFAULT = new VehicleDetails("unknown", "unknown");
    }

    public static void main(String[] args) {
        var l = new ArrayList<VehicleDetails>();
        l.add(null);
        l.add(new VehicleDetails("V70", "Blue"));
        l.add(null);
        l.add(new VehicleDetails("XC90", "Blue"));
        l.stream()
                .map(v -> Ensure.notNullOrElse(v, VehicleDetails.DEFAULT))
                .forEach(System.out::println);
        // or create a default value on demand
        l = new ArrayList<>();
        l.add(null);
        l.add(new VehicleDetails("V70", "Blue"));
        l.add(null);
        l.add(new VehicleDetails("XC90", "Blue"));
        l.stream()
                .map(v -> Ensure.notNullOrElseGet(v, () -> new VehicleDetails("unknown", "unknown")))
                .forEach(System.out::println);
    }

}
