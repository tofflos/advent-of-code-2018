package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functional {

    public static void main(String[] args) throws IOException {

        var offsets = Files.readAllLines(Paths.get("1.in")).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var cycle = new Supplier<Integer>() {
            int index = 0;

            @Override
            public Integer get() {
                return offsets.get(index++ % offsets.size());
            }
        };

        var accumulator = new Function<Integer, Integer>() {
            Integer frequency = 0;

            @Override
            public Integer apply(Integer offset) {
                return frequency = frequency + offset;
            }
        };

        var unseen = new Predicate<Integer>() {
            Set<Integer> history = new HashSet<>();

            @Override
            public boolean test(Integer frequency) {
                if (history.contains(frequency)) {
                    return false;
                }

                history.add(frequency);

                return true;
            }
        };

        // Part one
        System.out.println("Part one: " + offsets.stream().reduce(Integer::sum).get());

        // Part two
        System.out.println("Part two: " + Stream.generate(cycle).map(accumulator).dropWhile(unseen).findFirst().get());
    }
}
