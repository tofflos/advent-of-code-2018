package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Functional {

    public static void main(String[] args) throws IOException {
        Function<String, Map<String, Long>> letterFrequencies = s -> s.codePoints()
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var boxes = Files.readAllLines(Paths.get("2.in")).stream()
                .collect(Collectors.toMap(Function.identity(), letterFrequencies));

        var twos = boxes.values().stream().filter(map -> map.containsValue(2L)).count();
        var threes = boxes.values().stream().filter(map -> map.containsValue(3L)).count();
        var checksum = twos * threes;

        System.out.println("Part one: " + checksum);        
    }
}